package de.hhu.stups.codegenerator;

import de.prob.parser.antlr.Antlr4BParser;
import de.prob.parser.antlr.BProject;
import de.prob.parser.antlr.ScopeException;
import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.nodes.MachineReferenceNode;
import de.prob.parser.ast.visitors.TypeErrorException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class CodeGenerator {

	private Set<Path> paths = new HashSet<>();

	public static void main(String[] args) throws URISyntaxException, CodeGenerationException {
		CodeGenerator codeGenerator = new CodeGenerator();
		codeGenerator.generate(Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/testfiles/Sieve.mch").toURI()), GeneratorMode.JAVA, true);
	}

	public Set<Path> generate(Path path, GeneratorMode mode, boolean isMain) throws CodeGenerationException {
		if(isMain) {
			paths.clear();
		}
		BProject project = parseProject(path);
		checkProject(project);
		for(MachineReferenceNode referenceNode : project.getMainMachine().getMachineReferences()) {
			String[] pathAsList = path.toString().split("/");
			pathAsList[pathAsList.length - 1] = pathAsList[pathAsList.length - 1].replaceAll(project.getMainMachine().getName(), referenceNode.getMachineName());
			Path currentPath = Paths.get(String.join("/", pathAsList));
			if(!paths.contains(currentPath)) {
				generate(currentPath, mode, false);
			}
		}
		paths.add(writeToFile(path, mode, project.getMainMachine()));
		return paths;
	}

	private Path writeToFile(Path path, GeneratorMode mode, MachineNode node) {
		MachineGenerator generator = new MachineGenerator(mode);
		String code = generator.generateMachine(node);

		int lastIndexDot = path.toString().lastIndexOf(".");
		Path newPath = Paths.get(path.toString().substring(0, lastIndexDot + 1) + mode.name().toLowerCase());
		try {
			return Files.write(newPath, code.getBytes(), Files.exists(newPath) ? TRUNCATE_EXISTING : CREATE_NEW);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private BProject parseProject(Path path) throws CodeGenerationException {
		BProject project;
		try {
			project = Antlr4BParser.createBProjectFromMainMachineFile(path.toFile());
		} catch (TypeErrorException | ScopeException | IOException e) {
			e.printStackTrace();
			throw new CodeGenerationException(e.getMessage());
		}
		return project;
	}

	private void checkProject(BProject project) throws CodeGenerationException {
		checkMachine(project.getMainMachine());
		for(MachineReferenceNode referenceNode : project.getMainMachine().getMachineReferences()) {
			checkMachine(referenceNode.getMachineNode());
		}
	}

	private void checkMachine(MachineNode machine) throws CodeGenerationException {
		CodeGenerationChecker codeGenerationChecker = new CodeGenerationChecker(machine);
		codeGenerationChecker.check();
		if (codeGenerationChecker.getErrors().size() > 0) {
			throw new CodeGenerationException(String.join("\n", codeGenerationChecker.getErrors()));
		}
	}

}
