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

    public ST generate(OperationNode node, List<String> variables) {
        ST operation = group.getInstanceOf("operation");
        operation.add("locals", generateDeclarations(node.getOutputParams(), DeclarationType.LOCAL_DECLARATION, variables));

        if(node.getOutputParams().size() == 1) {
            BType type = node.getOutputParams().get(0).getType();
            String identifier = node.getOutputParams().get(0).getName();
            //TODO
            operation.add("returnParameters", (node.getParams().size() > 0 ? ", " : "") + typeGenerator.generate(type, variables, false) + "* " + identifier);
            operation.add("returnType", typeGenerator.generate(type, variables, false));
            operation.add("return", group.getInstanceOf("return").add("identifier", identifier).render());
        } else if(node.getOutputParams().size() == 0) {
            operation.add("returnParameters", (node.getParams().size() > 0 ? ", " : ""));
            operation.add("returnType", typeGenerator.generate(new UntypedType(), variables, false));
        }
        operation.add("operationName", nameHandler.handle(node.getName()));
        operation.add("parameters", generateDeclarations(node.getParams(), DeclarationType.PARAMETER, variables));
        return operation;
    }

    public List<String> generateDeclarations(List<DeclarationNode> declarations, DeclarationType type, List<String> variables) {
        return declarations.stream()
                .map(declaration -> type == DeclarationType.LOCAL_DECLARATION ?
                        generateLocalDeclaration(declaration, variables) : generateParameter(declaration, variables))
                .collect(Collectors.toList());
    }

    private String generateDeclaration(DeclarationNode node, String templateName, List<String> variables) {
        ST declaration = group.getInstanceOf(templateName);
        declaration.add("type", typeGenerator.generate(node.getType(), variables, false));
        declaration.add("identifier", nameHandler.handle(node.getName()));
        return declaration.render();
    }

    private String generateLocalDeclaration(DeclarationNode node, List<String> variables) {
        return generateDeclaration(node, "local_declaration", variables);
    }

    private String generateParameter(DeclarationNode node, List<String> variables) {
        return generateDeclaration(node, "declaration", variables);
    }

}
