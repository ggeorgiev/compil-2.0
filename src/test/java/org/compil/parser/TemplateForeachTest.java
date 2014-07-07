package org.compil.parser;

import static org.testng.AssertJUnit.assertEquals;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.testng.annotations.*;

public class TemplateForeachTest {

	String structureDump(String input) {
		ANTLRInputStream inputStream = new ANTLRInputStream(input);
		TemplateLexer lexer = new TemplateLexer(inputStream);
		CommonTokenStream stream = new CommonTokenStream(lexer);
		TemplateParser parser = new TemplateParser(stream);
		ParseTree tree = parser.foreach();
		TemplateVisitorImpl visitor = new TemplateVisitorImpl();
		return visitor.visit(tree);
	}
	
	@BeforeClass
	public void setUp() {
		// code that will be invoked when this test is instantiated
	}

	@Test
	public void sanityTest() {
		String dump = structureDump("foreach .blah {}");
		assertEquals("foreach .blah\n{\n}\n", dump);
	}
}
