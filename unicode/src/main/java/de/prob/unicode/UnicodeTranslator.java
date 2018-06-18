package de.prob.unicode;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import de.prob.unicode.lexer.Lexer;
import de.prob.unicode.lexer.LexerException;
import de.prob.unicode.node.EOF;
import de.prob.unicode.node.TAnyChar;
import de.prob.unicode.node.TDoubleQuote;
import de.prob.unicode.node.TSeparator;
import de.prob.unicode.node.Token;

public class UnicodeTranslator {
	enum Encoding {
		ASCII, LATEX, UNICODE
	}

	private static final class Translation {

		private final String ascii;
		private final String latex;
		private final String unicode;

		public Translation(final String ascii, final String latex, final String unicode) {
			this.ascii = ascii;
			this.latex = latex;
			this.unicode = unicode;
		}

		public String getAscii(final boolean needsSpace) {
			// If the operator begins with a letter, and the last character
			// before is also a letter, there needs to be a space to separate
			// them
			return (Character.isLetter(ascii.charAt(0)) && needsSpace ? " " : "") + ascii;
		}

		public String getLatex() {
			return latex;
		}

		public String getUnicode() {
			return unicode;
		}

	}

	private static final Map<String, Translation> m = new HashMap<>();

	static {
		m.put("TIn", new Translation(":", "\\in", "\u2208"));
		m.put("TNotsubseteq", new Translation("/<:", "\\notsubseteq", "\u2288"));
		m.put("TNotsubset", new Translation("/<<:", "\\notsubset", "\u2284"));
		m.put("TSubseteq", new Translation("<:", "\\subseteq", "\u2286"));
		m.put("TSetminus", new Translation("\\", "\\setminus", "\u2216"));
		m.put("TDotdot", new Translation("..", "\\ldots", "\u2025"));
		m.put("TDotdotdot", new Translation("...", "\\0xpto", "\u2026")); // ellipsis, used in shortened set values #Nr.{a,b,...,c,d}
		m.put("TNat1", new Translation("NAT1", "\\nat1", "\u21151"));
		m.put("TNat", new Translation("NAT", "\\nat", "\u2115"));
		m.put("TEmptyset", new Translation("{}", "\\emptyset", "\u2205"));
		m.put("TBcmsuch", new Translation(":|", "\\bcmsuch", ":\u2223"));
		m.put("TBfalse", new Translation("false", "\\bfalse", "\u22a5"));
		m.put("TForall", new Translation("!", "\\forall", "\u2200"));
		m.put("TExists", new Translation("#", "\\exists", "\u2203"));
		m.put("TMapsto", new Translation("|->", "\\mapsto", "\u21a6"));
		m.put("TBtrue", new Translation("true", "\\btrue", "\u22a4"));
		m.put("TSubset", new Translation("<<:", "\\subset", "\u2282"));
		m.put("TBunion", new Translation("\\/", "\\bunion", "\u222a"));
		m.put("TBinter", new Translation("/\\", "\\binter", "\u2229"));
		m.put("TDomres", new Translation("<|", "\\domres", "\u25c1"));
		m.put("TRanres", new Translation("|>", "\\ranres", "\u25b7"));
		m.put("TDomsub", new Translation("<<|", "\\domsub", "\u2a64"));
		m.put("TRansub", new Translation("|>>", "\\ransub", "\u2a65"));
		m.put("TLambda", new Translation("%", "\\lambda", "\u03bb"));
		m.put("TOftype", new Translation("oftype", "\\oftype", "\u2982"));
		m.put("TNotin", new Translation("/:", "\\notin", "\u2209"));
		m.put("TCprod", new Translation("**", "\\cprod", "\u00d7"));
		m.put("TUnion", new Translation("UNION", "\\Union", "\u22c3"));
		m.put("TInter", new Translation("INTER", "\\Inter", "\u22c2"));
		m.put("TFcomp", new Translation(";", "\\fcomp", "\u003b"));
		m.put("TBcomp", new Translation("circ", "\\bcomp", "\u2218"));
		m.put("TStrel", new Translation("<<->>", "\\strel", "\ue102"));
		m.put("TDprod", new Translation("><", "\\dprod", "\u2297"));
		m.put("TPprod", new Translation("||", "\\pprod", "\u2225"));
		m.put("TBcmeq", new Translation(":=", "\\bcmeq", "\u2254"));
		m.put("TBcmin", new Translation("::", "\\bcmin", ":\u2208"));
		m.put("TIntg", new Translation("INT", "\\intg", "\u2124"));
		m.put("TLand", new Translation("&", "\\land", "\u2227"));
		m.put("TLimp", new Translation("=>", "\\limp", "\u21d2"));
		m.put("TLeqv", new Translation("<=>", "\\leqv", "\u21d4"));
		m.put("TLnot", new Translation("not", "\\lnot", "\u00ac"));
		m.put("TQdot", new Translation(".", "\\qdot", "\u00b7"));
		m.put("TConv", new Translation("~", "\\conv", "\u223c"));
		m.put("TTrel", new Translation("<<->", "\\trel", "\ue100"));
		m.put("TSrel", new Translation("<->>", "\\srel", "\ue101"));
		m.put("TPfun", new Translation("+->", "\\pfun", "\u21f8"));
		m.put("TTfun", new Translation("-->", "\\tfun", "\u2192"));
		m.put("TPinj", new Translation(">+>", "\\pinj", "\u2914"));
		m.put("TTinj", new Translation(">->", "\\tinj", "\u21a3"));
		m.put("TPsur", new Translation("+>>", "\\psur", "\u2900"));
		m.put("TTsur", new Translation("->>", "\\tsur", "\u21a0"));
		m.put("TTbij", new Translation(">->>", "\\tbij", "\u2916"));
		m.put("TExpn", new Translation("^", "\\expn", "\u005e"));
		m.put("TLor", new Translation("or", "\\lor", "\u2228"));
		m.put("TPow1", new Translation("POW1", "\\pow1", "\u21191"));
		m.put("TPow", new Translation("POW", "\\pow", "\u2119"));
		m.put("TMid", new Translation("|", "\\mid", "\u2223")); // is the divides symbol, also generated by Rodin
		m.put("TNeq", new Translation("/=", "\\neq", "\u2260"));
		m.put("TRel", new Translation("<->", "\\rel", "\u2194"));
		m.put("TOvl", new Translation("<+", "\\ovl", "\ue103"));
		m.put("TLeq", new Translation("<=", "\\leq", "\u2264"));
		m.put("TGeq", new Translation(">=", "\\geq", "\u2265"));
		m.put("TDiv", new Translation("/", "\\div", "\u00f7"));
		m.put("TMult", new Translation("*", "*", "\u2217"));
		m.put("TMinus", new Translation("-", "-", "\u2212"));
		m.put("TLbrace", new Translation("{", "\\{", "{"));
		m.put("TRbrace", new Translation("}", "\\}", "}"));
		m.put("TComma", new Translation(",", ",", ","));

		m.put("TTake", new Translation("/|\\", "/\\mid\\textbackslash", "/|\\"));
		m.put("TDrop", new Translation("\\|/", "\\textbackslash\\mid/", "\\|/"));
		m.put("TWhitespace", new Translation(" ", " ", " "));

		m.put("TTypeofOpen", new Translation("/*","/*", "/*"));
		m.put("TTypeofClose", new Translation("*/", "*/", "*/"));
	}

