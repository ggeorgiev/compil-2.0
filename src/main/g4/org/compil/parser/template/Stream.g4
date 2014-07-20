grammar Stream;

stream
	:	KW_STREAM IDENTIFIER
		KW_PATH ':' path
		';'
	;
	
path
	: absoluteDirectory
	| directory
	;
	
absoluteDirectory
	:	'/' directory
	|	'/'
	;	
	
directory
    :	FS_NAME '/' directory
	|	FS_NAME
	;

KW_PATH:				'path';
KW_STREAM:              'stream';

IDENTIFIER
    :   LETTER (LETTER|ID_DIGIT)*
    ;
    
FS_NAME
	:	LETTER+
	;
    
fragment
FS_DONOTUSE_CHARACTERS
	:	'<'
	|	'>'
	|	':'
	|	';'
	|	'"'
	|	'\''
	|	'/'
	|	'\\'
	|	'|'
	|	'?'
	|	'*'
	;
    
fragment	
EOL
	:	('\n' |	'\r\n')
	;
    
fragment
LETTER
    :   '_'
    |   'A'..'Z'
    |   'a'..'z'
    ;
    
fragment
ID_DIGIT
    :   '0'..'9'
    ;