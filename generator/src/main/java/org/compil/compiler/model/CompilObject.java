package org.compil.compiler.model;

import java.util.List;

public class CompilObject {
	
	static private Character dot = '.'; 
	
	public CompilObject getParent() {
		return null;
	}

	public String getBaseName() {
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + 
												" objects has no base name");
	}
	
	public String getPropertyName(IPropertyFactory propertyFactory, String text) {
		if (text.charAt(0) != dot) {
			throw new IllegalArgumentException("property must start with '.'");
		}
		
		String name = text.substring(1);

		if (text.charAt(1) == dot)
			return getParent().getPropertyName(propertyFactory, name);		
		
		if (!propertyFactory.hasProperty(this, name)) {
			throw new UnsupportedOperationException("Object " + this.getClass().getSimpleName() + 
													" does not support property " + name);
		}
		
		String fullname = "";
		if (getParent() != null)
			fullname = getParent().getBaseName() + dot;
		
		fullname += getBaseName() + dot + name;
		return fullname;
	}
	
	public List<CompilObject> getObjectList(IObjectListFactory factory, String list) {
		if (list.charAt(0) != dot) {
			throw new IllegalArgumentException("object list must start with '.'");
		}
		
		String name = list.substring(1);

		if (list.charAt(1) == dot)
			return getParent().getObjectList(factory, name);
		
		return factory.getObjectList(this, name);		
	}
}
