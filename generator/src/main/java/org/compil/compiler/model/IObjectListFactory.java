package org.compil.compiler.model;

import java.util.List;

public interface IObjectListFactory {
	public boolean hasObjectList(CompilObject obj,
								 String list);

	public List<CompilObject> getObjectList(CompilObject obj,
											String list);
}
