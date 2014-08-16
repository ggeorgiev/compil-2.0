grammar Aligner;

line
	:	emptyLine
	|	codeLine
	;
	
emptyLine
	:	( KW_EMPTY KW_LINE )?
		( EOL | EOF )
	;

codeLine
	:	KW_LINE COLON
		indent COLON
		code
		( EOL | EOF )
	;
	
code
	:	CODE*
	;
	
indent
	:	actionIndent
	|	expressionIndent
	;
	
actionIndent
	: pushIndent
	| popIndent
	;
	
pushIndent
	:	KW_PUSH
		expressionIndent?
	;

popIndent
	:	KW_POP
	;
	
expressionIndent
	:	absoluteIndent
	|	relativeIndent
	;
	
absoluteIndent
	:	NUMBER
	;
	
relativeIndent
	:	sign NUMBER
	;
	
sign
	:	PLUS
	|	MINUS
	;
	
COLON:					':';
MINUS:					'-';
PLUS:					'+';

KW_EMPTY:				'empty';
KW_LINE:				'line';
KW_PUSH:				'push';
KW_POP: 				'pop';

NUMBER
	:	DIGIT+
	;

EOL
	:	('\n' | '\r\n')
	;
    
SPACING
	:	LINE_WS -> skip
	;
	
CODE
	:	(~('\n' | '\r'))
	;

fragment
DIGIT
    :   '0'..'9'
    ;

fragment   
LINE_WS
    :   ' '
    |	'\t'
    |	'\u000C'
    ;