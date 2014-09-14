package org.compil.generator.cpp;

import org.compil.parser.template.language.CppBaseVisitor;
import org.compil.parser.template.language.CppParser.DocumentContext;

public class CppVistiorImpl extends CppBaseVisitor<String> {

	@Override
	public String visitDocument(DocumentContext ctx) {
		return "";
	}


}
