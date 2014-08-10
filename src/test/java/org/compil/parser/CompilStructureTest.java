package org.compil.parser;

import static org.testng.AssertJUnit.assertEquals;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.testng.annotations.*;

public class CompilStructureTest {
	
	String structureDump(String input) {
		ANTLRInputStream inputStream = new ANTLRInputStream(input);
		CompilLexer lexer = new CompilLexer(inputStream);
		TokenStream stream = new CommonTokenStream(lexer);
		CompilParser parser = new CompilParser(stream);
		ParseTree tree = parser.structure();
		
		CompilVisitorImpl visitor = new CompilVisitorImpl();
		return visitor.visit(tree);
	}

	@BeforeClass
	public void setUp() {
		// code that will be invoked when this test is instantiated
	}

	@Test
	public void commentTest() {
		String dump = structureDump("// comment\nstructure sname{}");
		assertEquals("structure sname\n{\n}\n", dump);
	}
	
	@Test
	public void commentSpaceTest() {
		String dump = structureDump("// comment\n  structure sname{}");
		assertEquals("structure sname\n{\n}\n", dump);
	}
}
