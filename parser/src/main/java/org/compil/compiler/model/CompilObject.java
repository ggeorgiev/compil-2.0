package org.compil.compiler.model;

import java.util.List;

import org.compil.compiler.model.property.Property;

public class CompilObject {

	static private Character dot = '.';

	public CompilObject getParent() {
		return null;
	}

	public String getBaseName() {
		throw new UnsupportedOperationException(this.getClass().getSimpleName() +
												" objects has no base name");
	}

	public String getPropertyName(IPropertyFactory propertyFactory, String property) {
		if (property.charAt(0) != dot) {
			throw new IllegalArgumentException("property must start with '.'");
		}

		String name = property.substring(1);

		if (property.charAt(1) == dot)
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

	public Property getProperty(IPropertyFactory propertyFactory, String property) {
		if (property.charAt(0) != dot) {
			throw new IllegalArgumentException("property must start with '.'");
		}

		String name = property.substring(1);

		if (property.charAt(1) == dot)
			return getParent().getProperty(propertyFactory, name);

		return propertyFactory.getProperty(this, name);
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
