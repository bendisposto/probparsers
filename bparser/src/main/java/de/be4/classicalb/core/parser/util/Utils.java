package de.be4.classicalb.core.parser.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AImplementationMachineParseUnit;
import de.be4.classicalb.core.parser.node.ARefinementMachineParseUnit;
import de.be4.classicalb.core.parser.node.PParseUnit;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.hhu.stups.sablecc.patch.SourcePosition;

public class Utils {

	public static String getAIdentifierAsString(AIdentifierExpression idExpr) {
		return getTIdentifierListAsString(idExpr.getIdentifier());
	}

	public static String getTIdentifierListAsString(final List<TIdentifierLiteral> idElements) {
		final String string;
		if (idElements.size() == 1) {
			// faster version for the simple case
			string = idElements.get(0).getText();
		} else {
			final StringBuilder idName = new StringBuilder();

			boolean first = true;
			for (final TIdentifierLiteral e : idElements) {
				if (first) {
					first = false;
				} else {
					idName.append('.');
				}
				idName.append(e.getText());
			}
			string = idName.toString();
		}
		return string.trim();
	}

	public static boolean isCompleteMachine(final Start rootNode) {
		final PParseUnit parseUnit = rootNode.getPParseUnit();

		if (parseUnit instanceof AAbstractMachineParseUnit || parseUnit instanceof ARefinementMachineParseUnit
				|| parseUnit instanceof AImplementationMachineParseUnit) {
			return true;
		}

		return false;
	}

	public static String getRevisionFromManifest() {
		String revision = "";
		InputStream stream = Utils.class.getClassLoader().getResourceAsStream("revision.properties");
		Properties properties = new Properties();
		try {
			properties.load(stream);
			stream.close();
			revision = properties.getProperty("CompileDate");
		} catch (IOException e) {
			revision = String.valueOf(System.currentTimeMillis());
		}
		return revision;
	}

	public static String getSourcePositionAsString(SourcePosition sourcePos) {
		return "[" + sourcePos.getLine() + "," + sourcePos.getPos() + "]";
	}

	public static <T> List<T> sortByTopologicalOrder(final Map<T, Set<T>> dependencies) {
		final Set<T> allValues = new HashSet<>(dependencies.keySet());
		ArrayList<T> sortedList = new ArrayList<T>();
		boolean newRun = true;
		while (newRun) {
			newRun = false;
			final ArrayList<T> todo = new ArrayList<>(allValues);
			todo.removeAll(sortedList);
			for (T element : todo) {
				Set<T> deps = new HashSet<>(dependencies.get(element));
				deps.removeAll(sortedList);
				if (deps.isEmpty()) {
					sortedList.add(element);
					newRun = true;
				}
			}
		}
		return sortedList;
	}

	public static <T> List<T> determineCycle(final Set<T> remaining, final Map<T, Set<T>> dependencies) {
		ArrayList<T> cycle = new ArrayList<T>();
		Set<T> set = new HashSet<T>(remaining);
		boolean newRun = true;
		while (newRun) {
			for (T next : set) {
				if (cycle.contains(next)) {
					newRun = false;
					cycle.add(next);
					break;
				} else if (remaining.contains(next)) {
					cycle.add(next);
					set = new HashSet<>(dependencies.get(next));
					break;
				}
			}
		}
		return cycle;
	}

	public static String getFileWithoutExtension(String f) {
		String res = null;
		int i = f.lastIndexOf('.');
		if (i > 0 && i < f.length() - 1) {
			res = f.substring(0, i);
		} else {
			// there is no file name extension
			res = f;
		}
		return res;
	}

	public static final String readFile(final File filePath) throws FileNotFoundException, IOException {
		final InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath),
				Charset.forName("UTF-8"));

		final StringBuilder builder = new StringBuilder();
		final char[] buffer = new char[1024];
		int read;
		while ((read = inputStreamReader.read(buffer)) >= 0) {
			builder.append(String.valueOf(buffer, 0, read));
		}
		String content = builder.toString();

		inputStreamReader.close();

		// remove utf-8 byte order mark
		// replaceAll \uFEFF did not work for some reason
		// apparently, unix like systems report a single character with the code
		// below
		if (!content.isEmpty() && Character.codePointAt(content, 0) == 65279) {
			content = content.substring(1);
		}
		// while windows splits it up into three characters with the codes below
		if (!content.isEmpty() && Character.codePointAt(content, 0) == 239 && Character.codePointAt(content, 1) == 187
				&& Character.codePointAt(content, 2) == 191) {
			content = content.substring(3);
		}

		return content.replaceAll("\r\n", "\n");
	}

}
