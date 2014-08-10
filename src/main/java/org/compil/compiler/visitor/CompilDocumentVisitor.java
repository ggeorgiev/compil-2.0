package org.compil.compiler.visitor;

import org.compil.compiler.model.CompilObject;
import org.compil.compiler.model.Document;
import org.compil.parser.CompilBaseVisitor;
import org.compil.parser.CompilParser;

public class CompilDocumentVisitor extends CompilBaseVisitor<Document> {
	@Override 
	public Document visitDocument(CompilParser.DocumentContext ctx) {
		Document document = new Document();
		
		for (int i = 0; i < ctx.getChildCount(); ++i) {
			CompilObjectVisitor visitor = new CompilObjectVisitor();
			CompilObject obj = visitor.visit(ctx.getChild(i));
			document.addObject(obj);
		}
		
		return document;
	}
}
