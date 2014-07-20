package org.compil.parser.template;

import static org.testng.AssertJUnit.assertEquals;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StreamTest {
	String pathDump(String input) {
		ANTLRInputStream inputStream = new ANTLRInputStream(input);
		StreamLexer lexer = new StreamLexer(inputStream);
		CommonTokenStream stream = new CommonTokenStream(lexer);
		StreamParser parser = new StreamParser(stream);
		ParseTree tree = parser.path();
		StreamVisitorImpl visitor = new StreamVisitorImpl();
		return visitor.visit(tree);
	}

	String streamDump(String input) {
		ANTLRInputStream inputStream = new ANTLRInputStream(input);
		StreamLexer lexer = new StreamLexer(inputStream);
		CommonTokenStream stream = new CommonTokenStream(lexer);
		StreamParser parser = new StreamParser(stream);
		ParseTree tree = parser.stream();
		StreamVisitorImpl visitor = new StreamVisitorImpl();
		return visitor.visit(tree);
	}

	@BeforeClass
	public void setUp() {
		// code that will be invoked when this test is instantiated
	}

	@Test
	public void pathTest() {
		String paths[] = {
			"/",
			"/foo/bar",
			"foo",
		};
		
		for (String path : paths) {
			System.out.print("parsing " + path + "\n");
			String dump = pathDump(path);
			assertEquals(path, dump);
		}
	}
/*
	@Test
	public void sanityTest() {
		String dump = streamDump("stream Foo path: bar ;");
		//assertEquals("", dump);
	}
*/
	
}
