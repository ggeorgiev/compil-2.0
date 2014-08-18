package org.compil.compiler.visitor;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.compil.compiler.model.CompilObject;
import org.compil.compiler.model.Document;
import org.compil.parser.idl.compil.ModelLexer;
import org.compil.parser.idl.compil.ModelParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CompilDocumentVisitorTest {
	Document parseDocument(String input) {
		ANTLRInputStream inputStream = new ANTLRInputStream(input);
		ModelLexer lexer = new ModelLexer(inputStream);
		TokenStream stream = new CommonTokenStream(lexer);
		ModelParser parser = new ModelParser(stream);
		ParseTree tree = parser.document();

		CompilDocumentVisitor visitor = new CompilDocumentVisitor();
		return visitor.visit(tree);
	}

	@BeforeClass
	public void setUp() {
		// code that will be invoked when this test is instantiated
	}

	@Test
	public void sanityTest() {
		Document document = parseDocument("structure foo {}");
		assertNotNull(document);

		List<CompilObject> objects = document.getObjects();
		assertEquals(1, objects.size());
	}
}
