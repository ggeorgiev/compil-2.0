package org.compil.compiler.model;

import static org.testng.AssertJUnit.assertEquals;

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
	    public boolean hasProperty(CompilObject obj,
	                               String property){
	        if (obj instanceof MyCompilObject) {
	            if (property.equals("foo")) {
	                return true;
	            }
	        }
	        return false;
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
