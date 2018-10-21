package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.nodes.MachineReferenceNode;
import de.prob.parser.ast.types.BType;
import de.prob.parser.ast.types.BoolType;
import de.prob.parser.ast.types.CoupleType;
import de.prob.parser.ast.types.EnumeratedSetElementType;
import de.prob.parser.ast.types.IntegerType;
import de.prob.parser.ast.types.SetType;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ImportGenerator {

    private final STGroup group;

    private final NameHandler nameHandler;

    private final Set<String> imports;

    public ImportGenerator(final STGroup group, final NameHandler nameHandler) {
        this.group = group;
        this.nameHandler = nameHandler;
        this.imports = new HashSet<>();
    }

    /*
    * This function adds import for the types used in the generated code
    */
    public void addImport(BType type) {
        ST template = group.getInstanceOf("import_type");
        if (type instanceof IntegerType) {
            imports.add(template.add("type", "BInteger").render());
        } else if (type instanceof BoolType) {
            imports.add(template.add("type", "BBoolean").render());
        } else if(type instanceof SetType) {
            imports.add(template.add("type", "BSet").render());
        } else if(type instanceof EnumeratedSetElementType) {
            imports.add(group.getInstanceOf("import_type").add("type", "BObject").render());
            imports.add(group.getInstanceOf("import_type").add("type", "BBoolean").render());
        } else if(type instanceof CoupleType) {
            imports.add(template.add("type", "BCouple").render());
        }
    }

    public List<String> generateMachineImports(MachineNode node) {
        return node.getMachineReferences().stream()
                .map(this::generateMachineImport)
                .collect(Collectors.toList());
    }

    private String generateMachineImport(MachineReferenceNode reference) {
        ST imp = group.getInstanceOf("import_type");
        String machine = reference.getMachineName();
        imp.add("type", nameHandler.handle(machine));
        return imp.render();
    }


    public Set<String> getImports() {
        return imports;
    }
}
