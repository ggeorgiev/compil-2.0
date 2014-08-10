package org.compil.compiler.annotation;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import org.compil.compiler.model.PropertyFactory;
import org.compil.compiler.model.Structure;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PropertyFactoryTest {
	@BeforeClass
	public void setUp() {
		// code that will be invoked when this test is instantiated
	}

	@Test
	public void hasPropertyTest() {
		Structure structure = new Structure();
		assertTrue(PropertyFactory.hasProperty(structure, "name"));
		assertFalse(PropertyFactory.hasProperty(structure, "blah"));
	}
}
