lexer grammar Shared;

LEFT_CODE_BRACE:	 '<~';
RIGHT_CODE_BRACE:	 '~>';

PROPERTY
	:	DOT+ IDENTIFIER
	;

DOT
	:	'.'
	;

IDENTIFIER
	:	LETTER (LETTER|ID_DIGIT)*
	;

SPACING
	:	WS -> skip
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
