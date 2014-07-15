package org.compil.parser;

public class TemplateVisitorImpl extends TemplateBaseVisitor<String> {

	@Override 
	public String visitCompoundStatement(TemplateParser.CompoundStatementContext ctx)
	{
		String result = ctx.LEFT_BRACE().getText() + "\n";
		if (ctx.blockStatementItemList() != null)
			visit(ctx.blockStatementItemList());
		result += ctx.RIGHT_BRACE().getText() + "\n";
		return result;
	}
	
	@Override 
	public String visitWhen(TemplateParser.WhenContext ctx) 
	{
		String result = ctx.KW_WHEN() + " ";
		result += visit(ctx.type()) + "\n";
		result += visit(ctx.statement());
		return result;
	}
	
	@Override
	public String visitForeach(TemplateParser.ForeachContext ctx)
	{
		String result = ctx.KW_FOREACH() + " ";
		result += visit(ctx.property()) + "\n";
		result += visit(ctx.statement());
		return result;
	}
	
	@Override 
	public String visitProperty(TemplateParser.PropertyContext ctx) 
	{
		String result = ctx.DOT().getText();
		if (ctx.IDENTIFIER()  != null)
			result += ctx.IDENTIFIER().getText();
		else if (ctx.property() != null)
			result += visit(ctx.property());
		return result;
	}
	
	@Override 
	public String visitType(TemplateParser.TypeContext ctx)
	{
		return ctx.IDENTIFIER().getText();
	}	
}
