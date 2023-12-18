grammar LExpr;

// labels are used to identify the different alternatives
e : e MULT e # Mult
  | e ADD e # Add
  | INT # Int
  ;

MULT: '*' ;
ADD : '+' ;
INT : [0-9]+ ;
WS : [ \t\n]+ -> skip ;
