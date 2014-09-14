package org.compil.compiler.model;

import org.compil.compiler.model.property.Property;

public interface IPropertyFactory {
	public boolean hasProperty(CompilObject obj,
							   String property);

	public Property getProperty(CompilObject obj,
								String property);
}
