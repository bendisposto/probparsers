package de.be4.classicalb.core.parser.rules;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.util.PrettyPrinter;

public class RulesUtil {

	public static String getRulesProjectAsPrologTerm(final String content) {
		RulesProject rulesProject = new RulesProject();
		ParsingBehaviour pb = new ParsingBehaviour();
		pb.setAddLineNumbers(false);
		rulesProject.setParsingBehaviour(pb);
		rulesProject.parseRulesMachines(content, new String[] {});
		rulesProject.checkAndTranslateProject();
		List<IModel> bModels = rulesProject.getBModels();
		List<BException> bExceptionList = rulesProject.getBExceptionList();
		if (bExceptionList.isEmpty()) {
			IModel model = bModels.get(bModels.size() - 2);
			PrettyPrinter pp = new PrettyPrinter();
			model.getStart().apply(pp);
			System.out.println(pp.getPrettyPrint());
		}

		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			public String toString() {
				return this.string.toString();
			}
		};
		rulesProject.printPrologOutput(new PrintStream(output), new PrintStream(output));
		return output.toString();
	}

	public static String getRulesMachineAsBMachine(File file) {
		RulesProject rulesProject = new RulesProject();
		rulesProject.parseProject(file);
		rulesProject.checkAndTranslateProject();
		List<IModel> bModels = rulesProject.getBModels();
		List<BException> bExceptionList = rulesProject.getBExceptionList();
		if (!bExceptionList.isEmpty()) {
			throw new RuntimeException(bExceptionList.get(0));
		}
		IModel model = bModels.get(bModels.size() - 2);
		PrettyPrinter pp = new PrettyPrinter();
		model.getStart().apply(pp);
		return pp.getPrettyPrint();
	}

	public static String getRulesMachineAsBMachine(final String content) {
		RulesProject rulesProject = new RulesProject();
		rulesProject.parseRulesMachines(content, new String[] {});
		rulesProject.checkAndTranslateProject();
		List<IModel> bModels = rulesProject.getBModels();
		List<BException> bExceptionList = rulesProject.getBExceptionList();
		if (!bExceptionList.isEmpty()) {
			throw new RuntimeException(bExceptionList.get(0));
		}
		IModel model = bModels.get(bModels.size() - 2);
		PrettyPrinter pp = new PrettyPrinter();
		model.getStart().apply(pp);
		return pp.getPrettyPrint();
	}

	public static String getFileAsPrologTerm(final String file) {
		return getFileAsPrologTerm(file, false);
	}

	public static String getFileAsPrologTerm(final String file, boolean addLineNumbers) {
		ParsingBehaviour pb = new ParsingBehaviour();
		pb.setAddLineNumbers(addLineNumbers);
		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			public String toString() {
				return this.string.toString();
			}
		};
		RulesProject.parseProject(new File(file), pb, new PrintStream(output), new PrintStream(output));
		return output.toString();
	}

	public static String getRulesMachineAsPrologTerm(final String content) {
		RulesParseUnit unit = new RulesParseUnit();
		unit.setMachineAsString(content);
		ParsingBehaviour pb = new ParsingBehaviour();
		pb.setAddLineNumbers(false);
		unit.setParsingBehaviour(pb);
		unit.parse();
		unit.translate();

		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			public String toString() {
				return this.string.toString();
			}
		};
		unit.printPrologOutput(new PrintStream(output), new PrintStream(output));
		return output.toString();
	}

}
