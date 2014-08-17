package org.compil.generator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import org.compil.compiler.model.CompilObject;
import org.compil.compiler.model.Document;
import org.compil.parser.template.GrammarBaseVisitor;
import org.compil.parser.template.GrammarParser;
import org.compil.parser.template.GrammarParser.BlockStatementItemListContext;
import org.compil.parser.template.GrammarParser.DocumentContext;
import org.compil.parser.template.GrammarParser.StatementContext;

public class GrammarVisitorImpl extends GrammarBaseVisitor<String> {
	
	private Document document = null;
	private Language defaultLanguage = null;
		
	CompilObject activeObject = null;
	
	Deque<CompilObject> activeObjects = null;
	
	public GrammarVisitorImpl(Document document,
							  Language defaultLanguage)
	{
		this.document = document;
		this.defaultLanguage = defaultLanguage;

		activeObject = document;
		activeObjects = new ArrayDeque<CompilObject>();
	}
	
	@Override 
	public String visitDocument(DocumentContext ctx) {
		StringBuffer buffer = new StringBuffer();
		
		List<StatementContext> statements = ctx.statement();
		if (statements == null)
			return "";
		
		for (StatementContext statement : statements) {
			buffer.append(visitStatement(statement));
		}
		
		return buffer.toString();
	}
	
	@Override 
	public String visitCodeStatement(GrammarParser.CodeStatementContext ctx) {
		if (  (ctx.language() != null)
		   && (!defaultLanguage.equals(new Language(ctx.language().getText())))) {
		   return "";
		}
		
		return ctx.getText();
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
