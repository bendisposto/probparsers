package de.hhu.stups.codegenerator;

import de.prob.parser.ast.nodes.MachineNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.hhu.stups.codegenerator.NameHandler.IdentifierHandlingEnum.MACHINES;
import static de.hhu.stups.codegenerator.NameHandler.IdentifierHandlingEnum.VARIABLES;

/**
 * Created by fabian on 01.06.18.
 */
public class NameHandler {

    public enum IdentifierHandlingEnum {
        VARIABLES,
        MACHINES,
        ENUMS
    }

    private final STGroup group;

    private List<String> globals;

    private List<String> reservedMachines;

    private List<String> reservedMachinesAndVariables;

    private List<String> reservedMachinesAndVariablesAndEnums;

    private Map<String, List<String>> enumTypes;

    public NameHandler(final STGroup group) {
        this.group = group;
        this.globals = new ArrayList<>();
        this.enumTypes = new HashMap<>();
        this.reservedMachines = new ArrayList<>();
        this.reservedMachinesAndVariables = new ArrayList<>();
        this.reservedMachinesAndVariablesAndEnums = new ArrayList<>();
    }

    public void initialize(MachineNode node) {
        node.getEnumaratedSets().forEach(set -> enumTypes.put(set.getSetDeclarationNode().getName(), set.getElementsAsStrings()));
        reservedMachines.addAll(node.getMachineReferences().stream()
                .map(reference -> handle(reference.getMachineName()))
                .collect(Collectors.toList()));
        reservedMachinesAndVariables.addAll(reservedMachines);
        reservedMachinesAndVariables.addAll(node.getVariables().stream()
                .map(variable -> handleIdentifier(variable.getName(), MACHINES))
                .collect(Collectors.toList()));

        reservedMachinesAndVariablesAndEnums.addAll(reservedMachinesAndVariables);
        this.reservedMachinesAndVariablesAndEnums.addAll(node.getEnumaratedSets().stream()
                .map(set -> handleIdentifier(set.getSetDeclarationNode().getName(), VARIABLES))
                .collect(Collectors.toList()));
        this.globals.addAll(reservedMachinesAndVariablesAndEnums);
        this.globals.addAll(node.getEnumaratedSets().stream()
                .map(set -> handleIdentifier(set.getSetDeclarationNode().getName(), NameHandler.IdentifierHandlingEnum.ENUMS))
                .collect(Collectors.toList()));
    }


    public String handle(String string) {
        ST keywords = group.getInstanceOf("keywords");
        List<String> words = Arrays.asList(keywords.render().replaceAll(" ","").replaceAll("\n","").split(","));
        if(words.contains(string)) {
            return "_" + string;
        }
        return string;
    }

    public String handleIdentifier(String identifier, IdentifierHandlingEnum identifierHandling) {
        List<String> variables = getVariables(identifierHandling);
        String result = handle(identifier);
        while(variables.contains(result)) {
            result = "_" + result;
        }
        return result;
    }

    public String handleEnum(String identifier, List<String> enums) {
        ST keywords = group.getInstanceOf("keywords");
        List<String> words = Arrays.asList(keywords.render().replaceAll(" ","").replaceAll("\n","").split(","));
        String result = identifier;
        if(words.contains(identifier)) {
            while (enums.contains(result)) {
                result = result + "_";
            }
        }
        return result;
    }

    private List<String> getVariables(IdentifierHandlingEnum identifierHandling) {
        List<String> variables = null;
        switch (identifierHandling) {
            case MACHINES:
                variables = reservedMachines;
                break;
            case VARIABLES:
                variables = reservedMachinesAndVariables;
                break;
            case ENUMS:
                variables = reservedMachinesAndVariablesAndEnums;
                break;
            default:
                break;
        }
        return variables;
    }

    public List<String> getGlobals() {
        return globals;
    }

    public Map<String, List<String>> getEnumTypes() {
        return enumTypes;
    }
}
