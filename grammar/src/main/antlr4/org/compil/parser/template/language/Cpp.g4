grammar Cpp;

import Shared;

document
	:	definition*
		(LEFT_CODE_BRACE
		statement*
		RIGHT_CODE_BRACE)?
	;

definition
	: (~LEFT_CODE_BRACE)
	;


statement
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
