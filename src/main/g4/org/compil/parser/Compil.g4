grammar Compil;

document
	:	structure*
	;
	
comment
	:   block_comment
	|	line_block_comment
	;
	
block_comment
	:   LINE_WS* BLOCK_COMMENT
	;
	
line_block_comment
	:	line_comment+
	;     
	
line_comment
	:	LINE_WS* LINE_COMMENT
	;     

structure
	:	comment? KW_STRUCTURE LEFT_BRACE RIGHT_BRACE
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

LEFT_BRACE:             '{';
RIGHT_BRACE:            '}';

KW_STRUCTURE:           'structure';

ID
    :   LETTER (LETTER|ID_DIGIT)*
    ;
    
LINE_WS
    :   (' ' | '\t' | '\u000C' )
    ;
    
EOL
	:	('\n' |	'\r\n')
	;
    
WS
    :   (LINE_WS | EOL)
    ;
    
BLOCK_COMMENT
    :   '/*' .*? '*/'
    ;

LINE_COMMENT
    :   '//' .*? ( EOL | EOF )
    ;
    
SPACING
	:	WS -> channel(HIDDEN)
	;
	
COMMENT
	:	BLOCK_COMMENT -> channel(HIDDEN)
	;
	
INLINE_COMMENT
	:	LINE_COMMENT -> channel(HIDDEN)
	;		