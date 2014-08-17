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

public class GrammarVisitorTest {
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
		return visitor.visit(tree);
	}

	@BeforeClass
	public void setUp() {
		// code that will be invoked when this test is instantiated
	}

	@Test
	public void sanity1Test() {
		Document document = parseDocument("structure foo {}");
		assertNotNull(document);
		
		Language cpp = new Language(ELanguage.Cpp);
		String result = applyGrammer("", document, cpp);
		assertEquals("", result);
	}
	
	@Test
	public void codeTest() {
		Document document = parseDocument("structure foo {}");
		assertNotNull(document);
		
		Language cpp = new Language(ELanguage.Cpp);
		String result = applyGrammer("<? class .name {} ?>", document, cpp);
		assertEquals("class .name {}", result);
	}
	
	@Test
	public void codeLanguageTest() {
		Document document = parseDocument("structure foo {}");
		assertNotNull(document);
		
		Language cpp = new Language(ELanguage.Cpp);
		String result = applyGrammer("<?cpp class .name {} ?>", document, cpp);
		assertEquals("class .name {}", result);
	}

	@Test
	public void codeAnotherLanguageTest() {
		Document document = parseDocument("structure foo {}");
		assertNotNull(document);
		
		Language cpp = new Language(ELanguage.Cpp);
		String result = applyGrammer("<?java class .name {} ?>", document, cpp);
		assertEquals("", result);
	}
}