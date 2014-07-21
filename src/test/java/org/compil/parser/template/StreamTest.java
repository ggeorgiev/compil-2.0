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

	String serializeDump(String input) {
		ANTLRInputStream inputStream = new ANTLRInputStream(input);
		StreamLexer lexer = new StreamLexer(inputStream);
		CommonTokenStream stream = new CommonTokenStream(lexer);
		StreamParser parser = new StreamParser(stream);
		ParseTree tree = parser.serialize();
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
			"VAR/file.txt",
			"VAR/foo/bar/file.txt",
			"VAR/path/stream/file.txt",
		};
		
		for (String path : paths) {
			System.out.print("parsing " + path + "\n");
			String dump = pathDump(path);
			assertEquals(path, dump);
		}
	}

	@Test
	public void streamTest() {
		String dump = streamDump("stream foo { path: VAR/file.txt; }");
		assertEquals("stream foo {\n    path: VAR/file.txt;\n}\n", dump);
	}
	
	@Test
	public void serializeEmptyBlockTest() {
		String dump = serializeDump("foo:");
		assertEquals("foo:", dump);
	}

	@Test
	public void serializeBlockTest() {
		String dump = serializeDump("foo:\n<bar");
		assertEquals("foo:\n<bar", dump);
	}
}
