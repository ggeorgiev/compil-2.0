package org.compil.generator.cpp;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.compil.compiler.model.Document;
import org.compil.compiler.visitor.CompilDocumentVisitor;
import org.compil.generator.GrammarVisitorImpl;
import org.compil.generator.Language;
import org.compil.generator.Language.ELanguage;
import org.compil.parser.idl.compil.ModelLexer;
import org.compil.parser.idl.compil.ModelParser;
import org.compil.parser.template.GrammarLexer;
import org.compil.parser.template.GrammarParser;
import org.compil.parser.template.language.CppLexer;
import org.compil.parser.template.language.CppParser;
import org.compil.generator.cpp.CppVistiorImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClassTest {
	Document parseDocument(String document) {
		ANTLRInputStream inputStream = new ANTLRInputStream(document);
		ModelLexer lexer = new ModelLexer(inputStream);
		TokenStream stream = new CommonTokenStream(lexer);
		ModelParser parser = new ModelParser(stream);
		ParseTree tree = parser.document();

		CompilDocumentVisitor visitor = new CompilDocumentVisitor();
		return visitor.visit(tree);
	}

	Language cpp = new Language(ELanguage.Cpp);

	String applyGrammer(String grammar, Document document) {
		ANTLRInputStream inputStream = new ANTLRInputStream(grammar);
		GrammarLexer lexer = new GrammarLexer(inputStream);
		TokenStream stream = new CommonTokenStream(lexer);
		GrammarParser parser = new GrammarParser(stream);
		ParseTree tree = parser.document();

		GrammarVisitorImpl visitor = new GrammarVisitorImpl(document, cpp);
		StringBuffer buffer = visitor.visit(tree);
		return buffer == null ? "" : buffer.toString();
	}

	String applyLangauge(String code) {
		ANTLRInputStream inputStream = new ANTLRInputStream(code);
		CppLexer lexer = new CppLexer(inputStream);
		TokenStream stream = new CommonTokenStream(lexer);
		CppParser parser = new CppParser(stream);
		ParseTree tree = parser.document();

		CppVistiorImpl visitor = new CppVistiorImpl();
		StringBuffer buffer = visitor.visit(tree);
		return buffer == null ? "" : buffer.toString();
	}

	@BeforeClass
	public void setUp() {
		// code that will be invoked when this test is instantiated
	}

	@Test
	public void sanityTest() {
		Document document = parseDocument("");
		assertNotNull(document);

		String pass1 = applyGrammer("<~ class Name {}~>", document);
		String code = applyLangauge(pass1);
		assertEquals("<~\nclass Name {\n}\n~>", code);
	}
}
