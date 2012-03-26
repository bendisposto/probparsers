package de.be4.classicalb.core.parser.analysis.pragma;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import de.be4.classicalb.core.pragma.lexer.Lexer;
import de.be4.classicalb.core.pragma.lexer.LexerException;
import de.be4.classicalb.core.pragma.node.EOF;
import de.be4.classicalb.core.pragma.node.TArgument;
import de.be4.classicalb.core.pragma.node.Token;

public class ArgumentLexer {

	private final Lexer lexer;

	public ArgumentLexer(String input) {
		lexer = new Lexer(new PushbackReader(new StringReader(input),
				input.length()));
	}

	private List<String> split() {
		ArrayList<String> result = new ArrayList<String>();
		Token t = new EOF();
		do {
			try {
				t = lexer.next();
				String text = t.getText();
				if (t instanceof TArgument) {
					result.add(text);
				}
			} catch (LexerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// cannot happen with String input
			}
		} while (!(t instanceof EOF));
		return result;
	}

	public static List<String> split(String input) {
		ArgumentLexer lexer = new ArgumentLexer(input);
		return lexer.split();
	}

	public static String[] splitA(String input) {
		ArgumentLexer lexer = new ArgumentLexer(input);
		List<String> list = lexer.split();
		return list.toArray(new String[list.size()]);
	}

}
