package org.compil.parser;

public class TemplateVisitorImpl extends TemplateBaseVisitor<String> {
	@Override
	public String visitForeach(TemplateParser.ForeachContext ctx)
	{
		String result = ctx.KW_FOREACH() + " ";
		result += visitProperty(ctx.property()) + "\n";
		result += "{\n";
		result += "}\n";
		return result;
	}
	
	@Override 
	public String visitProperty(TemplateParser.PropertyContext ctx) 
	{
		String result = ctx.DOT().getText();
		if (ctx.PROPERTY_NAME()  != null)
			result += ctx.PROPERTY_NAME().getText();
		else if (ctx.property() != null)
			result += visitProperty(ctx.property());
		return result;
	}	
}
