package org.compil.parser.template.language;

public class CppVisitorImpl extends CppBaseVisitor<String> {
	@Override
	public String visitDocument(CppParser.DocumentContext ctx) {
		return visitChildren(ctx);
	}
}
