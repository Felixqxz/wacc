lexer grammar WaccLexer;

COMMENT : '#' ~[\r\n]* [\r\n] -> skip ;
WS : [ \n\r\t]+ -> channel(HIDDEN) ;

BEGIN : 'begin' ;
DEFINE : 'define' ;
END : 'end' ;
IS : 'is' ;

SKIPP : 'skip' ;
BREAK : 'break' ;
CONTINUE : 'continue' ;
READ : 'read' ;
FREE : 'free' ;
RETURN : 'return' ;
EXIT : 'exit' ;
PRINT : 'print' ;
PRINTLN : 'println';
IF : 'if' ;
THEN : 'then' ;
ELSE : 'else' ;
FI : 'fi' ;
FOR : 'for' ;
WHILE : 'while' ;
SWITCH : 'switch' ;
CASE : 'case' ;
DEFAULT : 'default' ;
DO : 'do' ;
DONE : 'done' ;

NEWPAIR : 'newpair' ;
CALL : 'call' ;

FST : 'fst' ;
SND : 'snd' ;

INT : 'int' ;
BOOL : 'bool';
CHAR : 'char' ;
STRING : 'string' ;
PAIR : 'pair' ;

TRUE : 'true' ;
FALSE : 'false' ;

NOT : '!' ;
LEN : 'len' ;
ORD : 'ord' ;
CHR : 'chr' ;
BITNOT : '~' ;

MULT : '*' ;
DIV : '/' ;
MOD : '%' ;
PLUS : '+' ;
MINUS : '-' ;
EQUAL : '=' ;
GREATER : '>' ;
GREATER_EQ : '>=' ;
LESS : '<' ;
LESS_EQ : '<=' ;
EQ : '==' ;
NOT_EQ : '!=' ;
AND : '&&' ;
OR : '||' ;
BITAND : '&' ;
BITOR : '|' ;

OPEN_PARENTHESES : '(' ;
CLOSE_PARENTHESES : ')' ;
OPEN_BRACKETS : '[' ;
CLOSE_BRACKETS : ']' ;

COMMA : ',' ;
SEMICOLON : ';' ;
COLON : ':' ;

SINGLE_QUOTE : '\'' ;
DOUBLE_QUOTE : '"' ;

NULL : 'null' ;
IDENT : (UNDERSCORE | LETTER) (UNDERSCORE | LETTER | DIGIT)* ;
DIGIT : '0'..'9' ;
LETTER : [a-zA-Z] ;
UNDERSCORE : '_' ;

fragment ESCAPED_CHAR
  :
  ('\\0'
  | '\\' 'b'
  | '\\' 'n'
  | '\\' 'f'
  | '\\' 'r'
  | '\\' DOUBLE_QUOTE
  | '\\' SINGLE_QUOTE
  | '\\' '\\')
  ;

fragment CHARACTER
  :
  ~('\''
  | '"'
  | '\\')
  | ESCAPED_CHAR
  ;

CHAR_LITER : SINGLE_QUOTE CHARACTER SINGLE_QUOTE ;

STRING_LITER : DOUBLE_QUOTE CHARACTER* DOUBLE_QUOTE ;