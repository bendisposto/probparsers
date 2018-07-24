package de.hhu.stups.codegenerator;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fabian on 01.06.18.
 */
public class NameHandler {


    public static String handle(String string, STGroup template) {
        ST keywords = template.getInstanceOf("keywords");
        List<String> words = Arrays.asList(keywords.render().replaceAll(" ","").replaceAll("\n","").split(","));
        if(words.contains(string)) {
            return "_" + string;
        }
        return string;
    }

    public static String handleIdentifier(String identifier, List<String> variables, STGroup template) {
        String result = handle(identifier, template);
        while(variables.contains(result)) {
            result = "_" + result;
        }
        return result;
    }
}
