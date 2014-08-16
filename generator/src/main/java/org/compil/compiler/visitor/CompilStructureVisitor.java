package org.compil.compiler.visitor;

import org.compil.compiler.model.Structure;
import org.compil.parser.idl.compil.ModelBaseVisitor;
import org.compil.parser.idl.compil.ModelParser;

public class CompilStructureVisitor extends ModelBaseVisitor<Structure> {
	@Override 
	public Structure visitStructure(ModelParser.StructureContext ctx) {
		Structure structure = new Structure();
		structure.setName(ctx.name().getText());
		return structure;
	}
}
