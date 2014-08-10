package org.compil.compiler.visitor;

import org.compil.compiler.model.Structure;
import org.compil.parser.CompilBaseVisitor;
import org.compil.parser.CompilParser;

public class CompilStructureVisitor extends CompilBaseVisitor<Structure> {
	@Override 
	public Structure visitStructure(CompilParser.StructureContext ctx) {
		Structure structure = new Structure();
		structure.setName(ctx.name().getText());
		return structure;
	}
}
