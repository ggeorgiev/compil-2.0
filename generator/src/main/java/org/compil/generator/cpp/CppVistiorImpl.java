package org.compil.generator.cpp;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.compil.generator.language.Namer;
import org.compil.parser.template.language.CppBaseVisitor;
import org.compil.parser.template.language.Shared;
import org.compil.parser.template.language.CppParser.CppClassContext;
import org.compil.parser.template.language.CppParser.DocumentContext;
import org.compil.parser.template.language.CppParser.StatementContext;

public class CppVistiorImpl extends CppBaseVisitor<StringBuffer> {
	private String leftCodeBrace = StringUtils.strip(Shared.tokenNames[Shared.LEFT_CODE_BRACE], "'");
	private String rightCodeBrace = StringUtils.strip(Shared.tokenNames[Shared.RIGHT_CODE_BRACE], "'");

	private Namer cppClassNamer = new Namer();

	@Override
	public StringBuffer visitDocument(DocumentContext ctx) {
		StringBuffer result = new StringBuffer();

		result.append(leftCodeBrace);
		result.append("\n");

		List<StatementContext> statements = ctx.statement();
		for (StatementContext statement : statements) {
			result.append(visitStatement(statement));
		}

		result.append(rightCodeBrace);
		return result;
	}

	@Override
	public StringBuffer visitStatement(StatementContext ctx) {
		StringBuffer result = new StringBuffer();

		if (ctx.cppClass() != null) {
			result.append(visitCppClass(ctx.cppClass()));
		}
		return result;
	}

	@Override
	public StringBuffer visitCppClass(CppClassContext ctx) {
		StringBuffer result = new StringBuffer();

		result.append("class ");
		result.append(cppClassNamer.getShortName(ctx.name().getText()));
		result.append(" {\n");
		result.append("}\n");
		return result;
	}

}