	public static void main(final String[] args) throws LexerException, IOException {
		String input = args[0];
		StringReader reader = new StringReader(input);
		PushbackReader r = new PushbackReader(reader, input.length());
		Lexer l = new Lexer(r);
		Token t;
		while (!((t = l.next()) instanceof EOF)) {
			String key = t.getClass().getSimpleName();
			System.out.print(key);
			System.out.print(" ");
		}
		System.out.println(UnicodeTranslator.toAscii(input));
		System.out.println(UnicodeTranslator.toLatex(input));
		System.out.println(UnicodeTranslator.toUnicode(input));
	}

	public static String toAscii(final String s) {
		return translate(s, Encoding.ASCII);
	}

	public static String toLatex(final String s) {
		return translate(s, Encoding.LATEX);
	}

	public static String toUnicode(final String s) {
		return translate(s, Encoding.UNICODE);
	}

	private static String translate(final String input, final Encoding target) {
		if (input.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder(input.length());
		StringReader reader = new StringReader(input);
		PushbackReader r = new PushbackReader(reader, input.length());
		Lexer l = new Lexer(r);

		Token t;
		Token last;
		boolean insideDoubleQuouteComment = false;
		try {
			last = null;
			while ((t = l.next()) != null && !(t instanceof EOF)) {
				if (insideDoubleQuouteComment && !(t instanceof TDoubleQuote)) {
					sb.append(t.getText());
					continue;
				} else if (insideDoubleQuouteComment && t instanceof TDoubleQuote) {
					sb.append(t.getText());
					insideDoubleQuouteComment = false;
					continue;
				} else if (!insideDoubleQuouteComment && t instanceof TDoubleQuote) {
					sb.append(t.getText());
					insideDoubleQuouteComment = true;
					continue;
				}

				final String key = t.getClass().getSimpleName();

				if (t instanceof TSeparator) {
					sb.append(t.getText());
				} else if (t instanceof TAnyChar) {
					boolean before = sb.length() > 0 && Character.isLetter(sb.charAt(sb.length() - 1));
					if (before && (target == Encoding.ASCII || target == Encoding.LATEX)) {
						sb.append(' ');
					}
					if (target == Encoding.LATEX) {
						sb.append(t.getText().replace("_", "\\_"));
					} else {
						sb.append(t.getText());
					}
				} else {
					String translated;
					Translation translation = m.get(key);
					if (target == Encoding.UNICODE) {
						translated = translation.getUnicode();
					} else if (target == Encoding.LATEX) {
						translated = translation.getLatex();
					} else if (target == Encoding.ASCII) {
						boolean before = (last != null && last instanceof TAnyChar)
								|| sb.length() > 0 && (Character.isLetter(sb.charAt(sb.length() - 1)));
						translated = translation.getAscii(before);
					} else {
						throw new AssertionError("Unhandled translation target: " + target);
					}
					sb.append(translated);
				}
				last = t;
			}
		} catch (LexerException | IOException e) {
			throw new AssertionError(e);
		}
		return sb.toString();
	}
}
