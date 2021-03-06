package org.compil.parser.template;

public class GrammarVisitorImpl extends GrammarBaseVisitor<String> {

	@Override
	public String visitCompoundStatement(GrammarParser.CompoundStatementContext ctx)
	{
		String result = ctx.LEFT_BRACE().getText() + "\n";
		if (ctx.blockStatementItemList() != null)
			visit(ctx.blockStatementItemList());
		result += ctx.RIGHT_BRACE().getText() + "\n";
		return result;
	}

	@Override
	public String visitForeach(GrammarParser.ForeachContext ctx)
	{
		String result = ctx.KW_FOREACH() + " ";
		result += visit(ctx.objects()) + "\n";
		result += visit(ctx.statement());
		return result;
	}

	@Override
	public String visitObjects(GrammarParser.ObjectsContext ctx)
	{
		String result = ctx.DOT().getText();
		if (ctx.IDENTIFIER()  != null)
			result += ctx.IDENTIFIER().getText();
		else if (ctx.objects() != null)
			result += visit(ctx.objects());
		return result;
	}

	@Override
	public String visitWhen(GrammarParser.WhenContext ctx)
	{
		String result = ctx.KW_WHEN() + " ";
		result += visit(ctx.type()) + "\n";
		result += visit(ctx.statement());
		return result;
	}

	@Override
	public String visitType(GrammarParser.TypeContext ctx)
	{
		return ctx.IDENTIFIER().getText();
	}
}
