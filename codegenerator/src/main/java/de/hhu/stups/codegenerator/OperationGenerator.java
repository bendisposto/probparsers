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

    public static ST generate(OperationNode node, List<String> variables, STGroup template) {
        ST operation = template.getInstanceOf("operation");
        operation.add("locals", declareLocals(node.getOutputParams(), variables, template));

        if(node.getOutputParams().size() == 1) {
            BType type = node.getOutputParams().get(0).getType();
            String identifier = node.getOutputParams().get(0).getName();
            //TODO
            operation.add("returnParameters", (node.getParams().size() > 0 ? ", " : "") + TypeGenerator.generate(type, variables, template, false) + "* " + identifier);
            operation.add("returnType", TypeGenerator.generate(type, variables, template, false));
            operation.add("return", template.getInstanceOf("return").add("identifier", identifier).render());
        } else if(node.getOutputParams().size() == 0) {
            operation.add("returnParameters", (node.getParams().size() > 0 ? ", " : ""));
            operation.add("returnType", TypeGenerator.generate(new UntypedType(), variables, template, false));
        }
        operation.add("operationName", NameHandler.handle(node.getName(), template));
        operation.add("parameters", generateParameters(node.getParams(), variables, template));
        return operation;
    }

    public static List<String> declareLocals(List<DeclarationNode> outputs, List<String> variables, STGroup template) {
         return outputs.stream()
            .map(output -> generateLocalDeclaration(output, variables, template))
            .collect(Collectors.toList());
    }

    private static String generateLocalDeclaration(DeclarationNode node, List<String> variables, STGroup template) {
        ST declaration = template.getInstanceOf("local_declaration");
        declaration.add("type", TypeGenerator.generate(node.getType(), variables, template, false));
        declaration.add("identifier", NameHandler.handle(node.getName(), template));
        return declaration.render();
    }

    private static List<String> generateParameters(List<DeclarationNode> parameters, List<String> variables, STGroup template) {
        return parameters.stream()
            .map(parameterNode -> generateParameter(parameterNode, variables, template))
            .collect(Collectors.toList());
    }

    public static String generateParameter(DeclarationNode node, List<String> variables, STGroup template) {
        ST parameter = template.getInstanceOf("parameter");
        parameter.add("type", TypeGenerator.generate(node.getType(), variables, template, false));
        parameter.add("identifier", NameHandler.handle(node.getName(), template));
        return parameter.render();
    }

}
