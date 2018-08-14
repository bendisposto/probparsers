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

/**
 * Created by fabian on 01.06.18.
 */
public class NameHandler {

    /*
    * Enum for handling levels for the collision problem between identifiers and keywords
    */
    public enum IdentifierHandlingEnum {
        MACHINES,
        VARIABLES
    }

    private final STGroup group;

    private List<String> globals;

    private List<String> reservedMachines;

    private List<String> reservedMachinesAndVariables;

    private Map<String, List<String>> enumTypes;

    public NameHandler(final STGroup group) {
        this.group = group;
        this.globals = new ArrayList<>();
        this.enumTypes = new HashMap<>();
        this.reservedMachines = new ArrayList<>();
        this.reservedMachinesAndVariables = new ArrayList<>();
    }

    /*
    * This functions initializes different levels for handling collisions between identifiers and keywords
    */
    public void initialize(MachineNode node) {
        node.getEnumaratedSets().forEach(set -> enumTypes.put(set.getSetDeclarationNode().getName(), set.getElementsAsStrings()));
        reservedMachines.addAll(node.getMachineReferences().stream()
                .map(reference -> handle(reference.getMachineName()))
                .collect(Collectors.toList()));
        reservedMachinesAndVariables.addAll(reservedMachines);
        reservedMachinesAndVariables.addAll(node.getVariables().stream()
                .map(variable -> handleIdentifier(variable.getName(), MACHINES))
                .collect(Collectors.toList()));

        reservedMachinesAndVariables.addAll(node.getEnumaratedSets().stream()
                .map(set -> handleIdentifier(set.getSetDeclarationNode().getName(), MACHINES))
                .collect(Collectors.toList()));
        globals.addAll(reservedMachinesAndVariables);
        globals.addAll(node.getEnumaratedSets().stream()
                .map(set -> handleIdentifier(set.getSetDeclarationNode().getName(), NameHandler.IdentifierHandlingEnum.VARIABLES))
                .collect(Collectors.toList()));
    }


    /*
    * This function handles collision between identifiers and keywords from the belonging template.
    */
    public String handle(String string) {
        ST keywords = group.getInstanceOf("keywords");
        List<String> words = Arrays.asList(keywords.render().replaceAll(" ","").replaceAll("\n","").split(","));
        if(words.contains(string)) {
            return "_" + string;
        }
        return string;
    }

    /*
    * This function handles collision between identifiers and keywords for all levels
    */
    public String handleIdentifier(String identifier, IdentifierHandlingEnum identifierHandling) {
        StringBuilder result = new StringBuilder(handle(identifier));
        while(getVariables(identifierHandling).contains(result.toString())) {
            result.insert(0, "_");
        }
        return result.toString();
    }


    /*
    * This function handles collisions between keywords and identifiers for enums
    */
    public String handleEnum(String identifier, List<String> enums) {
        ST keywords = group.getInstanceOf("keywords");
        List<String> words = Arrays.asList(keywords.render().replaceAll(" ","").replaceAll("\n","").split(","));
        StringBuilder result = new StringBuilder(identifier);
        if(words.contains(identifier)) {
            while (enums.contains(result.toString())) {
                result.append("_");
            }
        }
        return result.toString();
    }

    /*
    * This function gets the list of reserved variables from a given level that is represented by identifierHandling
    */
    private List<String> getVariables(IdentifierHandlingEnum identifierHandling) {
        List<String> variables = null;
        switch (identifierHandling) {
            case MACHINES:
                variables = reservedMachines;
                break;
            case VARIABLES:
                variables = reservedMachinesAndVariables;
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
