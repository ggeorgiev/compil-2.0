package org.compil.parser.template;

public class AlignerVisitorImpl extends AlignerBaseVisitor<String>{
    @Override
    public String visitLine(AlignerParser.LineContext ctx)
    {
        if (ctx.emptyLine() != null)
            return visitEmptyLine(ctx.emptyLine());
        if (ctx.codeLine() != null)
            return visitCodeLine(ctx.codeLine());

        return "<unknown line type>";
    }

    @Override
    public String visitEmptyLine(AlignerParser.EmptyLineContext ctx) {
        return "empty line\n";
    }

    @Override
    public String visitCodeLine(AlignerParser.CodeLineContext ctx) {
        String result = ctx.KW_LINE().getText() + ": ";
        result += visitIndent(ctx.indent()) + ": ";
        result += ctx.code().getText() + "\n";
        return result;
    }

    @Override
    public String visitIndent(AlignerParser.IndentContext ctx) {
        if (ctx.actionIndent() != null)
            return visitActionIndent(ctx.actionIndent());
        return ctx.getText();
    }

    @Override
    public String visitActionIndent(AlignerParser.ActionIndentContext ctx) {
        if (ctx.pushIndent() != null)
            return visitPushIndent(ctx.pushIndent());
        return ctx.getText();
    }

    @Override
    public String visitPushIndent(AlignerParser.PushIndentContext ctx) {
        String result = ctx.KW_PUSH().getText();
        if (ctx.expressionIndent() != null)
            result += " " + visitExpressionIndent(ctx.expressionIndent());

        return result;
    }

    @Override
    public String visitExpressionIndent(AlignerParser.ExpressionIndentContext ctx) {
        return ctx.getText();
    }


}
