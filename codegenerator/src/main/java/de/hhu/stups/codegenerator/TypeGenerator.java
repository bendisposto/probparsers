package de.hhu.stups.codegenerator;


import de.prob.parser.ast.types.BType;
import de.prob.parser.ast.types.BoolType;
import de.prob.parser.ast.types.CoupleType;
import de.prob.parser.ast.types.EnumeratedSetElementType;
import de.prob.parser.ast.types.IntegerType;
import de.prob.parser.ast.types.SetType;
import de.prob.parser.ast.types.UntypedType;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.HashSet;
import java.util.Set;

public class TypeGenerator {

    private final STGroup group;

    private final NameHandler nameHandler;

    private final Set<String> imports;

    public TypeGenerator(STGroup group, NameHandler nameHandler) {
        this.group = group;
        this.nameHandler = nameHandler;
        this.imports = new HashSet<>();
    }

    /*
    * This function generates code for a type with the given type and the information whether the type is generated for casting an object
    */
    public String generate(BType type, boolean cast) {
        ST template = group.getInstanceOf("type");
        if(type instanceof IntegerType) {
            return template.add("type", "BInteger").add("cast", cast).render();
        } else if(type instanceof BoolType) {
            return template.add("type", "BBoolean").add("cast", cast).render();
        } else if(type instanceof SetType) {
            return template.add("type", "BSet").add("cast", cast).render();
        } else if(type instanceof EnumeratedSetElementType) {
            return template.add("type", nameHandler.handleIdentifier(type.toString(), NameHandler.IdentifierHandlingEnum.MACHINES)).add("cast", cast).render();
        } else if(type instanceof CoupleType) {
            return template.add("type", "BCouple").add("cast", cast).render();
        } else if(type instanceof UntypedType) {
            return generateUntyped();
        }
        return "";
    }

    /*
    * This function generates code for untyped nodes.
    */
    private String generateUntyped() {
        return group.getInstanceOf("void").render();
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

    public Set<String> getImports() {
        return imports;
    }

}
