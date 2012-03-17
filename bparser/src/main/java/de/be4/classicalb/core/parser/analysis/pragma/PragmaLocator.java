package de.be4.classicalb.core.parser.analysis.pragma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.analysis.pragma.internal.ClassifiedPragma;
import de.be4.classicalb.core.parser.analysis.pragma.internal.RawPragma;
import de.be4.classicalb.core.parser.analysis.pragma.internal.UnknownPragma;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.SourcePosition;

public class PragmaLocator extends DepthFirstAdapter {

	private static Map<String, Classifier> classifiers = new HashMap<String, Classifier>();

	public static void addClassifier(String name,
			Class<? extends Node> seekFor, PragmaParser... argParsers) {
		classifiers.put(name, new Classifier(seekFor, argParsers));
	}

	static {
		addClassifier("label", PPredicate.class); // if no argument parser is
													// given or a parser is null
													// we will use identity
	}

	private Node[] nearestLeft;
	private Node[] nearestRight;
	private Node[] predecessor;
	private Node[] successor;
	private Node[] container;
	private RawPragma[] pragmas;

	public PragmaLocator(RawPragma[] p) {
		this.pragmas = p;
	}

	@Override
	public void inStart(Start node) {
		for (int i = 0; i < pragmas.length; i++) {
			nearestLeft[i] = node;
			container[i] = node;
		}
	}

	@Override
	public void caseEOF(EOF node) {
		for (int i = 0; i < pragmas.length; i++) {
			if (nearestRight[i] == null) nearestRight[i] = node;
			if (successor[i] == null) successor[i] = node;
		}
	}

	@Override
	public void defaultOut(Node node) {
		SourcePosition endPos = node.getEndPos();
		SourcePosition startPos = node.getStartPos();
		if (endPos == null) return; // no source info available
		for (int i = 0; i < pragmas.length; i++) {
			SourcePosition start = pragmas[i].getStart();
			SourcePosition end = pragmas[i].getEnd();
			if (endPos.compareTo(start) <= 0) predecessor[i] = node;
			if (nearestRight[i] == null && end.compareTo(startPos) <= 0)
				nearestRight[i] = node;
		}
	}

	@Override
	public void defaultIn(Node node) {
		Node n = node;
		// System.out.println(n.getClass().getSimpleName());
		SourcePosition startPos = node.getStartPos();
		SourcePosition endPos = node.getEndPos();
		if (startPos != null && endPos != null) {
			for (int i = 0; i < pragmas.length; i++) {
				SourcePosition start = pragmas[i].getStart();
				SourcePosition end = pragmas[i].getEnd();
				if (endPos.compareTo(start) <= 0) nearestLeft[i] = node;
				if (startPos.compareTo(start) <= 0
						&& endPos.compareTo(end) >= 0) container[i] = node;
				if (successor[i] == null && end.compareTo(startPos) <= 0)
					successor[i] = node;
			}
		}
	}

	public static ArrayList<Pragma> locate(Start ast, RawPragma[] p) {
		PragmaLocator locator = new PragmaLocator(p);
		int size = p.length;
		locator.nearestLeft = new Node[size];
		locator.container = new Node[size];
		locator.predecessor = new Node[size];
		locator.successor = new Node[size];
		locator.nearestRight = new Node[size];
		ast.apply(locator);

		ArrayList<Pragma> list = new ArrayList<Pragma>();
		for (int i = 0; i < p.length; i++) {
			RawPragma pma = locator.pragmas[i];
			UnknownPragma unknownPragma = new UnknownPragma(pma,
					locator.nearestLeft[i], locator.predecessor[i],
					locator.container[i], locator.successor[i],
					locator.nearestRight[i]);
			;
			list.add(classify(unknownPragma));
		}
		return list;
	}

	private static Pragma classify(UnknownPragma p) {
		String name = p.getPragmaName();
		Classifier classifier = classifiers.get(name);
		if (classifier == null) return p;
		List<String> parsedArgs = new ArrayList<String>();
		List<String> pragmaArguments = p.getPragmaArguments();
		for (int i = 0; i < pragmaArguments.size(); i++) {
			parsedArgs.add(classifier.getParser(i).parse(pragmaArguments.get(i)));
		}
		Node attachment = classifier.seek(p.getNearestRight());
		return new ClassifiedPragma(name,attachment, parsedArgs);
	}
}
