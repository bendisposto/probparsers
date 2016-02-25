package de.be4.classicalb.core.parser;

import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.be4.classicalb.core.parser.exceptions.BLexerException;
import de.be4.classicalb.core.parser.lexer.Lexer;
import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.TComment;
import de.be4.classicalb.core.parser.node.TCommentEnd;
import de.be4.classicalb.core.parser.node.TConjunction;
import de.be4.classicalb.core.parser.node.TDefLiteralPredicate;
import de.be4.classicalb.core.parser.node.TDefLiteralSubstitution;
import de.be4.classicalb.core.parser.node.TDot;
import de.be4.classicalb.core.parser.node.TDotPar;
import de.be4.classicalb.core.parser.node.TEnd;
import de.be4.classicalb.core.parser.node.THexLiteral;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TIntegerLiteral;
import de.be4.classicalb.core.parser.node.TLeftPar;
import de.be4.classicalb.core.parser.node.TPragmaDescription;
import de.be4.classicalb.core.parser.node.TSemicolon;
import de.be4.classicalb.core.parser.node.TStringLiteral;
import de.be4.classicalb.core.parser.node.TWhiteSpace;
import de.be4.classicalb.core.parser.node.Token;
import de.hhu.stups.sablecc.patch.IToken;

public class BLexer extends Lexer {

	private static Map<Class<? extends Token>, Map<Class<? extends Token>, String>> invalid = new HashMap<Class<? extends Token>, Map<Class<? extends Token>, String>>();

	private static void addInvalid(Class<? extends Token> f,
			Class<? extends Token> s, String message) {
		Map<Class<? extends Token>, String> secs = invalid.get(f);
		if (secs == null)
			secs = new HashMap<Class<? extends Token>, String>();
		secs.put(s, message);
		invalid.put(f, secs);
	}

	static {
		addInvalid(TSemicolon.class, TSemicolon.class,
				"Two succeeding semicolons are not allowed.");
		addInvalid(TConjunction.class, TConjunction.class,
				"& & is not allowed.");
		addInvalid(TLogicalOr.class, TLogicalOr.class,
				"or or is not allowed.");
		addInvalid(TDoubleVerticalBar.class, TDoubleVerticalBar.class,
				"|| || is not allowed.");
		addInvalid(TSetSubtraction.class, TEqual.class,
				"You need to use /= for inequality and not \=.");

		/*
		 * This is wrong! see testSemicolonAdEnd2 for an example where the
		 * combination is valid addInvalid( TSemicolon.class, TEnd.class,
		 * "A semicolon is not allowed before END. Remember: The last Operation must not end with a semicolon."
		 * );
		 */
	}

	private Map<Class<? extends Token>, String> currentlyInvalid = null;

	private Token comment = null;
	private StringBuilder commentBuffer = null;

	private final DefinitionTypes definitions;

	private final List<IToken> dotList = new ArrayList<IToken>();

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

		if (currentlyInvalid != null
				&& currentlyInvalid.containsKey(token.getClass())) {
			int l = token.getLine() + 1;
			int c = token.getPos();
			String errormessage = currentlyInvalid.get(token.getClass())
					+ "\n The error occurred near [" + l + "," + c + "]";
			throw new LexerException(errormessage);
		} else if (!(token instanceof TWhiteSpace)) {
			currentlyInvalid = invalid.get(token.getClass());
		}

		if (state.equals(State.COMMENT)) {
			collectComment();
		}

		if (state.equals(State.DESCRIPTION)
				&& !(token instanceof TPragmaDescription)) {
			collectComment();
		}

		if (state.equals(State.SHEBANG) && token.getLine() != 1) {
			throw new LexerException(
					"#! only allowed in first line of the file");
		}

		if (token instanceof TStringLiteral) {
			// google for howto-unescape-a-java-string-literal-in-java
			// quickfix: we do nothing just strip off the "
			final String literal = token.getText();
			token.setText(literal.substring(1, literal.length() - 1));
		}
		if (token instanceof THexLiteral) {
			final String literal = token.getText().substring(2);
			int value = Integer.valueOf(literal, 16);
			token = new TIntegerLiteral(Integer.toString(value));
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

		if (token != null) {
			if (token instanceof TDot) {
				dotList.clear();
				dotList.add((IToken) token.clone());
				token = null;
			} else {

				if (!dotList.isEmpty()) {
					if (token instanceof TWhiteSpace
							|| token instanceof TComment) {
						dotList.add((IToken) token.clone());
						token = null;
					} else if (token instanceof TLeftPar) {
						dotList.set(0, new TDotPar(".(", dotList.get(0)
								.getLine(), dotList.get(0).getPos()));
						getNextList().addAll(dotList);
						dotList.clear();
						token = null;
					} else {
						getNextList().addAll(dotList);
						dotList.clear();
					}
				}
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
			commentBuffer = new StringBuilder(token.getText());
			comment = token;
			token = null;
		} else {
			commentBuffer.append(token.getText());

			// end of comment reached?
			if (token instanceof TCommentEnd) {
				String text = commentBuffer.toString();
				if (state.equals(State.DESCRIPTION))
					text = text.substring(0, text.length() - 2);
				comment.setText(text.trim());
				token = comment;
				comment = null;
				commentBuffer = null;
				state = State.NORMAL;

			} else {
				token = null;
			}
		}
	}

	public void setDebugOutput(final boolean debugOutput) {
		this.debugOutput = debugOutput;
	}
}
