grammar Tags;
file : (TAG|ENTITY|TEXT|CDATA)* ; // indicate tokens kind

COMMENT : '<!--' .*? '-->' -> skip ;
CDATA : '<![CDATA[' .*? ']]>' ;
TAG : '<' .*? '>' ; // must come after other tag-like structures
ENTITY : '&' .*? ';' ;
TEXT : ~[<&]+ ;     // any sequence of chars except < and & chars
