package de.be4.eventbalg.core.parser;

import java.util.List;

import de.be4.eventbalg.core.parser.node.AContextParseUnit;
import de.be4.eventbalg.core.parser.node.AMachineParseUnit;
import de.be4.eventbalg.core.parser.node.PParseUnit;
import de.be4.eventbalg.core.parser.node.Start;
import de.be4.eventbalg.core.parser.node.TIdentifierLiteral;

public class Utils {

	public static String getIdentifierAsString(
			final List<TIdentifierLiteral> idElements) {
		final StringBuilder idName = new StringBuilder();

		for (final TIdentifierLiteral e : idElements) {
			idName.append(e.getText());
			idName.append('.');
		}

		if (idElements.size() > 0) {
			idName.deleteCharAt(idName.length() - 1);
		}

		return idName.toString().trim();
	}

	public static boolean isCompleteMachine(final Start rootNode) {
		if (isMachine(rootNode) || isContext(rootNode)) {
			return true;
		}

		return false;
	}

	public static boolean isMachine(final Start rootNode) {
		final PParseUnit parseUnit = rootNode.getPParseUnit();

		if (parseUnit instanceof AMachineParseUnit) {
			return true;
		}

		return false;
	}

	public static boolean isContext(final Start rootNode) {
		final PParseUnit parseUnit = rootNode.getPParseUnit();

		if (parseUnit instanceof AContextParseUnit) {
			return true;
		}

		return false;
	}
}
