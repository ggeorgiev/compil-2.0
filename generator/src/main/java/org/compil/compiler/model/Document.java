package org.compil.compiler.model;

import java.util.ArrayList;
import java.util.List;

import org.compil.compiler.annotation.ObjectList;

public class Document extends CompilObject {

	@ObjectList
	List<CompilObject> objects = new ArrayList<CompilObject>();

	public void addObject(CompilObject obj) {
		objects.add(obj);
	}

	public List<CompilObject> getObjects() {
		return objects;
	}
}
