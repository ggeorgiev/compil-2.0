package org.compil.generator;

import java.util.ArrayDeque;
import java.util.Deque;

import org.compil.compiler.model.CompilObject;
import org.compil.compiler.model.Document;
import org.compil.parser.template.GrammarBaseVisitor;
import org.compil.parser.template.GrammarParser;
import org.compil.parser.template.GrammarParser.BlockStatementItemListContext;

public class GrammarVisitorImpl extends GrammarBaseVisitor<String> {
	
	private Document document = null;
	CompilObject activeObject = null;
	
	Deque<CompilObject> activeObjects = null;
	
	public GrammarVisitorImpl(Document document)
	{
		this.document = document;
		activeObject = document;
		
		activeObjects = new ArrayDeque<CompilObject>();
	}

	@Override 
	public String visitCompoundStatement(GrammarParser.CompoundStatementContext ctx)
	{
		BlockStatementItemListContext bsilc = ctx.blockStatementItemList();

		String result = "";
		while (bsilc != null) {
			if (bsilc.statement() != null)
				result += visitStatement(bsilc.statement());
			bsilc = bsilc.blockStatementItemList();
		}
		
		return result;
	}
	
	@Override 
	public String visitWhen(GrammarParser.WhenContext ctx)
	{
		try {
			Class<?> type = Class.forName(ctx.getText());
			if (!activeObject.getClass().isInstance(type))
				return "";
			return visitStatement(ctx.statement());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String visitForeach(GrammarParser.ForeachContext ctx)
	{
		String result = ctx.KW_FOREACH() + " ";
		result += visit(ctx.property()) + "\n";
		result += visit(ctx.statement());
		return result;
	}
	
	@Override 
	public String visitProperty(GrammarParser.PropertyContext ctx) 
	{
		String result = ctx.DOT().getText();
		if (ctx.IDENTIFIER()  != null)
			result += ctx.IDENTIFIER().getText();
		else if (ctx.property() != null)
			result += visit(ctx.property());
		return result;
	}
	
	@Override 
	public String visitType(GrammarParser.TypeContext ctx)
	{
		return ctx.IDENTIFIER().getText();
	}	
}
