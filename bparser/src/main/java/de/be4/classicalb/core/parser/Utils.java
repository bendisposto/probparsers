package de.be4.classicalb.core.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
import de.be4.classicalb.core.parser.node.AImplementationMachineParseUnit;
import de.be4.classicalb.core.parser.node.ARefinementMachineParseUnit;
import de.be4.classicalb.core.parser.node.PParseUnit;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;

public class Utils {

	public static String getIdentifierAsString(
			final List<TIdentifierLiteral> idElements) {
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

		if (parseUnit instanceof AAbstractMachineParseUnit
				|| parseUnit instanceof ARefinementMachineParseUnit
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
		}
		catch (IOException e) {
			revision = String.valueOf(System.currentTimeMillis());
		}
		return revision;
	}
}
