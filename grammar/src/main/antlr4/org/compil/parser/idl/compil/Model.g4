grammar Model;

document
    :   structure*
    ;

objectComment
    :   OBJECT_COMMENT
    |   // always optional
    ;

structure
    :   objectComment
        KW_STRUCTURE name
        LEFT_BRACE
        RIGHT_BRACE
    ;

name
    :   IDENTIFIER
    ;

LEFT_BRACE:             '{';
RIGHT_BRACE:            '}';

KW_STRUCTURE:           'structure';

IDENTIFIER
    :   LETTER (LETTER|ID_DIGIT)*
    ;

OBJECT_COMMENT
    :   OBJECT_BLOCK_COMMENT
    |   OBJECT_LINE_BLOCK_COMMENT
    ;

OBJECT_BLOCK_COMMENT
    :   LINE_WS* '/*' .*? '*/'
    ;

OBJECT_LINE_BLOCK_COMMENT
    :   (LINE_WS*? OBJECT_LINE_COMMENT)+
    ;

fragment
OBJECT_LINE_COMMENT
    :   '//' .*? ( EOL | EOF )
    ;

SPACING
    :   WS -> skip
    ;

COMMENT
    :   '#' .*? ( EOL | EOF ) -> skip
    ;

WS
    :   (LINE_WS | EOL)
    ;

fragment
EOL
    :   ('\n' | '\r\n')
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
    |   '\t'
    |   '\u000C'
    ;
