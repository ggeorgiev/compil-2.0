package org.compil.compiler.model;

import static org.testng.AssertJUnit.assertEquals;

import org.compil.compiler.model.property.Property;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CompilObectTest {

	class MyCompilObject extends CompilObject {
		String foo = "";

		public String getBaseName() {
			return "base";
		}
	}

	class MyPropertyFactory implements IPropertyFactory {
		@Override
		public boolean hasProperty(CompilObject obj,
								   String property){
			if (obj instanceof MyCompilObject) {
				if (property.equals("foo")) {
					return true;
				}
			}
			return false;
		}

		@Override
		public Property getProperty(CompilObject obj, String property) {
			return null;
		}
	}

	@BeforeClass
	public void setUp() {
	}

	@Test
	public void propertyNameTest() {
		MyCompilObject object = new MyCompilObject();
		IPropertyFactory propertyFactory = new MyPropertyFactory();

		assertEquals("base.foo", object.getPropertyName(propertyFactory, ".foo"));
	}
}
