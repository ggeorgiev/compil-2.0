package org.compil.parser;

public class CompilVisitorImpl extends CompilBaseVisitor<String> {
	@Override 
	public String visitStructure(CompilParser.StructureContext ctx) {
		
		String result;
		
		result = ctx.KW_STRUCTURE() + " " + ctx.name().getText() + "\n";
		result += "{\n";
		//result += visitChildren(ctx);
		result += "}\n";
		
		return result;
	}

	@Override 
	public String visitDocument(CompilParser.DocumentContext ctx) { 
		return visitChildren(ctx);
	}

	@Override 
	public String visitObjectComment(CompilParser.ObjectCommentContext ctx) {
		return visitChildren(ctx); 
	}
}
