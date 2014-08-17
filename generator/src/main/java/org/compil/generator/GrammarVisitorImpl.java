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

public class GrammarVisitorImpl extends GrammarBaseVisitor<StringBuffer> {
	
	private Document document = null;
	private Language language = null;
		
	CompilObject activeObject = null;
	
	Deque<CompilObject> activeObjects = null;
	
	public GrammarVisitorImpl(Document document,
							  Language language)
	{
		this.document = document;
		this.language = language;

		activeObject = document;
		activeObjects = new ArrayDeque<CompilObject>();
	}
	
	@Override 
	public StringBuffer visitDocument(DocumentContext ctx) {
		StringBuffer buffer = new StringBuffer();
		
		List<StatementContext> statements = ctx.statement();
		if (statements == null)
			return buffer;
		
		for (StatementContext statement : statements) {
			buffer.append(visitStatement(statement));
		}
		
		return buffer;
	}
	
	@Override 
	public StringBuffer visitCodeStatement(GrammarParser.CodeStatementContext ctx) {
		StringBuffer buffer = new StringBuffer();

		if (  (ctx.language() != null)
		   && (!language.equals(new Language(ctx.language().getText())))) {
		   return buffer;
		}
		buffer.append(ctx.codeList().getText());
		return buffer;
	}
	
	@Override 
	public StringBuffer visitCompoundStatement(GrammarParser.CompoundStatementContext ctx)
	{
		StringBuffer buffer = new StringBuffer();

		BlockStatementItemListContext bsilc = ctx.blockStatementItemList();
		while (bsilc != null) {
			if (bsilc.statement() != null)
				buffer.append(visitStatement(bsilc.statement()));
			bsilc = bsilc.blockStatementItemList();
		}
		
		return buffer;
	}
	
	@Override 
	public StringBuffer visitWhen(GrammarParser.WhenContext ctx)
	{
		try {
			Class<?> type = Class.forName(ctx.getText());
			if (!activeObject.getClass().isInstance(type))
				return null;
			return visitStatement(ctx.statement());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}	
}
