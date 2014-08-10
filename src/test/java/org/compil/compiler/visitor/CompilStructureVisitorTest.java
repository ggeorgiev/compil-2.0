package org.compil.compiler.visitor;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.compil.compiler.model.Structure;
import org.compil.compiler.model.PropertyFactory;
import org.compil.parser.CompilLexer;
import org.compil.parser.CompilParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CompilStructureVisitorTest {
	Structure parseStructure(String input) {
		ANTLRInputStream inputStream = new ANTLRInputStream(input);
		CompilLexer lexer = new CompilLexer(inputStream);
		TokenStream stream = new CommonTokenStream(lexer);
		CompilParser parser = new CompilParser(stream);
		ParseTree tree = parser.structure();
		
		CompilStructureVisitor visitor = new CompilStructureVisitor();
		return visitor.visit(tree);
	}

	@BeforeClass
	public void setUp() {
		// code that will be invoked when this test is instantiated
	}

	@Test
	public void sanityTest() {
		Structure structure = parseStructure("structure foo {}");
		assertNotNull(structure);
		assertEquals("foo", structure.getName());
		
		PropertyFactory factory = new PropertyFactory();
	}
}
