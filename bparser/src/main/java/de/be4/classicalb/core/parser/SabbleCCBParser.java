package de.be4.classicalb.core.parser;

import java.util.ArrayList;
import java.util.LinkedList;

import de.be4.classicalb.core.parser.lexer.Lexer;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.parser.Parser;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class SabbleCCBParser extends Parser {

	public SabbleCCBParser(Lexer lexer) {
		super(lexer);
	}

	protected ArrayList new337() /* reduce AMultipleExpressionList */
	{
		this.firstPopped = null;
		this.lastPopped = null;
		ArrayList nodeList = new ArrayList();

		ArrayList nodeArrayList3 = pop();
		ArrayList nodeArrayList2 = pop();
		ArrayList nodeArrayList1 = pop();
		LinkedList listNode3 = new LinkedList();
		{
			// Block
			LinkedList listNode1 = new LinkedList();
			PExpression pexpressionNode2;
			listNode1 = (LinkedList) nodeArrayList1.get(0);
			pexpressionNode2 = (PExpression) nodeArrayList3.get(0);
			if (listNode1 != null) // Macro:ParserTypedLinkedListAddAll
			{
				// listNode3.addAll(listNode1);
				listNode3 = listNode1; // this line was previously patched
			}
			if (pexpressionNode2 != null) {
				listNode3.add(pexpressionNode2);
			}
		}
		nodeList.add(listNode3);
		// return nodeList;
		final ArrayList containerList = nodeList;
		Object elementToCheck = containerList.get(0);
		checkResult(elementToCheck, false);

		return containerList;
	}
}
