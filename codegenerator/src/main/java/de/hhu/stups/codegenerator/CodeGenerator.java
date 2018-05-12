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

    public static void main(String[] args) throws URISyntaxException {
        generate(Paths.get(CodeGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/testfiles/AbstractMachine3.mch").toURI()),GeneratorMode.JAVA);
    }

    public static void generate(Path path, GeneratorMode mode) {
        String file = "";
        MachineNode node = null;
        try {
            file = new String(Files.readAllBytes(path));
            node = Antlr4BParser.createSemanticAST(file);
        } catch(TypeErrorException | IOException e) {
            e.printStackTrace();
        }
        String code = new MachineGenerator(mode).generateMachine(node);
        int lastIndexDot = path.toString().lastIndexOf(".");
        Path newPath = Paths.get(path.toString().substring(0, lastIndexDot + 1) + mode.name().toLowerCase());
        try {
            Files.write(newPath, code.getBytes(), Files.exists(newPath) ? TRUNCATE_EXISTING : CREATE_NEW);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
