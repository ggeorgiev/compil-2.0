package org.compil.compiler.annotation;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes("org.compil.compiler.annotation.Property")
public class PropertyAnnotationProcessor extends AbstractProcessor {

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latest();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		if (roundEnv.processingOver())
			return true;

		ProcessingEnvironment env = super.processingEnv;
		Filer filer = env.getFiler();

		String pkgName = "org.compil.compiler.model";
		String clsName = "PropertyFactory";

		try {
			JavaFileObject javaFile = filer.createSourceFile(pkgName + "." + clsName,
															 (Element[]) null);


			Writer writer = javaFile.openWriter();
			PrintWriter pw = new PrintWriter(writer);
			Formatter formatter = new Formatter(pw);

			formatter.format("package %s;\n", pkgName);
			pw.println();

			formatter.format("public class %s implements IPropertyFactory {", clsName);
			pw.println();

			HashMap<TypeElement, List<VariableElement>> map =
					new HashMap<TypeElement, List<VariableElement>>();

			for (TypeElement te : annotations) {
				for (Element element : roundEnv.getElementsAnnotatedWith(te)) {
					VariableElement field = (VariableElement)element;
					TypeElement cls = (TypeElement)field.getEnclosingElement();

					if (!map.containsKey(cls)) {
						map.put(cls, new ArrayList<VariableElement>());
					}

					List<VariableElement> list = map.get(cls);
					list.add(field);
				}
			}

			pw.println("    public boolean hasProperty(CompilObject obj,\n" +
					   "                               String property) {");

			for (TypeElement cls : map.keySet()) {
				formatter.format("        if (obj instanceof %s) {\n",
								 cls.getSimpleName());

				List<VariableElement> list = map.get(cls);
				for (VariableElement field : list) {
					formatter.format("            if (property.equals(\"%s\")) {\n",
									 field.getSimpleName());
					pw.println("                return true;");
					pw.println("            }");
				}

				pw.println("        }");
			}

			pw.println("        return false;");
			pw.println("    }");

			pw.println("}");
			pw.println();

			formatter.close();
		} catch (javax.annotation.processing.FilerException e) {
			// when the project is build with maven it runs the annotation generation
			// more than once. This handles the second attempt - ignoring it
			if (!e.getMessage().startsWith("Attempt to recreate a file")) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}

}
