package org.compil.compiler.model;

import org.compil.compiler.annotation.Property;
import org.compil.compiler.model.property.NameProperty;

public class Structure extends CompilObject {
	@Property
	private NameProperty name = null;

	public NameProperty getName() {
		return name;
	}

	public void setName(NameProperty name) {
		this.name = name;
	}
	
	public String getBaseName() {
		return getName().value;
	}
}
