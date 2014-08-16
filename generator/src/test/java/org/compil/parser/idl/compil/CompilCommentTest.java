package org.compil.parser.idl.compil;

import static org.testng.AssertJUnit.assertEquals;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.testng.annotations.*;

public class CompilCommentTest {
	
	TokenStream tokenStream(String input) {
		ANTLRInputStream inputStream = new ANTLRInputStream(input);
		ModelLexer lexer = new ModelLexer(inputStream);
		return new CommonTokenStream(lexer);
	}

	@BeforeClass
	public void setUp() {
		// code that will be invoked when this test is instantiated
	}

	@Test
	public void commentTest() {
		String[] strings = {
				"//test",
				"//test\n",
				"    //  test\n",
				"    //  test1\n    //  test2\n",
				"/* test */",
				"    /* test */",
				"    /* test1 \n test2 */",
		};
		
		
		for (String string : strings)
		{
			//System.out.print("testing: <" + string + ">\n");
			
			TokenStream stream = tokenStream(string);
			ModelParser parser = new ModelParser(stream);
			
			ParseTree tree = parser.objectComment();
			
			assertEquals(string, tree.getText());
		}
	}
}
