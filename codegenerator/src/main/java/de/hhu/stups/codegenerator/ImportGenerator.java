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
            TemplateHandler.add(template, "type", "BInteger");
            imports.add(template.render());
        } else if (type instanceof BoolType) {
            TemplateHandler.add(template, "type", "BBoolean");
            imports.add(template.render());
        } else if(type instanceof SetType) {
            TemplateHandler.add(template, "type", "BSet");
            imports.add(template.render());
        } else if(type instanceof EnumeratedSetElementType) {
            template = group.getInstanceOf("import_type");
            TemplateHandler.add(template, "type", "BObject");
            imports.add(template.render());
            template = group.getInstanceOf("import_type");
            TemplateHandler.add(template, "type", "BBoolean");
            imports.add(template.render());
        } else if(type instanceof CoupleType) {
            TemplateHandler.add(template, "type", "BCouple");
            imports.add(template.render());
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
        TemplateHandler.add(imp, "type", nameHandler.handle(machine));
        return imp.render();
    }


    public Set<String> getImports() {
        return imports;
    }
}
