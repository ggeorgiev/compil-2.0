grammar Grammar;

document
	:	(statement WS*)*
	;

statement
	:	compoundStatement
	|	iterationStatement
	|	selectionStatement
	|	codeStatement
	;

iterationStatement
	:	foreach
	;

foreach
	:	KW_FOREACH WS+
		objects WS*
		statement
	;
	
objects
	:	DOT (IDENTIFIER | objects)
	;

selectionStatement
	:	when
	;

when
	:	KW_WHEN WS+
		type WS*
		statement
	;

blockStatementItemList
	:	blockStatementItemList WS*
		statement
	|	statement
	;

compoundStatement
	:	LEFT_BRACE WS*
		(blockStatementItemList WS*)?
		RIGHT_BRACE
	;

codeStatement
	:	LEFT_CODE_BRACE (language WS)? WS*
		codeList WS*
		RIGHT_CODE_BRACE
	;

language
	:	KW_LANGUAGE_CPP
	|	KW_LANGUAGE_JAVA
	;

codeList
	:	code codeWhitespace?
		codeList
	|	code
	;

code
	:	codeExpression
	|	~RIGHT_CODE_BRACE
	;

codeExpression
	:	GRAVE_ACCENT
		property
		GRAVE_ACCENT
	;

codeWhitespace
	:	WS+
	;

property
	:	DOT (IDENTIFIER | property)
	;

type
	:	IDENTIFIER
	;

DOT:				 '.';
LEFT_BRACE:			 '{';
RIGHT_BRACE:		 '}';

LEFT_CODE_BRACE:	 '<~';
RIGHT_CODE_BRACE:	 '~>';

GRAVE_ACCENT:		 '`';

KW_FOREACH:			 'foreach';
KW_WHEN:			 'when';

KW_LANGUAGE_CPP:	 'cpp';
KW_LANGUAGE_JAVA:	 'java';

IDENTIFIER
	:	LETTER (LETTER|ID_DIGIT)*
	;

COMMENT
	:	'#' .*? ( EOL | EOF ) -> skip
	;

WS
	:	(LINE_WS | EOL)
	;

fragment
EOL
	:	('\n' | '\r\n')
	;

fragment
LETTER
	:	'_'
	|	'A'..'Z'
	|	'a'..'z'
	;

fragment
ID_DIGIT
	:	'0'..'9'
	;

fragment
LINE_WS
	:	' '
	|	'\t'
	|	'\u000C'
	;
