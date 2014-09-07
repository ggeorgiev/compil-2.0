grammar Cpp;

import Shared;

document
	:	(~LEFT_CODE_BRACE)?
		(LEFT_CODE_BRACE
		statment*
		RIGHT_CODE_BRACE)?
	;
	
statment
	:	cppClass
	;

cppClass
	:	KW_CLASS name
		LEFT_BRACE
		RIGHT_BRACE
	;

name
	:	IDENTIFIER
	|	PROPERTY
	;

LEFT_BRACE:				'{';
RIGHT_BRACE:			'}';

KW_CLASS:				'class';
