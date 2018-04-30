package de.be4.classicalb.core.parser.antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MyTreeListener implements ParseTreeListener {

	@Override
	public void visitTerminal(TerminalNode node) {
		System.out.println(node.getClass().getName() + " " + node.getText());
		// TODO Auto-generated method stub

	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		String name = ctx.getClass().getName();
		int startIndex = ctx.start.getStartIndex();
		int endIndex = ctx.stop.getStopIndex();
		System.out.println(name);
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub

	}

}
