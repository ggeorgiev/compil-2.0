package org.compil.parser.template;


public class StreamVisitorImpl extends StreamBaseVisitor<String> {
	@Override
	public String visitStream(StreamParser.StreamContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitDirectory(StreamParser.DirectoryContext ctx) {
		return ctx.getText();
	}

	@Override
	public String visitPath(StreamParser.PathContext ctx) {
		String result = "";
		
		if (ctx.absoluteDirectory() != null)
			result += visitAbsoluteDirectory(ctx.absoluteDirectory());
		
		if (ctx.directory() != null)
			result += visitDirectory(ctx.directory());
		
		return result;
	}
	
	@Override
	public String visitAbsoluteDirectory(StreamParser.AbsoluteDirectoryContext ctx) {
		String result = "/";
		if (ctx.directory() != null)
			result += visitDirectory(ctx.directory());
		return result;
	}

}
