package de.hhu.stups.codegenerator;


import de.prob.parser.ast.types.BType;
import de.prob.parser.ast.types.BoolType;
import de.prob.parser.ast.types.EnumeratedSetElementType;
import de.prob.parser.ast.types.IntegerType;
import de.prob.parser.ast.types.SetType;
import org.stringtemplate.v4.STGroup;

import java.util.Set;

public class TypeGenerator {

    public static String generate(BType type, STGroup template, boolean cast) {
        if(type instanceof IntegerType) {
            return generateInteger(template, cast);
        } else if(type instanceof BoolType) {
            return generateBoolean(template, cast);
        } else if(type instanceof SetType) {
            return generateSet(template, cast);
        } else if(type instanceof EnumeratedSetElementType) {
          return generateObject(template, cast);
        }
        return "";
    }

    private static String generateInteger(STGroup template, boolean cast) {
        return template.getInstanceOf("integer").add("cast", cast).render();
    }

    private static String generateBoolean(STGroup template, boolean cast) {
        return template.getInstanceOf("boolean").add("cast", cast).render();
    }

    private static String generateSet(STGroup template, boolean cast) {
        return template.getInstanceOf("set").add("cast", cast).render();
    }

    private static String generateObject(STGroup template, boolean cast) {
        return template.getInstanceOf("object").add("cast", cast).render();
    }

    public static void addImport(BType type, Set<String> imports, STGroup template) {
        if (type instanceof IntegerType) {
            imports.add(template.getInstanceOf("integer_import").render());
        } else if (type instanceof BoolType) {
            imports.add(template.getInstanceOf("boolean_import").render());
        } else if(type instanceof EnumeratedSetElementType) {
            imports.add(template.getInstanceOf("set_import").render());
        }
    }

}
