package de.prob.tmparser;

import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.Collection;

import de.prob.core.theorymapping.lexer.Lexer;
import de.prob.core.theorymapping.lexer.LexerException;
import de.prob.core.theorymapping.node.Start;
import de.prob.core.theorymapping.parser.Parser;
import de.prob.core.theorymapping.parser.ParserException;
import de.prob.tmparser.internal.MappingVisitor;

public class TheoryMappingParser {
	static Collection<OperatorMapping> parseTheoryMapping(String theoryName,
			String filename) throws TheoryMappingException, IOException {
		final Reader input = new FileReader(filename);
		return parseTheoryMapping(theoryName, input);
	}

	static Collection<OperatorMapping> parseTheoryMapping(String theoryName,
			Reader input) throws TheoryMappingException, IOException {
		Start ast;
		try {
			ast = parse(input);
		} catch (ParserException e) {
			throw new TheoryMappingException(e);
		} catch (LexerException e) {
			throw new TheoryMappingException(e);
		}
		return extractMappings(ast, theoryName);
	}

	static private Start parse(Reader input) throws ParserException,
			LexerException, IOException {
		final Lexer lexer = new Lexer(new PushbackReader(input));
		final Parser parser = new Parser(lexer);
		return parser.parse();
	}

	static private Collection<OperatorMapping> extractMappings(Start ast,
			String theoryName) {
		MappingVisitor visitor = new MappingVisitor(theoryName);
		ast.apply(visitor);
		return visitor.getMappings();
	}

}
