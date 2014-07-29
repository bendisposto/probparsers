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
import de.prob.unicode.node.TSeparator;
import de.prob.unicode.node.Token;

public class UnicodeTranslator {
	private static final class Translation {

		private final String unicode;
		private final String ascii;

		public Translation(final String ascii, final String unicode) {
			this.ascii = ascii;
			this.unicode = unicode;
		}

		public String getAscii(final boolean needsSpace) {
			// If the operator begins with a letter, and the last character
			// before is also a letter, there needs to be a space to separate
			// them
			return (Character.isLetter(ascii.charAt(0)) && needsSpace ? " "
					: "") + ascii;
		}

		public String getUnicode() {
			return unicode;
		}

	}

	private static final Map<String, Translation> m = new HashMap<String, Translation>();

	static {
		m.put("TIn", new Translation(":", "\u2208"));
		m.put("TNotsubseteq", new Translation("/<:", "\u2288"));
		m.put("TNotsubset", new Translation("/<<:", "\u2284"));
		m.put("TSubseteq", new Translation("<:", "\u2286"));
		m.put("TSetminus", new Translation("\\", "\u2216"));
		m.put("TDotdot", new Translation("..", "\u2025"));
		m.put("TNat", new Translation("NAT", "\u2115"));
		m.put("TNat1", new Translation("NAT1", "\u21151"));
		m.put("TEmptyset", new Translation("{}", "\u2205"));
		m.put("TBcmsuch", new Translation(":|", ":\u2223"));
		m.put("TBfalse", new Translation("false", "\u22a5"));
		m.put("TForall", new Translation("!", "\u2200"));
		m.put("TExists", new Translation("#", "\u2203"));
		m.put("TMapsto", new Translation("|->", "\u21a6"));
		m.put("TBtrue", new Translation("true", "\u22a4"));
		m.put("TSubset", new Translation("<<:", "\u2282"));
		m.put("TBunion", new Translation("\\/", "\u222a"));
		m.put("TBinter", new Translation("/\\", "\u2229"));
		m.put("TDomres", new Translation("<|", "\u25c1"));
		m.put("TRanres", new Translation("|>", "\u25b7"));
		m.put("TDomsub", new Translation("<<|", "\u2a64"));
		m.put("TRansub", new Translation("|>>", "\u2a65"));
		m.put("TLambda", new Translation("%", "\u03bb"));
		m.put("TOftype", new Translation("oftype", "\u2982"));
		m.put("TNotin", new Translation("/:", "\u2209"));
		m.put("TCprod", new Translation("**", "\u00d7"));
		m.put("TUnion", new Translation("UNION", "\u22c3"));
		m.put("TInter", new Translation("INTER", "\u22c2"));
		m.put("TFcomp", new Translation(";", "\u003b"));
		m.put("TBcomp", new Translation("circ", "\u2218"));
		m.put("TStrel", new Translation("<<->>", "\ue102"));
		m.put("TDprod", new Translation("><", "\u2297"));
		m.put("TPprod", new Translation("||", "\u2225"));
		m.put("TBcmeq", new Translation(":=", "\u2254"));
		m.put("TBcmin", new Translation("::", ":\u2208"));
		m.put("TIntg", new Translation("INT", "\u2124"));
		m.put("TLand", new Translation("&", "\u2227"));
		m.put("TLimp", new Translation("=>", "\u21d2"));
		m.put("TLeqv", new Translation("<=>", "\u21d4"));
		m.put("TLnot", new Translation("not", "\u00ac"));
		m.put("TQdot", new Translation(".", "\u00b7"));
		m.put("TConv", new Translation("~", "\u223c"));
		m.put("TTrel", new Translation("<<->", "\ue100"));
		m.put("TSrel", new Translation("<->>", "\ue101"));
		m.put("TPfun", new Translation("+->", "\u21f8"));
		m.put("TTfun", new Translation("-->", "\u2192"));
		m.put("TPinj", new Translation(">+>", "\u2914"));
		m.put("TTinj", new Translation(">->", "\u21a3"));
		m.put("TPsur", new Translation("+>>", "\u2900"));
		m.put("TTsur", new Translation("->>", "\u21a0"));
		m.put("TTbij", new Translation(">->>", "\u2916"));
		m.put("TExpn", new Translation("^", "\u005e"));
		m.put("TLor", new Translation("or", "\u2228"));
		m.put("TPow", new Translation("POW", "\u2119"));
		m.put("TPow1", new Translation("POW1", "\u21191"));
		m.put("TMid", new Translation("|", "\u2223"));
		m.put("TNeq", new Translation("/=", "\u2260"));
		m.put("TRel", new Translation("<->", "\u2194"));
		m.put("TOvl", new Translation("<+", "\ue103"));
		m.put("TLeq", new Translation("<=", "\u2264"));
		m.put("TGeq", new Translation(">=", "\u2265"));
		m.put("TDiv", new Translation("/", "\u00f7"));
		m.put("TMult", new Translation("*", "\u2217"));
		m.put("TMinus", new Translation("-", "\u2212"));
		m.put("TComma", new Translation(",", ","));

		m.put("TTake", new Translation("/|\\", "/|\\"));
		m.put("TDrop", new Translation("\\|/", "\\|/"));
		m.put("TWhitespace", new Translation(" ", " "));
	}

	public static void main(final String[] args) throws LexerException,
			IOException {
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
		System.out.println(UnicodeTranslator.toUnicode(input));
	}

	public static String toAscii(final String s) {
		return translate(s, "ascii");
	}

	public static String toUnicode(final String s) {
		return translate(s, "unicode");
	}

	private static String translate(final String input, final String target) {
		if (input.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder(input.length());
		StringReader reader = new StringReader(input);
		PushbackReader r = new PushbackReader(reader, input.length());
		Lexer l = new Lexer(r);

		Token t, last;
		try {
			last = null;
			while ((t = l.next()) != null && !(t instanceof EOF)) {
				String key = t.getClass().getSimpleName();
				if (t instanceof TSeparator) {
					sb.append(t.getText());
				} else if (t instanceof TAnyChar) {
					boolean before = sb.length() > 0
							&& Character.isLetter(sb.charAt(sb.length() - 1));
					if (before && "ascii".equals(target)) {
						sb.append(' ');
					}
					sb.append(t.getText());
				} else {
					String translated = "";
					Translation translation = m.get(key);
					if ("unicode".equals(target)) {
						translated = translation.getUnicode();
					}
					if ("ascii".equals(target)) {
						boolean before = (last != null && last instanceof TAnyChar)
								|| sb.length() > 0
								&& (Character
										.isLetter(sb.charAt(sb.length() - 1)));
						translated = translation.getAscii(before);
					}
					sb.append(translated);
				}
				last = t;
			}
		} catch (LexerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
