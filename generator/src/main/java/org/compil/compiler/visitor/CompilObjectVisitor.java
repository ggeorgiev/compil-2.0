package org.compil.compiler.visitor;

import org.compil.compiler.model.CompilObject;
import org.compil.parser.idl.compil.ModelBaseVisitor;
import org.compil.parser.idl.compil.ModelParser;

public class CompilObjectVisitor extends ModelBaseVisitor<CompilObject> {
    @Override
    public CompilObject visitDocument(ModelParser.DocumentContext ctx) {
        CompilDocumentVisitor visitor = new CompilDocumentVisitor();
        return visitor.visit(ctx);
    }

    @Override
    public CompilObject visitStructure(ModelParser.StructureContext ctx) {
        CompilStructureVisitor visitor = new CompilStructureVisitor();
        return visitor.visit(ctx);
    }
}
