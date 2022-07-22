parser grammar WaccParser ;

options {
  tokenVocab = WaccLexer ;
}

prog: BEGIN macro* func* stat END EOF ;

macro: DEFINE type ident EQUAL assign_rhs ;

func: type ident OPEN_PARENTHESES param_list? CLOSE_PARENTHESES IS stat END ;

param_list: param (COMMA param)* ;

param: type ident ;

stat
  : SKIPP                                                               # skipStat
  | BREAK                                                               # breakStat
  | CONTINUE                                                            # continueStat
  | type ident EQUAL assign_rhs                                         # declareStat
  | assign_lhs EQUAL assign_rhs                                         # assignStat
  | READ assign_lhs                                                     # readStat
  | FREE expr                                                           # freeStat
  | RETURN expr                                                         # returnStat
  | EXIT expr                                                           # exitStat
  | PRINT expr                                                          # printStat
  | PRINTLN expr                                                        # printlnStat
  | FOR stat? SEMICOLON expr? SEMICOLON stat? DO stat DONE              # forStat
  | IF expr THEN stat ELSE stat FI                                      # ifElseStat
  | IF expr THEN stat FI                                                  # ifStat
  | WHILE expr DO stat DONE                                             # whileStat
  | DO stat WHILE expr DONE                                             # doWhileStat
  | SWITCH expr case_expr* (DEFAULT COLON stat)? DONE                   # switchStat
  | BEGIN stat END                                                      # beginStat
  | stat SEMICOLON stat                                                 # seqStat
  ;

case_expr : CASE expr COLON (stat)? ;

assign_lhs
  : ident                                                               # identLhs
  | array_elem                                                          # arrayelemLhs
  | pair_elem                                                           # pairelemLhs
  ;

assign_rhs
  : expr                                                                # exprRhs
  | array_liter                                                         # arrayliterRhs
  | NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES          # newpairRhs
  | pair_elem                                                           # pairelemRhs
  | CALL ident OPEN_PARENTHESES arg_list? CLOSE_PARENTHESES             # callRhs
  ;

arg_list: expr (COMMA expr)* ;

pair_elem
  : FST expr                              # fstPairElem
  | SND expr                              # sndPairElem
  ;

type
  : (INT | BOOL | CHAR | STRING)
  | type OPEN_BRACKETS CLOSE_BRACKETS
  | PAIR OPEN_PARENTHESES pair_elem_type COMMA pair_elem_type CLOSE_PARENTHESES
  ;

pair_elem_type
  : type
  | PAIR
  ;

expr
  : int_liter                                              # intlitExpr
  | bool_liter                                             # boollitExpr
  | CHAR_LITER                                             # charlitExpr
  | STRING_LITER                                           # stringlitExpr
  | NULL                                                   # pairlitExpr
  | ident                                                  # identExpr
  | array_elem                                             # arrayelemExpr
  | (NOT | MINUS | LEN | ORD | CHR | BITNOT) expr          # unaryOperExpr
  | expr (MULT | DIV | MOD) expr                           # multDivModExpr
  | expr (PLUS | MINUS) expr                               # plusMinusExpr
  | expr (GREATER | GREATER_EQ | LESS | LESS_EQ) expr      # greaterLessExpr
  | expr (EQ | NOT_EQ) expr                                # eqNotEqExpr
  | expr AND expr                                          # andExpr
  | expr OR expr                                           # orExpr
  | expr BITAND expr                                       # bitAndExpr
  | expr BITOR expr                                        # bitOrExpr
  | OPEN_PARENTHESES expr CLOSE_PARENTHESES                # parenthesesExpr
  ;

ident : IDENT ;

array_elem : ident (OPEN_BRACKETS expr CLOSE_BRACKETS)+ ;

int_liter : (PLUS | MINUS)? DIGIT+ ;

bool_liter : TRUE | FALSE ;

array_liter : OPEN_BRACKETS (expr (COMMA expr)*)? CLOSE_BRACKETS ;