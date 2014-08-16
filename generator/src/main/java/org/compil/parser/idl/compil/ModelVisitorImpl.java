package org.compil.parser.idl.compil;

public class ModelVisitorImpl extends ModelBaseVisitor<String> {
	@Override 
	public String visitStructure(ModelParser.StructureContext ctx) {
		
		String result;
		
		result = ctx.KW_STRUCTURE() + " " + ctx.name().getText() + "\n";
		result += "{\n";
		//result += visitChildren(ctx);
		result += "}\n";
		
		return result;
	}

	@Override 
	public String visitDocument(ModelParser.DocumentContext ctx) { 
		return visitChildren(ctx);
	}

	@Override 
	public String visitObjectComment(ModelParser.ObjectCommentContext ctx) {
		return visitChildren(ctx); 
	}
}
