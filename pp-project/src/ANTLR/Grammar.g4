grammar Grammar;

program : def EOF; // start symbol

def   : CLASS 'main' stat                       #classDec
        ;

stat    : type ID '=' expr ';'                  #varDec
        | LOCK ID '=' 'new' LOCK ';'            #decLock
        | ID DOT lock ';'                       #callLock
        | IF '(' expr ')' stat ('else' stat)?    #ifStatement   //expr must be oof type Bool
        | WHILE '(' expr ')' stat               #whileLoop     //expr must be oof type Bool
        | THREADED '(' NUM ')' stat             #threadedBlock //Threaded
        | '{' stat* '}'                         #blockStat
        ;

lock    : 'lock' '(' ')' ';'                    #putLock
        | 'unlock' '(' ')' ';'                  #putUnlock
        ;

expr:   NOT expr                                #notExpr
        | expr (PLUS | MINUS) expr              #addExpr
        | expr AND expr                         #andExpr
        | expr OR  expr                         #orExpr
        | expr (LT | GT | EQ | NE) expr         #compExpr
        | LPAR expr RPAR                        #parExpr
        | (NUM | TRUE | FALSE)                  #constExpr
        | LSQ arr RSQ                           #arrayExpr
        | ID                                    #idExpr
        ;

arr     : expr (',' expr)*                      #arrContents
        |                                       #emptyArr
        ;


type : 'Int'
      | 'Bool'
      | 'Int[]'
      | 'Bool[]'
      ;

THREADED: '@Threaded';
LOCK: 'Lock';
DOT: '.';
SEMI: ';';
COMMA: ',';
LSQ: '[';
RSQ: ']';
ASSIGN: '=';
NOT: '!';
OR: '||';
AND: '&&';
BIT_OR: '|';
BIT_AND: '&';
PLUS: '+';
MINUS: '-';
LTEQ: '<=';
GTEQ: '>=';
LT: '<';
GT: '>';
EQ: '==';
NE: '!=';
LCURLY: '{';
RCURLY: '}';
LPAR: '(';
RPAR: ')';
PRINT: 'printf';
WHILE: 'while';
IF: 'if';
ELSE: 'else';
TRUE: 'True';
FALSE: 'False';
CLASS: 'class';

fragment LETTER: [a-zA-Z];
fragment DIGIT: [0-9];
WS: [ \t\r\n]+ -> skip;

ID: LETTER (LETTER | DIGIT);
NUM: DIGIT+;