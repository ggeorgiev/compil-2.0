package org.compil.parser;

import org.testng.annotations.*;

public class TemplateTest {

	@BeforeClass
	public void setUp() {
		// code that will be invoked when this test is instantiated
	}

	@Test
	public void sanityTest() {
		TemplateParser parser = new TemplateParser(null);
		parser.reset();
	}
}
