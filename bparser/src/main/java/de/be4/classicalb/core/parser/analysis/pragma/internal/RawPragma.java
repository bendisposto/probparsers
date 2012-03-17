package de.be4.classicalb.core.parser.analysis.pragma.internal;

import de.hhu.stups.sablecc.patch.SourcePosition;

public class RawPragma {
	private final String text;
	private final SourcePosition start;
	private final SourcePosition end;

	public RawPragma(SourcePosition startPos, SourcePosition endPos,
			String pragmaText) {
		start = startPos;
		end = endPos;
		text = pragmaText;
	}

	@Override
	public String toString() {
		return text +" "+ start.toString() +"-"+ end.toString();
	}

	public SourcePosition getEnd() {
		return end;
	}

	public SourcePosition getStart() {
		return start;
	}

	public String getText() {
		return text;
	}

}