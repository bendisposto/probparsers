package de.be4.classicalb.core.parser.rules;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.hhu.stups.sablecc.patch.SourcePosition;

public class CTagsGenerator {

	private static final String OPERATION_TYPE = "operation";
	private static final String IDENTIFIER_TYPE = "identifier";

	private CTagsGenerator() {
		// class only contains static methods
	}

	public static void generateCtagsFile(RulesProject project, File ctagsFile) {
		try {
			ctagsFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		List<CTagsEntry> list = new ArrayList<>();
		list.addAll(createFromOperations(project.getOperationsMap().values()));
		list.addAll(createFromConstants(project));

		try (FileWriter fw = new FileWriter(ctagsFile);) {
			for (CTagsEntry cTagsEntry : list) {
				fw.write(cTagsEntry.toString());
				fw.write(System.lineSeparator());
			}
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			// do nothing
		}
	}

	private static Collection<? extends CTagsEntry> createFromConstants(RulesProject project) {
		List<CTagsEntry> list = new ArrayList<>();
		for (IModel model : project.bModels) {
			if (model instanceof RulesParseUnit) {
				RulesParseUnit parseUnit = (RulesParseUnit) model;
				if (null != parseUnit.getRulesMachineChecker()) {
					for (TIdentifierLiteral literal : parseUnit.getRulesMachineChecker().getGlobalIdentifiers()) {
						list.add(new CTagsEntry(literal.getText(), parseUnit.getFile().getAbsolutePath(),
								literal.getStartPos(), IDENTIFIER_TYPE));
					}
				}
			}
		}
		return list;
	}

	private static List<CTagsEntry> createFromOperations(Collection<AbstractOperation> values) {
		List<CTagsEntry> list = new ArrayList<>();
		for (AbstractOperation operation : values) {
			list.add(new CTagsEntry(operation.getName(), operation.getFileName(),
					operation.getNameLiteral().getStartPos(), OPERATION_TYPE));
		}
		return list;
	}

	static class CTagsEntry {

		private final SourcePosition srcPos;
		private final String file;
		private final String name;
		private final String type;

		CTagsEntry(String name, String file, SourcePosition sourcePosition, String type) {
			this.name = name;
			this.file = file;
			this.srcPos = sourcePosition;
			this.type = type;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(name).append("\t");
			sb.append(file).append("\t");
			sb.append("/^");
			for (int i = 1; i < srcPos.getPos(); i++) {
				sb.append(" ");
			}
			sb.append(name);
			sb.append("$/;\"\t");
			sb.append(type).append("\t");
			sb.append("line:").append(srcPos.getLine()).append("\t");
			sb.append("language:brules");
			return sb.toString();
		}

	}
}
