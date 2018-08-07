package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.OperationNode;
import de.prob.parser.ast.types.BType;
import de.prob.parser.ast.types.UntypedType;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.List;
import java.util.stream.Collectors;


public class OperationGenerator {

    public enum DeclarationType {
        LOCAL_DECLARATION,
        PARAMETER
    }

    private final STGroup group;

    private final NameHandler nameHandler;

    private final TypeGenerator typeGenerator;


    public OperationGenerator(final STGroup group, final NameHandler nameHandler, final TypeGenerator typeGenerator) {
        this.group = group;
        this.nameHandler = nameHandler;
        this.typeGenerator = typeGenerator;
    }

    public ST generate(OperationNode node, List<DeclarationNode> globals) {
        ST operation = group.getInstanceOf("operation");

        operation.add("locals", generateDeclarations(node.getOutputParams()
                .stream()
                .filter(output -> !globals.stream()
                        .map(DeclarationNode::getName)
                        .collect(Collectors.toList()).contains(output.getName()))
                .collect(Collectors.toList()), DeclarationType.LOCAL_DECLARATION, false));

        if(node.getOutputParams().size() == 1) {
            BType type = node.getOutputParams().get(0).getType();
            String identifier = node.getOutputParams().get(0).getName();
            operation.add("returnType", typeGenerator.generate(type, false));
            operation.add("return", group.getInstanceOf("return").add("identifier", nameHandler.handleIdentifier(identifier, NameHandler.IdentifierHandlingEnum.MACHINES)).render());
        } else if(node.getOutputParams().size() == 0) {
            operation.add("returnType", typeGenerator.generate(new UntypedType(), false));
        }
        operation.add("operationName", nameHandler.handle(node.getName()));
        operation.add("parameters", generateDeclarations(node.getParams(), DeclarationType.PARAMETER, false));
        operation.add("returnParameters", generateDeclarations(node.getOutputParams(), DeclarationType.PARAMETER, true));
        return operation;
    }

    public List<String> generateDeclarations(List<DeclarationNode> declarations, DeclarationType type, boolean isReturn) {
        return declarations.stream()
                .map(declaration -> type == DeclarationType.LOCAL_DECLARATION ?
                        generateLocalDeclaration(declaration) : generateParameter(declaration, isReturn))
                .collect(Collectors.toList());
    }

    private String generateLocalDeclaration(DeclarationNode node) {
        ST declaration = group.getInstanceOf("local_declaration");
        declaration.add("type", typeGenerator.generate(node.getType(), false));
        declaration.add("identifier", nameHandler.handleIdentifier(node.getName(), NameHandler.IdentifierHandlingEnum.MACHINES));
        return declaration.render();
    }

    private String generateParameter(DeclarationNode node, boolean isReturn) {
        ST declaration = group.getInstanceOf("parameter");
        declaration.add("isReturn", isReturn);
        declaration.add("type", typeGenerator.generate(node.getType(), false));
        declaration.add("identifier", nameHandler.handleIdentifier(node.getName(), NameHandler.IdentifierHandlingEnum.MACHINES));
        return declaration.render();
    }

}
