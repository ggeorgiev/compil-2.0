package org.compil.parser.template;


public class StreamVisitorImpl extends StreamBaseVisitor<String> {

	@Override
	public String visitStreamName(StreamParser.StreamNameContext ctx) {
		return ctx.getText();
	}

	@Override
	public String visitSerializeLine(StreamParser.SerializeLineContext ctx) {
		return ctx.getText();
	}

	@Override
	public String visitSerializeBlock(StreamParser.SerializeBlockContext ctx) {
		String result = "";

		if (ctx.serializeLine() != null) {
			for (StreamParser.SerializeLineContext line : ctx.serializeLine()) {
				result += visitSerializeLine(line);
			}
		}
		return result;
	}

	@Override
	public String visitSerialize(StreamParser.SerializeContext ctx) {
		String result = visitStreamName(ctx.streamName()) + ctx.COLON();
		result += visitSerializeBlock(ctx.serializeBlock());
		return result;
	}

	@Override
	public String visitFilepath(StreamParser.FilepathContext ctx) {
		return ctx.getText();
	}

	@Override
	public String visitVariable(StreamParser.VariableContext ctx) {
		return ctx.getText();
	}

	@Override
	public String visitPath(StreamParser.PathContext ctx) {
		String result = visitVariable(ctx.variable());
		result += visitFilepath(ctx.filepath());
		return result;
	}

	@Override
	public String visitStream(StreamParser.StreamContext ctx) {
		String result = ctx.KW_STREAM() + " " +
						visitStreamName(ctx.streamName()) + " " + ctx.LEFT_BRACE() + "\n";
		result += "    " + ctx.KW_PATH() + ctx.COLON() + " " +
				  visitPath(ctx.path()) + ctx.SEMICOLON()  + "\n";
		result += ctx.RIGHT_BRACE() + "\n";
		return result;
	}
}
