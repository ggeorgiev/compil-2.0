package org.compil.generator;

public class Language {
	enum ELanguage {
		Undefined,
		Cpp,
		Java
	}
	
	private ELanguage value = ELanguage.Undefined; 

	Language(String name) {
		if (name.equalsIgnoreCase("cpp")) {
			value = ELanguage.Cpp;
		}
		else if (name.equalsIgnoreCase("java")) {
			value = ELanguage.Java;
		}
		else {
			throw new IllegalArgumentException("the language name '" + name + "'is unknown"); 
		}
	}
	
	boolean equals(Language language) {
		return value == language.value;
	}
	
}
