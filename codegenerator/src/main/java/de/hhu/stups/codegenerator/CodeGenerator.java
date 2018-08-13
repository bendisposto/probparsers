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

	/*
	* Just a main function for generating an exampe
	*/
	public static void main(String[] args) throws URISyntaxException, CodeGenerationException {
		CodeGenerator codeGenerator = new CodeGenerator();
		codeGenerator.generate(Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/testfiles/project1/A.mch").toURI()), GeneratorMode.JAVA, true);
	}

	/*
	* This function generates code from a given path for a machine, the target language and the information whether it is a main machine of a project
	*/
	public Set<Path> generate(Path path, GeneratorMode mode, boolean isMain) throws CodeGenerationException {
		if(isMain) {
			paths.clear();
		}
		BProject project = parseProject(path);
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

	/*
	* This function generates code for a targeted programming language with creating the belonging file
	*/
	private Path writeToFile(Path path, GeneratorMode mode, MachineNode node) {
		MachineGenerator generator = new MachineGenerator(mode);
		String code = generator.generateMachine(node);

		int lastIndexDot = path.toString().lastIndexOf(".");
		int lastIndexSlash = path.toString().lastIndexOf("/");

		String fileName = path.toString().substring(lastIndexSlash + 1, lastIndexDot);
		Path newPath = Paths.get(path.toString().substring(0, lastIndexSlash + 1) + generator.getNameHandler().handle(fileName) + "." + mode.name().toLowerCase());
		try {
			return Files.write(newPath, code.getBytes(), Files.exists(newPath) ? TRUNCATE_EXISTING : CREATE_NEW);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/*
	* This function executes parsing and semantic checkings on a project
	*/
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

}
