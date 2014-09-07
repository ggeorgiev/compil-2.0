package org.compil.compiler.visitor;

import org.compil.compiler.model.Structure;
import org.compil.compiler.model.property.NameProperty;
import org.compil.parser.idl.compil.ModelBaseVisitor;
import org.compil.parser.idl.compil.ModelParser;

public class CompilStructureVisitor extends ModelBaseVisitor<Structure> {
	@Override
	public Structure visitStructure(ModelParser.StructureContext ctx) {
		Structure structure = new Structure();
		NameProperty propertyName = new NameProperty();
		propertyName.value = ctx.name().getText();
		structure.setName(propertyName);
		return structure;
	}
}
