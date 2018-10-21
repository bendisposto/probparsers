package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.EnumeratedSetDeclarationNode;
import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.nodes.MachineReferenceNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeclarationGenerator {

    private final STGroup currentGroup;

    private final MachineGenerator machineGenerator;

    private final TypeGenerator typeGenerator;

    private final NameHandler nameHandler;

    private final Map<String, List<String>> setToEnum;


    public DeclarationGenerator(final STGroup currentGroup, final MachineGenerator machineGenerator, final TypeGenerator typeGenerator, final NameHandler nameHandler) {
        this.currentGroup = currentGroup;
        this.machineGenerator = machineGenerator;
        this.typeGenerator = typeGenerator;
        this.nameHandler = nameHandler;
        this.setToEnum = new HashMap<>();
    }

    /*
    * This function generates code from the VARIABLES clause
    */
    public List<String> visitDeclarations(List<DeclarationNode> declarations) {
        return declarations.stream()
                .map(this::generateGlobalDeclaration)
                .collect(Collectors.toList());
    }

    /*
    * This function generates code for each declaration from the VARIABLES clause
    */
    private String generateGlobalDeclaration(DeclarationNode node) {
        ST declaration = currentGroup.getInstanceOf("global_declaration");
        declaration.add("type", typeGenerator.generate(node.getType(), false));
        declaration.add("identifier", nameHandler.handleIdentifier(node.getName(), NameHandler.IdentifierHandlingEnum.MACHINES));
        return declaration.render();
    }

    public String generateValues(MachineNode node) {
        if(node.getValues().size() == 0) {
            return "";
        }
        ST values = currentGroup.getInstanceOf("values");
        List<String> assignments = node.getValues().stream()
                .map(substitution -> machineGenerator.visitSubstitutionNode(substitution, null))
                .collect(Collectors.toList());
        values.add("assignments", assignments);
        return values.render();
    }

    public List<String> generateConstants(MachineNode node) {
        //TODO Generate code for PROPERTIES (?)
        return node.getConstants().stream()
                .map(this::generateConstant)
                .collect(Collectors.toList());
    }

    private String generateConstant(DeclarationNode constant) {
        ST declaration = currentGroup.getInstanceOf("constant");
        declaration.add("type", typeGenerator.generate(constant.getType(), false));
        declaration.add("identifier", nameHandler.handleIdentifier(constant.getName(), NameHandler.IdentifierHandlingEnum.MACHINES));
        return declaration.render();
    }

    /*
    * This function generates code for all including other machines with the given AST node of the main machine.
    */
    public List<String> generateIncludes(MachineNode node) {
        return node.getMachineReferences().stream()
                .map(this::generateIncludeDeclaration)
                .collect(Collectors.toList());
    }

    /*
    * This function generates code for one included machine with the given AST node and the template.
    */
    private String generateIncludeDeclaration(MachineReferenceNode reference) {
        ST declaration = currentGroup.getInstanceOf("include_declaration");
        String machine = reference.getMachineName();
        declaration.add("type", nameHandler.handle(machine));
        declaration.add("identifier", nameHandler.handle(machine));
        return declaration.render();
    }

    /*
    * This function generates code for enumerated sets within a machine.
    */
    public List<String> generateEnumDeclarations(MachineNode node) {
        node.getEnumaratedSets().forEach(set -> setToEnum.put(set.getSetDeclarationNode().getName(), set.getElements().stream()
                .map(DeclarationNode::getName)
                .collect(Collectors.toList())));
        return node.getEnumaratedSets().stream()
                .map(this::declareEnums)
                .collect(Collectors.toList());
    }

    /*
    * This function generates code for all declarations of enums for enumerated sets from the node of the machine.
    */
    public List<String> generateSetDeclarations(MachineNode node) {
        return node.getEnumaratedSets().stream()
                .map(this::visitEnumeratedSetDeclarationNode)
                .collect(Collectors.toList());
    }

    /*
    * This function generates code for declarating a enum for an enumerated set from the belonging AST node and the belonging template.
    */
    private String declareEnums(EnumeratedSetDeclarationNode node) {
        typeGenerator.addImport(node.getElements().get(0).getType());
        ST enumDeclaration = currentGroup.getInstanceOf("set_enum_declaration");
        enumDeclaration.add("name", nameHandler.handleIdentifier(node.getSetDeclarationNode().getName(), NameHandler.IdentifierHandlingEnum.MACHINES));
        List<String> enums = node.getElements().stream()
                .map(element -> nameHandler.handleEnum(element.getName(), node.getElements().stream().map(DeclarationNode::getName).collect(Collectors.toList())))
                .collect(Collectors.toList());
        enumDeclaration.add("enums", enums);
        return enumDeclaration.render();
    }

    /*
    * This function generates code with creating a BSet for an enumerated set from the belonging AST node and the belonging template.
    */
    public String visitEnumeratedSetDeclarationNode(EnumeratedSetDeclarationNode node) {
        typeGenerator.addImport(node.getSetDeclarationNode().getType());
        ST setDeclaration = currentGroup.getInstanceOf("set_declaration");
        setDeclaration.add("identifier", nameHandler.handleIdentifier(node.getSetDeclarationNode().getName(), NameHandler.IdentifierHandlingEnum.VARIABLES));
        List<String> enums = node.getElements().stream()
                .map(declaration -> callEnum(node.getSetDeclarationNode().getName(), declaration))
                .collect(Collectors.toList());
        setDeclaration.add("enums", enums);
        return setDeclaration.render();
    }

    /*
    * This function generates code for calling enums from an enumerated from the belonging AST node,
    * template and the name of the enumerated set the enum belongs to.
    */
    public String callEnum(String setName, DeclarationNode enumNode) {
        ST enumST = currentGroup.getInstanceOf("enum_call");
        enumST.add("class", nameHandler.handleIdentifier(setName, NameHandler.IdentifierHandlingEnum.MACHINES));
        enumST.add("identifier", nameHandler.handleEnum(enumNode.getName(), setToEnum.get(setName)));
        return enumST.render();
    }
}
