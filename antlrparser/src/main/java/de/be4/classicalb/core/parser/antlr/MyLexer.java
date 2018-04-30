package de.be4.classicalb.core.parser.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;

import files.BLexer;

public class MyLexer extends BLexer {

	public MyLexer(CharStream input) {
		super(input);
	}

	@Override
	public Token nextToken() {
		Token token = super.nextToken();
		String s = this.VOCABULARY.getSymbolicName(token.getType());
		System.out.println(token.getText() + " " + token.getClass() +" "+ token.getType() + " " + s + " " + this._mode);
//		if (token.getText().equals("foo") && token instanceof CommonToken) {
//			CommonToken commonToken = (CommonToken) token;
//			System.out.println(commonToken.getType());
//			commonToken.setType(Number);
//			commonToken.setText("12");
//			return commonToken;
//		}
//		
		return token;
	}

}
