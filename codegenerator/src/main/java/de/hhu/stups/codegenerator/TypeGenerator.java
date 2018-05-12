package de.hhu.stups.codegenerator;


import de.prob.parser.ast.types.BType;
import de.prob.parser.ast.types.BoolType;
import de.prob.parser.ast.types.EnumeratedSetElementType;
import de.prob.parser.ast.types.IntegerType;
import de.prob.parser.ast.types.SetType;
import org.stringtemplate.v4.STGroup;

import java.util.Set;

public class TypeGenerator {

    public static String generate(BType type, STGroup template) {
        if(type instanceof IntegerType) {
            return generateInteger(template);
        } else if(type instanceof BoolType) {
            return generateBoolean(template);
        } else if(type instanceof SetType) {
            return generateSet(template);
        } else if(type instanceof EnumeratedSetElementType) {
          return generateObject(template);
        }
        return "";
    }

    public static String generateCast(BType type, STGroup template) {
        if(type instanceof IntegerType) {
            return generateIntegerCast(template);
        } else if(type instanceof BoolType) {
            return generateBooleanCast(template);
        } else if(type instanceof SetType) {
            return generateSetCast(template);
        } else if(type instanceof EnumeratedSetElementType) {
            return generateObjectCast(template);
        }
        return "";
    }

    private static String generateInteger(STGroup template) {
        return template.getInstanceOf("integer").render();
    }

    private static String generateBoolean(STGroup template) {
        return template.getInstanceOf("boolean").render();
    }

    private static String generateSet(STGroup template) {
        return template.getInstanceOf("set").render();
    }

    private static String generateIntegerCast(STGroup template) {
        return template.getInstanceOf("integer_cast").render();
    }

    private static String generateBooleanCast(STGroup template) {
        return template.getInstanceOf("boolean_cast").render();
    }

    private static String generateSetCast(STGroup template) {
        return template.getInstanceOf("set_cast").render();
    }

    private static String generateObject(STGroup template) {
        return template.getInstanceOf("object").render();
    }

    private static String generateObjectCast(STGroup template) {
        return template.getInstanceOf("object_cast").render();
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
