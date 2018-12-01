package de.hhu.stups.codegenerator;

import org.stringtemplate.v4.ST;

public class TemplateHandler {

    public static void add(ST template, String key, Object object) {
        if(template.getAttributes() != null && template.getAttributes().keySet().contains(key)) {
            template.add(key,object);
        }
    }

}