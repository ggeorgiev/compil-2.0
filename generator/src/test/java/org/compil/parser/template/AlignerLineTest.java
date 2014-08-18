package org.compil.parser.template;

import static org.testng.AssertJUnit.assertEquals;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AlignerLineTest {
    String lineDump(String input) {
        ANTLRInputStream inputStream = new ANTLRInputStream(input);
        AlignerLexer lexer = new AlignerLexer(inputStream);
        CommonTokenStream stream = new CommonTokenStream(lexer);
        AlignerParser parser = new AlignerParser(stream);
        ParseTree tree = parser.line();
        AlignerVisitorImpl visitor = new AlignerVisitorImpl();
        return visitor.visit(tree);
    }

    String indentDump(String input) {
        ANTLRInputStream inputStream = new ANTLRInputStream(input);
        AlignerLexer lexer = new AlignerLexer(inputStream);
        CommonTokenStream stream = new CommonTokenStream(lexer);
        AlignerParser parser = new AlignerParser(stream);
        ParseTree tree = parser.indent();
        AlignerVisitorImpl visitor = new AlignerVisitorImpl();
        return visitor.visit(tree);
    }

    @BeforeClass
    public void setUp() {
        // code that will be invoked when this test is instantiated
    }

    @Test
    public void emptyLineAtEOFTest() {
        String dump = lineDump("\n");
        assertEquals("empty line\n", dump);
    }

    @Test
    public void emptyLineTextAtEOFTest() {
        String dump = lineDump("empty line\n");
        assertEquals("empty line\n", dump);
    }

    @Test
    public void lineAtEOFTest() {
        String dump = lineDump("line: 0: foo");
        assertEquals("line: 0: foo\n", dump);
    }

    @Test
    public void lineWithEndTest() {
        String dump = lineDump("line: 0: foo\n");
        assertEquals("line: 0: foo\n", dump);
    }

    @Test
    public void absoluteIndentTest() {
        String dump = indentDump("0");
        assertEquals("0", dump);
    }

    @Test
    public void relativeIndentTest() {
        String dump = indentDump("+0");
        assertEquals("+0", dump);
    }

    @Test
    public void pushIndentTest() {
        String dump = indentDump("push");
        assertEquals("push", dump);
    }

    @Test
    public void pushPlusIndentTest() {
        String dump = indentDump("push 0");
        assertEquals("push 0", dump);
    }

    @Test
    public void popIndentTest() {
        String dump = indentDump("pop");
        assertEquals("pop", dump);
    }
}
