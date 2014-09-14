package org.compil.generator;

public class Language {
	public enum ELanguage {
		Undefined,
		Cpp,
		Java
	}

	private ELanguage value = ELanguage.Undefined;

	public Language(ELanguage value) {
		this.value = value;
	}

	public Language(String name) {
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

	public boolean equals(Language language) {
		return value == language.value;
	}

}
