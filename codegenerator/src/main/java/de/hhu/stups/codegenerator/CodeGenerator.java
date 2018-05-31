package de.hhu.stups.codegenerator;

import de.prob.parser.antlr.Antlr4BParser;
import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.visitors.TypeErrorException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;


public class CodeGenerator {

    public static void main(String[] args) throws URISyntaxException, CodeGenerationException {
        generate(Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/testfiles/AbstractMachine4.mch").toURI()),GeneratorMode.JAVA);
    }

    public static void generate(Path path, GeneratorMode mode) throws CodeGenerationException {
        String file = "";
        MachineNode node = null;
        try {
            file = new String(Files.readAllBytes(path));
            node = Antlr4BParser.createSemanticAST(file);
        } catch(TypeErrorException | IOException e) {
            e.printStackTrace();
        }
        MachineGenerator generator = new MachineGenerator(mode);
        String code = generator.generateMachine(node);

        if(generator.getErrors().size() > 0) {
            throw new CodeGenerationException(String.join("\n", generator.getErrors()));
        }

        int lastIndexDot = path.toString().lastIndexOf(".");
        Path newPath = Paths.get(path.toString().substring(0, lastIndexDot + 1) + mode.name().toLowerCase());
        try {
            Files.write(newPath, code.getBytes(), Files.exists(newPath) ? TRUNCATE_EXISTING : CREATE_NEW);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
