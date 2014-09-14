package org.compil.generator.cpp;

import org.apache.commons.lang3.StringUtils;
import org.compil.parser.template.language.CppBaseVisitor;
import org.compil.parser.template.language.CppParser.DocumentContext;
import org.compil.parser.template.language.Shared;

public class CppVistiorImpl extends CppBaseVisitor<String> {
	private String leftLanguageBrace = StringUtils.strip(Shared.tokenNames[Shared.LEFT_CODE_BRACE], "'");
	private String rightLanguageBrace = StringUtils.strip(Shared.tokenNames[Shared.RIGHT_CODE_BRACE], "'");

	@Override
	public String visitDocument(DocumentContext ctx) {
		return "";
	}


}
