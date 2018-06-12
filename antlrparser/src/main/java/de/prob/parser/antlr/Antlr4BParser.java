package de.prob.parser.antlr;

import de.prob.parser.ast.nodes.MachineReferenceNode;
import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.visitors.MachineScopeChecker;
import de.prob.parser.ast.visitors.TypeChecker;
import de.prob.parser.ast.visitors.TypeErrorException;
import de.prob.parser.util.Utils;
import files.BLexer;
import files.BParser;
import files.BParser.StartContext;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DiagnosticErrorListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Antlr4BParser {

	public static MachineNode createSemanticAST(String input) throws TypeErrorException, ScopeException {
		StartContext tree = parse(input);
		AstCreator astCreator = new AstCreator(tree);
		MachineNode machineNode = astCreator.getMachineNode();
		new MachineScopeChecker(machineNode);
		TypeChecker.typecheckMachineNode(machineNode);
		return machineNode;
	}

	public static BProject createProject(String input, String... machines) throws TypeErrorException, ScopeException {
		StartContext tree = parse(input);
		AstCreator astCreator = new AstCreator(tree);
		MachineNode main = astCreator.getMachineNode();
		List<MachineNode> machineNodeList = new ArrayList<>();
		machineNodeList.add(main);

		for (String string : machines) {
			StartContext tree2 = parse(string);
			AstCreator astCreator2 = new AstCreator(tree2);
			MachineNode mNode = astCreator2.getMachineNode();
			machineNodeList.add(mNode);
		}

		// determine machine order
		sortMachineNodes(machineNodeList);

		for (int i = machineNodeList.size() - 1; i >= 0; i--) {
			new MachineScopeChecker(machineNodeList.get(i));
		}

		for (int i = machineNodeList.size() - 1; i >= 0; i--) {
			MachineNode machineNode = machineNodeList.get(i);
			TypeChecker.typecheckMachineNode(machineNode);
		}
		return new BProject(machineNodeList);
	}

	private static void sortMachineNodes(List<MachineNode> machineNodeList) {
		final Map<String, MachineNode> machineNodeMap = new HashMap<>();
		for (MachineNode machineNode : machineNodeList) {
			machineNodeMap.put(machineNode.getName(), machineNode);
		}
		Map<String, Set<String>> dependencies = new HashMap<>();
		determineMachineDependencies(machineNodeList.get(0), machineNodeMap, dependencies, new ArrayList<>());
		List<String> machineNameList = Utils.sortByTopologicalOrder(dependencies);

		machineNodeList.clear();
		for (String machineName : machineNameList) {
			machineNodeList.add(machineNodeMap.get(machineName));
		}
	}

	private static void determineMachineDependencies(final MachineNode machineNode,
			final Map<String, MachineNode> machineNodes, final Map<String, Set<String>> dependencies,
			final List<String> ancestors) {
		final String name = machineNode.getName();
		ancestors.add(name);

		final Set<String> set = new HashSet<>();
		for (MachineReferenceNode machineReferenceNode : machineNode.getMachineReferences()) {
			final String refName = machineReferenceNode.getMachineName();
			if (ancestors.contains(refName)) {
				throw new RuntimeException("Cycle detected");
			}
			final MachineNode refMachineNode = machineNodes.get(refName);
			machineReferenceNode.setMachineNode(refMachineNode);
			determineMachineDependencies(refMachineNode, machineNodes, dependencies, new ArrayList<>(ancestors));
			set.addAll(dependencies.get(refName));
		}
		dependencies.put(name, set);

	}

	public static StartContext parse(String bString) {
		CodePointCharStream charStream = CharStreams.fromString(bString);

		BLexer lexer = new BLexer(charStream);
		// MyLexer myLexer = new MyLexer(fromString);

		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		// BLexer.rulesGrammar = true;
		// create a parser that feeds off the tokens buffer

		BParser parser = new BParser(tokens);
		// RulesGrammar parser = new RulesGrammar(tokens);

		// parser.addErrorListener(new MyErrorListener());
		// parser.removeErrorListeners();
		parser.addErrorListener(new DiagnosticErrorListener());
		MyErrorListener myErrorListener = new MyErrorListener();
		parser.addErrorListener(myErrorListener);
		StartContext tree = null;

		tree = parser.start();

		// begin parsing at start rule
		// if (myErrorListener.exception != null) {
		// throw new RuntimeException(myErrorListener.exception);
		// }

		// System.out.println(tree.toStringTree(parser)); // print LISP-style
		// tree

		// PragmaListener pragmaListener = new PragmaListener(tokens);
		// ParseTreeWalker walker = new ParseTreeWalker();
		// walker.walk(pragmaListener, tree);

		// MyTreeListener listener = new MyTreeListener();
		// ParseTreeWalker walker2 = new ParseTreeWalker();
		// walker.walk(listener, tree);
		// System.out.println("-------------");

		return tree;
	}

}
