grammar Template;

foreach
	:	KW_FOREACH property 
		LEFT_BRACE 
		RIGHT_BRACE
	;
	
property
	:	DOT (PROPERTY_NAME | property)
	;  	

DOT:					'.';	
LEFT_BRACE:             '{';
RIGHT_BRACE:            '}';

KW_FOREACH:             'foreach';

PROPERTY_NAME
	: ID
	;

fragment
ID
    :   LETTER (LETTER|ID_DIGIT)*
    ;
    
SPACING
	:	WS -> skip
	;
	
COMMENT
	:	'#' .*? ( EOL | EOF ) -> skip
	;
	
WS
    :   (LINE_WS | EOL)
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
    
fragment    
LINE_WS
    :   ' '
    |	'\t'
    |	'\u000C'
    ;