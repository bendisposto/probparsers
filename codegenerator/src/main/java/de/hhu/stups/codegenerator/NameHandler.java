package de.hhu.stups.codegenerator;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fabian on 01.06.18.
 */
public class NameHandler {

    private final STGroup group;

    public NameHandler(final STGroup group) {
        this.group = group;
    }


    public String handle(String string) {
        ST keywords = group.getInstanceOf("keywords");
        List<String> words = Arrays.asList(keywords.render().replaceAll(" ","").replaceAll("\n","").split(","));
        if(words.contains(string)) {
            return "_" + string;
        }
        return string;
    }

    public String handleIdentifier(String identifier, List<String> variables) {
        String result = handle(identifier);
        while(variables.contains(result)) {
            result = "_" + result;
        }
        return result;
    }
}
