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

public class TypeGenerator {

    private final STGroup group;

    private final NameHandler nameHandler;

    public TypeGenerator(STGroup group, NameHandler nameHandler) {
        this.group = group;
        this.nameHandler = nameHandler;
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
            return group.getInstanceOf("set_type")
                    .add("type", generate(((SetType) type).getSubType(), false))
                    .add("cast", cast).render();
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

}
