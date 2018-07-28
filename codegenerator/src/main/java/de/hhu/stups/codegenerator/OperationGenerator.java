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
        operation.add("locals", declareLocals(node.getOutputParams(), variables));

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
        operation.add("parameters", generateParameters(node.getParams(), variables));
        return operation;
    }

    public List<String> declareLocals(List<DeclarationNode> outputs, List<String> variables) {
         return outputs.stream()
            .map(output -> generateLocalDeclaration(output, variables))
            .collect(Collectors.toList());
    }

    private String generateLocalDeclaration(DeclarationNode node, List<String> variables) {
        ST declaration = group.getInstanceOf("local_declaration");
        declaration.add("type", typeGenerator.generate(node.getType(), variables, false));
        declaration.add("identifier", nameHandler.handle(node.getName()));
        return declaration.render();
    }

    private List<String> generateParameters(List<DeclarationNode> parameters, List<String> variables) {
        return parameters.stream()
            .map(parameterNode -> generateParameter(parameterNode, variables))
            .collect(Collectors.toList());
    }

    public String generateParameter(DeclarationNode node, List<String> variables) {
        ST parameter = group.getInstanceOf("parameter");
        parameter.add("type", typeGenerator.generate(node.getType(), variables, false));
        parameter.add("identifier", nameHandler.handle(node.getName()));
        return parameter.render();
    }

}
