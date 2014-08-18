package org.compil.compiler.model;

import java.util.ArrayList;
import java.util.List;

import org.compil.compiler.annotation.Property;

public class Document extends CompilObject {

    @Property
    List<CompilObject> objects = new ArrayList<CompilObject>();

    public void addObject(CompilObject obj) {
        objects.add(obj);
    }

    public List<CompilObject> getObjects() {
        return objects;
    }

}
