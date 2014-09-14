package org.compil.parser.template.language;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class CppTest {
	String documentDump(String input) {
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
		String dump = documentDump("<~class A {}~>");
		AssertJUnit.assertEquals("<~class A\n{\n}\n~>", dump);
	}
}
