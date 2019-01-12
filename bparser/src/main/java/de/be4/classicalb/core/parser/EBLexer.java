package de.be4.classicalb.core.parser;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.TEqual;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TLeftPar;
import de.be4.classicalb.core.parser.node.TRightPar;
import de.be4.classicalb.core.parser.node.TTrue;
import de.hhu.stups.sablecc.patch.IToken;

public class EBLexer extends BLexer {

	// True means Expression
	private Map<String, Boolean> v = null;

	public EBLexer(String theFormula, BigInteger b, List<String> ids, DefinitionTypes defTypes) {
		super(new PushbackReader(new StringReader(theFormula), 99), defTypes, theFormula.length());
		v = constructMap(b, ids);
	}

	private Map<String, Boolean> constructMap(BigInteger b, List<String> ids) {
		Map<String, Boolean> res = new HashMap<String, Boolean>();
		for (int i = 0; i < ids.size(); i++) {
			String id = ids.get(i);
			if (!res.containsKey(id)) {
				res.put(id, b.testBit(i));
			}
		}
		return res;
	}

	@Override
	protected void filter() throws LexerException, IOException {
		super.filter();
		if (token != null && token instanceof TIdentifierLiteral && v.get(token.getText())) {
			Queue<IToken> ts = getNextList();
			List<IToken> toks = getTokenList();
			int l = token.getLine();
			int p = token.getPos();

			TLeftPar t1 = new TLeftPar(l, p);
			ts.add(t1);
			toks.add(t1);

			ts.add(token);
			toks.add(token);

			TEqual t2 = new TEqual("=", l, p);
			ts.add(t2);
			toks.add(t2);

			TTrue t3 = new TTrue(l, p);
			ts.add(t3);
			toks.add(t3);

			TRightPar t4 = new TRightPar(l, p);
			ts.add(t4);
			toks.add(t4);

			token = null;
		}

	}
}
