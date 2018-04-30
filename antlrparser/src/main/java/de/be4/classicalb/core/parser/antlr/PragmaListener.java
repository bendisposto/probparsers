package de.be4.classicalb.core.parser.antlr;

import java.util.List;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import files.BLexer;
import files.BParser;
import files.BParserBaseListener;
import static files.BParser.*;

public class PragmaListener extends BParserBaseListener {
	CommonTokenStream tokens;

	public PragmaListener(CommonTokenStream tokens) {
		//this.tokens = new CommonTokenStream(tokens.getTokenSource(), BLexer.PRAGMA_CHANNEL);
		//String text = tokens.getText();
		//System.out.println(text);
		//System.out.println(tokens.getTokens());
	}

//	@Override
//	public void enterStart(BParser.StartContext ctx) {
//		Token start = ctx.start;
//		System.out.println(start);
//		Token stop = ctx.stop;
//		System.out.println(stop);
//		// List<Token> tokens2 = tokens.
//		System.out.println("tokens: " + tokens2);
//	}

	@Override
	public void exitComposed_identifier_list(Composed_identifier_listContext ctx) {
//		List<Composed_identifierContext> composedIidentifierContexts = ctx.composed_identifier();
//		for (Composed_identifierContext composedIdentifierContext : composedIidentifierContexts) {
//			Token stop = composedIdentifierContext.getStop();
//			System.out.println(stop.getText());
//			int i = stop.getTokenIndex();
//			List<Token> cmtChannel = tokens.getHiddenTokensToRight(i, BLexer.PRAGMA_CHANNEL);
//			System.out.println(cmtChannel);
//			if (cmtChannel != null) {
//				for (Token token : cmtChannel) {
//					System.out.println(token.toString());
//				}
//			}
//		}

	}
}
