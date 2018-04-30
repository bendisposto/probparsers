package de.prob.parser.antlr;

import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import de.be4.classicalb.core.parser.exceptions.BException;
import files.BParser;

import static files.BParser.*;

public class MyErrorListener extends BaseErrorListener {
	BException exception;

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		System.out.println("here: " + msg);
		if (!msg.contains("reportAttemptingFullContext")) {
			//exception = new BException("test", msg, null);
			throw new RuntimeException();
		} else {
			System.out.println();
			System.out.println("Message: " + msg);
			CommonToken token = (CommonToken) offendingSymbol;
			BParser parser = (BParser) recognizer;
			ParserRuleContext ruleContext = parser.getRuleContext();
			// FormulaContext con = (FormulaContext) ruleContext;
			// DefinitionContext defCon = (DefinitionContext) con.parent;
			// String defName = defCon.name.getText();
			// System.out.println(defName);
			System.out.println("----------------");
		}

	}

}
