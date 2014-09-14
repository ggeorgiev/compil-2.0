package org.compil.parser.template.language;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CppTest {
	String foreachDump(String input) {
		ANTLRInputStream inputStream = new ANTLRInputStream(input);
		CppLexer lexer = new CppLexer(inputStream);
		CommonTokenStream stream = new CommonTokenStream(lexer);
		CppParser parser = new CppParser(stream);
		ParseTree tree = parser.document();
		CppVisitorImpl visitor = new CppVisitorImpl();
		return visitor.visit(tree);
	}

	@BeforeClass
	public void setUp() {
		// code that will be invoked when this test is instantiated
	}

	@Test
	public void sanityTest() {
	}
}
