grammar Grammar;

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
	:	KW_FOREACH property 
		statement
	;
	
selectionStatement
	:	when
	;

when
	:	KW_WHEN type
		statement
	;
	
blockStatementItemList
    :   statement
    |   blockStatementItemList statement
    ;
	
compoundStatement
    :   LEFT_BRACE blockStatementItemList? RIGHT_BRACE
    ;

codeStatement
	:	LEFT_CODE_BRACE
		(language WS)?
		.*?
		RIGHT_CODE_BRACE
	;
	
language
	:	KW_LANGUAGE_CPP
	|	KW_LANGUAGE_JAVA
	;
	
property
	:	DOT (IDENTIFIER | property)
	;
	
type
	:	IDENTIFIER
	;
	
DOT:					'.';	
LEFT_BRACE:             '{';
RIGHT_BRACE:            '}';
LEFT_CODE_BRACE:        '<?';
RIGHT_CODE_BRACE:       '?>';

KW_FOREACH:             'foreach';
KW_WHEN:				'when';
KW_LANGUAGE_CPP:        'cpp';
KW_LANGUAGE_JAVA:       'java';

IDENTIFIER
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