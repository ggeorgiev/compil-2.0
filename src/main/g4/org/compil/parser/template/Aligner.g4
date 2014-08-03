grammar Aligner;

line
	:	KW_LINE COLON
		indent
		.*?
		( EOL | EOF )
	;
	
indent
	:	absolute_indent
	|	relative_indent
	;
	
absolute_indent
	:	NUMBER
	;
	
relative_indent
	:	sign NUMBER
	;
	
sign
	:	PLUS
	|	MINUS
	;
	
COLON:					':';
MINUS:					'-';
PLUS:					'+';

KW_LINE:				'line';

NUMBER
	:	ID_DIGIT+
	;
    
fragment
EOL
	:	('\n' |	'\r\n')
	;
    
fragment
ID_DIGIT
    :   '0'..'9'
    ;
