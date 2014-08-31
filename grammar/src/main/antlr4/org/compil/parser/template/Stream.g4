grammar Stream;

serialize
	:	streamName COLON
		serializeBlock
	;

serializeBlock
	:	serializeLine*
	;

serializeLine
	:	SERIALIZE_LINE
	;

stream
	:	KW_STREAM streamName LEFT_BRACE
		KW_PATH COLON path SEMICOLON
		RIGHT_BRACE
	;

streamName
	:	IDENTIFIER
	;

path
	:	variable filepath
	;

filepath
	:	FILEPATH
	;

variable
	:	CONSTANT_IDENTIFIER
	;

SEMICOLON:           ';';
COLON:               ':';
FS_DELIMITER:        '/';
LEFT_BRACE:          '{';
RIGHT_BRACE:         '}';
SERIALIZE_BRACKET:   '<';

SERIALIZE_LINE
	:	EOL SERIALIZE_BRACKET ~[\n\r]*
	;

FILEPATH
	:	(FS_DELIMITER (FS_ALLOWED_CHARACTERS)+)+
	;

KW_PATH:				'path';
KW_STREAM:				'stream';

CONSTANT_IDENTIFIER
	:	UPPERCASE_LETTER (UPPERCASE_LETTER|ID_DIGIT)*
	;

IDENTIFIER
	:	LETTER (LETTER|ID_DIGIT)*
	;

SPACING
	:	WS -> skip
	;

COMMENT
	:	'#' .*? ( EOL | EOF ) -> skip
	;

WS
	:	(LINE_WS | EOL)
	;

fragment
FS_ALLOWED_CHARACTERS
	:	~[<>:;"/|?*{}"'\\];

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
UPPERCASE_LETTER
	:	'_'
	|	'A'..'Z'
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
