package de.be4.classicalb.core.rules.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.node.Start;

public class RulesProject extends AbstractProject {
	private final File mainFile;
	private ParsingBehaviour parsingBehaviour;

	public static int parseProject(final File mainFile, final ParsingBehaviour parsingBehaviour, final PrintStream out,
			final PrintStream err) throws FileNotFoundException, IOException {
		RulesProject project = new RulesProject(mainFile);
		project.setParsingBehaviour(parsingBehaviour);
		project.parseProject();
		project.flattenProject();
		return project.printPrologOutput(out, err);
	}

	public RulesProject(File mainFile) {
		this.mainFile = mainFile;
	}

	public void setParsingBehaviour(final ParsingBehaviour parsingBehaviour) {
		this.parsingBehaviour = parsingBehaviour;
	}

	public void flattenProject() {
		if (projectHasErrors()) {
			return;
		}

		final String compositionName = "Composition";
		final BMachine compositionMachine = new BMachine(compositionName, new File(compositionName + ".mch"));
		compositionMachine.addExternalFunctions();
		MachineInjector injector = new MachineInjector(compositionMachine.getStart());
		final List<String> promotesList = new ArrayList<>();
		for (int i = 0; i < bModels.size(); i++) {
			RulesParseUnit rulesParseUnit = (RulesParseUnit) bModels.get(i);
			promotesList.addAll(rulesParseUnit.getRules());
			promotesList.addAll(rulesParseUnit.getComputations());
			Start otherStart = rulesParseUnit.getStart();
			injector.injectMachine(otherStart);
		}
		compositionMachine.setParsingBehaviour(this.parsingBehaviour);
		bModels.add(compositionMachine);
		final String mainMachineName = "Main";
		BMachine mainMachine = new BMachine(mainMachineName, new File(mainMachineName + ".mch"));
		mainMachine.addPreferenceDefinition("SET_PREF_ALLOW_LOCAL_OPERATION_CALLS", true);
		mainMachine.addIncludesClause(compositionName);
		mainMachine.addPromotesClause(promotesList);
		bModels.add(mainMachine);
	}

	public void parseProject() throws FileNotFoundException, IOException {
		final IModel mainModel = parseMainFile();
		bModels.add(mainModel);
		final LinkedList<Reference> fifo = new LinkedList<>(mainModel.getMachineReferences());
		while (!fifo.isEmpty()) {
			final Reference modelReference = fifo.pollFirst();
			if (isANewModel(modelReference)) {
				final IModel bModel = lookupReference(modelReference);
				bModels.add(bModel);
				fifo.addAll(bModel.getMachineReferences());
			}

		}
	}

	private IModel lookupReference(Reference reference) throws IOException {
		File file = reference.getFile();
		try {
			RulesParseUnit unit = new RulesParseUnit();
			unit.setParsingBehaviour(this.parsingBehaviour);
			unit.readMachineFromFile(file);
			unit.parse();
			if (!unit.hasError()) {
				final int fileNumber = bModels.size() + 1;
				Start start = unit.getStart();
				nodeIds.assignIdentifiers(fileNumber, start);
			}
			return unit;
		} catch (IOException e) {
			throw new RuntimeException();
		}
	};

	private IModel parseMainFile() throws FileNotFoundException, IOException {
		RulesParseUnit bParseUnit = new RulesParseUnit();
		bParseUnit.readMachineFromFile(mainFile);
		bParseUnit.setParsingBehaviour(this.parsingBehaviour);
		bParseUnit.parse();
		Start start = bParseUnit.getStart();
		if (start != null) {
			final int fileNumber = bModels.size() + 1;
			nodeIds.assignIdentifiers(fileNumber, start);
		}
		return bParseUnit;
	};

	protected boolean isANewModel(Reference reference) {
		for (IModel iModel : bModels) {
			if (iModel.getMachineName().equals(reference.getName())) {
				return false;
			}
		}
		return true;
	}

}
