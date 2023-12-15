// Just for java language

//grammar PropertyFile;
//@members {
//    void startFile() { } // blank implementations
//    void finishFile() { }
//    void defineProperty(Token name, Token value) { }
//}
//file : {startFile();} prop+ {finishFile();} ;
//prop : ID '=' STRING '\n' {defineProperty($ID, $STRING)} ;
//ID : [a-z]+ ;
//STRING : '"' .*? '"' ;

grammar PropertyFile;
file : prop+ ;
prop : ID '=' STRING '\n' ;
ID   : [a-z]+ ;
STRING : '"' .*? '"' ;
