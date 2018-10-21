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

    /*
    * Enum for identifying whether the given declaration is a local declaration or a parameter
    */
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


    /*
    * This function generates code for an operation from the given AST node
    */
    public ST generate(OperationNode node) {
        ST operation = group.getInstanceOf("operation");

        operation.add("locals", generateDeclarations(node.getOutputParams()
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
        operation.add("parameters", generateDeclarations(node.getParams(), DeclarationType.PARAMETER, false));
        operation.add("returnParameters", generateDeclarations(node.getOutputParams(), DeclarationType.PARAMETER, true));
        return operation;
    }

    /*
    * This function generates code for a list of local declarations or parameters in the generated code
    */
    public List<String> generateDeclarations(List<DeclarationNode> declarations, DeclarationType type, boolean isReturn) {
        return declarations.stream()
                .map(declaration -> type == DeclarationType.LOCAL_DECLARATION ?
                        generateLocalDeclaration(declaration) : generateParameter(declaration, isReturn))
                .collect(Collectors.toList());
    }

    /*
    * This function generates code for a local declaration with the given node from the AST
    */
    public String generateLocalDeclaration(DeclarationNode node) {
        ST declaration = group.getInstanceOf("local_declaration");
        declaration.add("type", typeGenerator.generate(node.getType(), false));
        declaration.add("identifier", nameHandler.handleIdentifier(node.getName(), NameHandler.IdentifierHandlingEnum.MACHINES));
        return declaration.render();
    }

    /*
    * This function generates code for a parameter with the given node from the AST and the information whether it is an output parameter
    */
    private String generateParameter(DeclarationNode node, boolean isReturn) {
        ST declaration = group.getInstanceOf("parameter");
        declaration.add("isReturn", isReturn);
        declaration.add("type", typeGenerator.generate(node.getType(), false));
        declaration.add("identifier", nameHandler.handleIdentifier(node.getName(), NameHandler.IdentifierHandlingEnum.MACHINES));
        return declaration.render();
    }

}
