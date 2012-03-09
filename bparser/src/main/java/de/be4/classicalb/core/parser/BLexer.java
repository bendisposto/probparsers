package de.be4.classicalb.core.parser;

import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.List;

import de.be4.classicalb.core.parser.exceptions.BLexerException;
import de.be4.classicalb.core.parser.lexer.Lexer;
import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.TComment;
import de.be4.classicalb.core.parser.node.TCommentEnd;
import de.be4.classicalb.core.parser.node.TDefLiteralPredicate;
import de.be4.classicalb.core.parser.node.TDefLiteralSubstitution;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TStringLiteral;
import de.be4.classicalb.core.parser.node.TWhiteSpace;
import de.be4.classicalb.core.parser.node.Token;

public class BLexer extends Lexer {

	private TComment comment = null;
	private StringBuilder commentBuffer = null;
	private List<Pragma> pragmas = new ArrayList<Pragma>();

	private final DefinitionTypes definitions;

	// private final List<Token> tokenList;

	private boolean debugOutput = false;

	public BLexer(final PushbackReader in, final DefinitionTypes definitions,
			final int tokenCountPrediction) {
		super(in);
		this.definitions = definitions;

		// if (tokenCountPrediction > 10) {
		// tokenList = new ArrayList<Token>(tokenCountPrediction);
		// } else {
		// tokenList = new ArrayList<Token>();
		// }
	}

	public BLexer(final PushbackReader in, final DefinitionTypes definitions) {
		this(in, definitions, -1);
	}

	public BLexer(final PushbackReader in) {
		this(in, null);
	}

	@Override
	protected void filter() throws LexerException, IOException {

		if (state.equals(State.COMMENT)) {
			collectComment();
		}

		if (token instanceof TStringLiteral) {
			// google for howto-unescape-a-java-string-literal-in-java
			// quickfix: we do nothing just strip off the "
			final String literal = token.getText();
			token.setText(literal.substring(1, literal.length() - 1));
		}
		if (token != null) {
			if (definitions != null) {
				replaceDefTokens();
			}

			buildTokenList();

			if (debugOutput && !(token instanceof TWhiteSpace)
					&& !(token instanceof EOF)) {
				System.out.print(token.getClass().getSimpleName() + "('"
						+ token.getText() + "') ");
			}
		}
	}

	private void replaceDefTokens() {
		if (token instanceof TIdentifierLiteral) {
			final Definitions.Type type = definitions.getType(token.getText());

			/*
			 * If no type is set, something went wrong during preparsing.
			 * Probably the right hand side of the definition was not parseble.
			 * But we'll also find this error in the main parser.
			 */
			if (type != null) {
				switch (type) {
				case Predicate:
					final Token predToken = new TDefLiteralPredicate(
							token.getText());
					predToken.setLine(token.getLine());
					predToken.setPos(token.getPos());
					token = predToken;
					break;

				case Substitution:
					final Token substToken = new TDefLiteralSubstitution(
							token.getText());
					substToken.setLine(token.getLine());
					substToken.setPos(token.getPos());
					token = substToken;
					break;

				default:
					/*
					 * no replacement for expression definitions (done after
					 * parsing) or for normal identifier
					 */
					break;
				}
			}
		}
	}

	private void buildTokenList() {
		if (token != null) {
			// tokenList.add(token);
		}
	}

	private void collectComment() throws LexerException, IOException {
		if (token instanceof EOF) {
			// make sure we don't loose this token, needed for error message
			// tokenList.add(token);
			final int line = token.getLine() - 1;
			final int pos = token.getPos() - 1;
			final String text = token.getText();

			throw new BLexerException(token, "Comment not closed",
					text.toString(), line, pos);
		}

		// starting a new comment
		if (comment == null) {
			comment = (TComment) token;
			commentBuffer = new StringBuilder(token.getText());
			token = null;
		} else {
			commentBuffer.append(token.getText());

			// end of comment reached?
			if (token instanceof TCommentEnd) {
				String text = commentBuffer.toString();
				comment.setText(text);
				token = comment;
				comment = null;
				commentBuffer = null;
				state = State.NORMAL;
				if (text.startsWith("/*!")) {
					String pragmaText = "";
					if (text.endsWith("!*/")) pragmaText = text.substring(3,
							text.length() - 3).trim();
					else
						pragmaText = text.substring(3, text.length() - 2)
								.trim();
					pragmas.add(new Pragma(token.getStartPos(), token
							.getEndPos(), pragmaText));
				}
			} else {
				token = null;
			}
		}
	}

	public List<Pragma> getPragmas() {
		return pragmas;
	}

	public void setDebugOutput(final boolean debugOutput) {
		this.debugOutput = debugOutput;
	}
}
