package de.hhu.stups.codegenerator;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fabian on 01.06.18.
 */
public class NameHandler {

    public static String handleMachineName(String string) {
        //TODO
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }

    public static String handle(String string, STGroup template) {
        //TODO
        ST keywords = template.getInstanceOf("keywords");
        List<String> words = Arrays.asList(keywords.render().replaceAll(" ","").replaceAll("\n","").split(","));
        if(words.contains(string)) {
            return "_" + string;
        }
        return string;
    }


}
