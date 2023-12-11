lexer grammar CommonLexerRules; // note that lexer rules come first

ID : [a-zA-Z]+ ;
INT : [0-9]+ ;
NEWLINE: '\r'? '\n' ;
WS : [ \t]+ -> skip; // toss out whitespace
CLEAR : 'clear' ;
