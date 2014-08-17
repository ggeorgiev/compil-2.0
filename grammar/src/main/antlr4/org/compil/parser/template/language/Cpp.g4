grammar Cpp;

import Shared;

cppClass
    :   KW_CLASS name
        LEFT_BRACE
        RIGHT_BRACE
    ;
    
name
    :   IDENTIFIER
    |   PROPERTY
    ;
    
LEFT_BRACE:             '{';
RIGHT_BRACE:            '}';

KW_CLASS:               'class';