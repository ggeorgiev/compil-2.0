package org.compil.compiler.visitor;

import org.compil.compiler.model.CompilObject;
import org.compil.parser.CompilBaseVisitor;
import org.compil.parser.CompilParser;

public class CompilObjectVisitor extends CompilBaseVisitor<CompilObject> {
	@Override 
	public CompilObject visitDocument(CompilParser.DocumentContext ctx) {
		CompilDocumentVisitor visitor = new CompilDocumentVisitor();
		return visitor.visit(ctx);
	}
	
	@Override 
	public CompilObject visitStructure(CompilParser.StructureContext ctx) {
		CompilStructureVisitor visitor = new CompilStructureVisitor();
		return visitor.visit(ctx);
	}
}
