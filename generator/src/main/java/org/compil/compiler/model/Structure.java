package org.compil.compiler.model;

import org.compil.compiler.annotation.Property;

public class Structure extends CompilObject {
    @Property
    private String name = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
