package de.prob.parser.antlr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.antlr.v4.runtime.ParserRuleContext;

import de.prob.parser.ast.SourceCodePosition;

public class Util {

	public static final String readFile(final File machine) throws FileNotFoundException, IOException {
		final InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(machine));

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

	public static SourceCodePosition createSourceCodePosition(ParserRuleContext ctx) {
		SourceCodePosition sourceCodePosition = new SourceCodePosition();
		sourceCodePosition.setText(ctx.getText());
		sourceCodePosition.setStartLine(ctx.getStart().getLine());
		sourceCodePosition.setStartColumn(ctx.getStart().getCharPositionInLine());
		return sourceCodePosition;
	}
	

}
