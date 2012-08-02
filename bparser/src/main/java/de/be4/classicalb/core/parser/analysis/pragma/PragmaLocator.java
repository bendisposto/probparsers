package de.be4.classicalb.core.parser.analysis.pragma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.analysis.pragma.internal.ClassifiedPragma;
import de.be4.classicalb.core.parser.analysis.pragma.internal.PrefixClassifier;
import de.be4.classicalb.core.parser.analysis.pragma.internal.RawPragma;
import de.be4.classicalb.core.parser.analysis.pragma.internal.UnitPragmaClassifier;
import de.be4.classicalb.core.parser.analysis.pragma.internal.UnknownPragma;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.SourcePosition;

/**
 * @author bendisposto
 * 
 */
public class PragmaLocator extends DepthFirstAdapter {
	

	private Map<String, IClassifier> classifiers = new HashMap<String, IClassifier>();

	@SuppressWarnings("unchecked")
	private PragmaLocator(List<RawPragma> p, String input) {
		this.pragmas = p;
		classifiers.put("label", new PrefixClassifier(input,  PPredicate.class));
		classifiers.put("symbolic", new PrefixClassifier(input, PExpression.class));
		classifiers.put("unit", new UnitPragmaClassifier(input));
		classifiers.put("inferred_unit", new UnitPragmaClassifier(input));
		classifiers.put("conversion", new PrefixClassifier(input, PExpression.class));
	}

	private Node[] nearestLeft;
	private Node[] nearestRight;
	private Node[] predecessor;
	private Node[] successor;
	private Node[] container;
	private final List<RawPragma> pragmas;

	@Override
	public void inStart(Start node) {
		for (int i = 0; i < pragmas.size(); i++) {
			nearestLeft[i] = node;
			container[i] = node;
		}
	}

	@Override
	public void caseEOF(EOF node) {
		for (int i = 0; i < pragmas.size(); i++) {
			if (nearestRight[i] == null) nearestRight[i] = node;
			if (successor[i] == null) successor[i] = node;
		}
	}

	@Override
	public void defaultOut(Node node) {
		SourcePosition endPos = node.getEndPos();
		SourcePosition startPos = node.getStartPos();
		if (endPos == null) return; // no source info available
		for (int i = 0; i < pragmas.size(); i++) {
			SourcePosition start = pragmas.get(i).getStart();
			SourcePosition end = pragmas.get(i).getEnd();
			if (endPos.compareTo(start) <= 0) predecessor[i] = node;
			if (nearestRight[i] == null && end.compareTo(startPos) <= 0)
				nearestRight[i] = node;
		}
	}

	@Override
	public void defaultIn(Node node) {
		SourcePosition startPos = node.getStartPos();
		SourcePosition endPos = node.getEndPos();
		if (startPos != null && endPos != null) {
			for (int i = 0; i < pragmas.size(); i++) {
				SourcePosition start = pragmas.get(i).getStart();
				SourcePosition end = pragmas.get(i).getEnd();
				if (endPos.compareTo(start) <= 0) nearestLeft[i] = node;
				if (startPos.compareTo(start) <= 0
						&& endPos.compareTo(end) >= 0) container[i] = node;
				if (successor[i] == null && end.compareTo(startPos) <= 0)
					successor[i] = node;
			}
		}
	}

	public static ArrayList<Pragma> locate(Start ast, List<RawPragma> p,
			String input) {
		PragmaLocator locator = new PragmaLocator(p, input);
		int size = p.size();
		locator.nearestLeft = new Node[size];
		locator.container = new Node[size];
		locator.predecessor = new Node[size];
		locator.successor = new Node[size];
		locator.nearestRight = new Node[size];
		ast.apply(locator);

		ArrayList<Pragma> list = new ArrayList<Pragma>();
		for (int i = 0; i < p.size(); i++) {
			RawPragma pma = locator.pragmas.get(i);
			UnknownPragma unknownPragma = new UnknownPragma(pma,
					locator.nearestLeft[i], locator.predecessor[i],
					locator.container[i], locator.successor[i],
					locator.nearestRight[i]);
			;
			list.add(locator.classify(unknownPragma, ast));
		}
		return  list;
	}

	private Pragma classify(UnknownPragma p, Start ast) {
		String name = p.getPragmaName();
		IClassifier classifier = classifiers.get(name);
		if (classifier == null) return p;
		List<String> parsedArgs = new ArrayList<String>();
		List<String> pragmaArguments = p.getPragmaArguments();
		for (int i = 0; i < pragmaArguments.size(); i++) {
			parsedArgs.add(classifier.getParser(i)
					.parse(pragmaArguments.get(i)));
		}
		Node attachment = classifier.seek(p, ast);
		List<String> warnings = classifier.getWarnings();
		return new ClassifiedPragma(name, attachment, parsedArgs, warnings, p.getStart(), p.getEnd());
	}
}
