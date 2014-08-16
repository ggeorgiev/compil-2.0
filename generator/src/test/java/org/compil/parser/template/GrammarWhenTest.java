package org.compil.parser.template;

import static org.testng.AssertJUnit.assertEquals;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.testng.annotations.*;

public class GrammarWhenTest {

	String whenDump(String input) {
		ANTLRInputStream inputStream = new ANTLRInputStream(input);
		GrammarLexer lexer = new GrammarLexer(inputStream);
		CommonTokenStream stream = new CommonTokenStream(lexer);
		GrammarParser parser = new GrammarParser(stream);
		ParseTree tree = parser.when();
		GrammarVisitorImpl visitor = new GrammarVisitorImpl();
		return visitor.visit(tree);
	}
	
	@BeforeClass
	public void setUp() {
		// code that will be invoked when this test is instantiated
	}

	@Test
	public void sanityTest() {
		String dump = whenDump("when structure {}");
		assertEquals("when structure\n{\n}\n", dump);
	}
}
