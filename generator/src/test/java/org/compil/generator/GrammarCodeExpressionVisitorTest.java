package org.compil.generator;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.compil.compiler.model.Document;
import org.compil.compiler.visitor.CompilDocumentVisitor;
import org.compil.generator.Language.ELanguage;
import org.compil.parser.idl.compil.ModelLexer;
import org.compil.parser.idl.compil.ModelParser;
import org.compil.parser.template.GrammarLexer;
import org.compil.parser.template.GrammarParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GrammarCodeExpressionVisitorTest {
	Document parseDocument(String document) {
		ANTLRInputStream inputStream = new ANTLRInputStream(document);
		ModelLexer lexer = new ModelLexer(inputStream);
		TokenStream stream = new CommonTokenStream(lexer);
		ModelParser parser = new ModelParser(stream);
		ParseTree tree = parser.document();

		CompilDocumentVisitor visitor = new CompilDocumentVisitor();
		return visitor.visit(tree);
	}

	String applyGrammer(String grammar, Document document, Language defaultLanguage) {
		ANTLRInputStream inputStream = new ANTLRInputStream(grammar);
		GrammarLexer lexer = new GrammarLexer(inputStream);
		TokenStream stream = new CommonTokenStream(lexer);
		GrammarParser parser = new GrammarParser(stream);
		ParseTree tree = parser.document();

		GrammarVisitorImpl visitor = new GrammarVisitorImpl(document, defaultLanguage);
		StringBuffer buffer = visitor.visit(tree);
		return buffer == null ? "" : buffer.toString();
	}

	@BeforeClass
	public void setUp() {
		// code that will be invoked when this test is instantiated
	}

	@Test
	public void codeExpresionTest() {
		Document document = parseDocument("structure foo {}");
		assertNotNull(document);

		Language cpp = new Language(ELanguage.Cpp);
		String result = applyGrammer(
			"foreach .objects {\n" +
			"<~ class `.name` {}~>\n" +
			"}\n", document, cpp);
		assertEquals("name foo.name {\n	   foo\n}\n<~\nclass `foo.name` {}\n~>", result);
	}
}
