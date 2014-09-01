package org.compil.generator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import org.compil.compiler.model.CompilObject;
import org.compil.compiler.model.Document;
import org.compil.compiler.model.IObjectListFactory;
import org.compil.compiler.model.IPropertyFactory;
import org.compil.compiler.model.ObjectListFactory;
import org.compil.compiler.model.PropertyFactory;
import org.compil.parser.template.GrammarBaseVisitor;
import org.compil.parser.template.GrammarParser.BlockStatementItemListContext;
import org.compil.parser.template.GrammarParser.CodeContext;
import org.compil.parser.template.GrammarParser.CodeExpressionContext;
import org.compil.parser.template.GrammarParser.CodeListContext;
import org.compil.parser.template.GrammarParser.CodeStatementContext;
import org.compil.parser.template.GrammarParser.CompoundStatementContext;
import org.compil.parser.template.GrammarParser.DocumentContext;
import org.compil.parser.template.GrammarParser.ForeachContext;
import org.compil.parser.template.GrammarParser.PropertyContext;
import org.compil.parser.template.GrammarParser.StatementContext;
import org.compil.parser.template.GrammarParser.WhenContext;

public class GrammarVisitorImpl extends GrammarBaseVisitor<StringBuffer> {

	private IPropertyFactory propertyFactory = new PropertyFactory();
	private IObjectListFactory objectListFactory = new ObjectListFactory();
	
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
	public StringBuffer visitCodeStatement(CodeStatementContext ctx) {
		StringBuffer buffer = new StringBuffer();

		if (  (ctx.language() != null)
		   && (!language.equals(new Language(ctx.language().getText())))) {
		   return buffer;
		}

		if (ctx.codeList() != null) {
			buffer.append(visitCodeList(ctx.codeList()));
		}

		return buffer;
	}

	@Override
	public StringBuffer visitCodeList(CodeListContext ctx) {
		StringBuffer buffer = new StringBuffer();
		if (ctx.codeList() != null) {
			buffer.append(visitCodeList(ctx.codeList()));
		}
		if (ctx.codeWhitespace() != null) {
			buffer.append(ctx.codeWhitespace().getText());
		}
		if (ctx.code() != null) {
			buffer.append(visitCode(ctx.code()));
		}
		return buffer;
	}

	@Override
	public StringBuffer visitCode(CodeContext ctx) {
		if (ctx.codeExpression() != null)
			return visitCodeExpression(ctx.codeExpression());

		return new StringBuffer(ctx.getText());
	}
	
	@Override
	public StringBuffer visitProperty(PropertyContext ctx) {
		String property = ctx.getText();
		String name = activeObject.getPropertyName(propertyFactory, property);
		return new StringBuffer(name);
	}

	@Override
	public StringBuffer visitCodeExpression(CodeExpressionContext ctx) {
		if (ctx.property() != null) {
			return visitProperty(ctx.property());
		}
			
		throw new UnsupportedOperationException("Unsuported code expression");
	}

	@Override
	public StringBuffer visitCompoundStatement(CompoundStatementContext ctx)
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
	public StringBuffer visitWhen(WhenContext ctx)
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
	
	@Override
	public StringBuffer visitForeach(ForeachContext ctx)
	{
		StringBuffer buffer = new StringBuffer();
		
		String objects = ctx.objects().getText();

		List<CompilObject> objectList = 
			activeObject.getObjectList(objectListFactory, objects);
		
		for (CompilObject object : objectList) {
			activeObject = object;
			buffer.append(visitStatement(ctx.statement()));
		}
		
		return buffer;
	}
}
