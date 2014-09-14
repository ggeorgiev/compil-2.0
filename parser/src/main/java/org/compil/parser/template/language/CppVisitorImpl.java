package org.compil.parser.template.language;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.compil.parser.template.language.CppParser.CppClassContext;
import org.compil.parser.template.language.CppParser.DocumentContext;
import org.compil.parser.template.language.CppParser.StatementContext;

public class CppVisitorImpl extends CppBaseVisitor<String> {
	private String leftLanguageBrace = StringUtils.strip(Shared.tokenNames[Shared.LEFT_CODE_BRACE], "'");
	private String rightLanguageBrace = StringUtils.strip(Shared.tokenNames[Shared.RIGHT_CODE_BRACE], "'");

	@Override
	public String visitDocument(DocumentContext ctx) {
		String result = "";

		result += leftLanguageBrace;

		List<StatementContext> statements = ctx.statement();
		for (StatementContext statement : statements) {
			result += visitStatement(statement);
		}

		result += rightLanguageBrace;
		return result;
	}

	@Override
	public String visitStatement(StatementContext ctx) {
		String result = "";

		if (ctx.cppClass() != null) {
			result += visitCppClass(ctx.cppClass());
		}
		return result;
	}

	@Override
	public String visitCppClass(CppClassContext ctx) {
		String result = "";

		result += ctx.KW_CLASS().getText() + " ";
		result += ctx.name().getText() + "\n";
		result += ctx.LEFT_BRACE().getText() + "\n";
		result += ctx.RIGHT_BRACE().getText() + "\n";
		return result;
	}
}
