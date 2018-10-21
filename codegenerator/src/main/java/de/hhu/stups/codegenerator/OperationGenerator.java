package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.nodes.OperationNode;
import de.prob.parser.ast.types.BType;
import de.prob.parser.ast.types.UntypedType;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class OperationGenerator {

    /*
    * Enum for identifying whether the given declaration is a local declaration or a parameter
    */
    public enum DeclarationType {
        LOCAL_DECLARATION,
        PARAMETER
    }

    private final STGroup group;

    private final MachineGenerator machineGenerator;

    private final DeclarationGenerator declarationGenerator;

    private final IdentifierGenerator identifierGenerator;

    private final NameHandler nameHandler;

    private final SubstitutionGenerator substitutionGenerator;

    private final TypeGenerator typeGenerator;

    private final Map<String, String> machineFromOperation;


    public OperationGenerator(final STGroup group, final MachineGenerator machineGenerator, final SubstitutionGenerator substitutionGenerator,
                              final DeclarationGenerator declarationGenerator, final IdentifierGenerator identifierGenerator,
                              final NameHandler nameHandler, final TypeGenerator typeGenerator) {
        this.group = group;
        this.machineGenerator = machineGenerator;
        this.declarationGenerator = declarationGenerator;
        this.substitutionGenerator = substitutionGenerator;
        this.substitutionGenerator.setOperationGenerator(this);
        this.identifierGenerator = identifierGenerator;
        this.nameHandler = nameHandler;
        this.typeGenerator = typeGenerator;
        this.machineFromOperation = new HashMap<>();
    }

    /*
    * This function maps operations to machines for identifying the included machine where the operation is called from.
    */
    public void mapOperationsToMachine(MachineNode node) {
        node.getMachineReferences()
                .forEach(reference -> reference.getMachineNode().getOperations()
                        .forEach(operation -> machineFromOperation.put(operation.getName(), reference.getMachineName())));
    }

    /*
    * This function generates code for all operations in a machine.
    */
    public List<String> visitOperations(List<OperationNode> operations) {
        return operations.stream()
                .map(this::visitOperation)
                .collect(Collectors.toList());
    }

    /*
    * This function generates code for one operation with the given AST node for an operation.
    */
    private String visitOperation(OperationNode node) {
        identifierGenerator.setParams(node.getParams(), node.getOutputParams());
        substitutionGenerator.resetParallel();
        ST operation = generate(node);
        operation.add("machine", nameHandler.handle(machineGenerator.getMachineName()));
        operation.add("body", machineGenerator.visitSubstitutionNode(node.getSubstitution(), null));
        return operation.render();
    }


    /*
    * This function generates code for an operation from the given AST node
    */
    private ST generate(OperationNode node) {
        ST operation = group.getInstanceOf("operation");

        operation.add("locals", declarationGenerator.generateDeclarations(node.getOutputParams()
                .stream()
                .collect(Collectors.toList()), DeclarationType.LOCAL_DECLARATION, false));

        if(node.getOutputParams().size() == 1) {
            BType type = node.getOutputParams().get(0).getType();
            String identifier = node.getOutputParams().get(0).getName();
            operation.add("returnType", typeGenerator.generate(type, false));
            operation.add("isTyped", true);
            operation.add("return", group.getInstanceOf("return").add("identifier", nameHandler.handleIdentifier(identifier, NameHandler.IdentifierHandlingEnum.MACHINES)).render());
        } else if(node.getOutputParams().size() == 0) {
            operation.add("isTyped", false);
            operation.add("returnType", typeGenerator.generate(new UntypedType(), false));
            operation.add("return", group.getInstanceOf("no_return").render());
        }
        operation.add("operationName", nameHandler.handle(node.getName()));
        operation.add("parameters", declarationGenerator.generateDeclarations(node.getParams(), DeclarationType.PARAMETER, false));
        operation.add("returnParameters", declarationGenerator.generateDeclarations(node.getOutputParams(), DeclarationType.PARAMETER, true));
        return operation;
    }

    public Map<String, String> getMachineFromOperation() {
        return machineFromOperation;
    }
}
