grammar Grammar;

program : def EOF                               #beginDec
        ;                  // start symbol

def     : thread CLASS 'main' stat              #classDec
        ;

thread  : THREADED '(' NUM ')'                  #threadedDec
        ;

stat    : '{' stat* '}'                         #blockStat
        | mem type ID '=' expr ';'              #varDec
        | IF '(' expr ')' stat (ELSE stat)?     #ifStatement   //expr must be oof type Bool
        | WHILE '(' expr ')' stat               #whileLoop     //expr must be oof type Bool
        | THREADED '(' NUM ')' stat             #threadedBlock //Threaded
        | 'lock' '(' ')' ';'                    #putLock
        | 'unlock' '(' ')' ';'                  #putUnlock
        | ID '=' expr ';'                       #copyOver
        | OUT '(' expr ')' ';'                  #output
        ;

expr:   '!' expr                                #notExpr
        | expr (PLUS | MINUS) expr              #addExpr
        | expr MULT expr                        #multExpr
        | expr '&&' expr                        #andExpr
        | expr '||'  expr                       #orExpr
        | expr (GT | LT | EQ | NEQ) expr        #compExpr
        | '(' expr ')'                          #parExpr
        | (NUM | TRUE | FALSE)                  #constExpr
        | '[' arr ']'                           #arrayExpr
        | 'Thread.id'                           #getThreadId
        | ID '[' expr ']'                       #getIndex    //expr must be of type int
        | ID                                    #idExpr
        ;

arr     : expr (',' expr)*                      #arrContents
        |                                       #emptyArr
        ;

type    : 'Int[]'                               #intArray
        | 'Bool[]'                              #boolArray
        | 'Int'                                 #int
        | 'Bool'                                #bool
        ;

mem     : 'Local'                               #isLocal
        | 'Shared'                              #isShared
        ;

CLASS: 'class';
OUT : 'printf';
IF: 'if';
ELSE: 'else';
WHILE: 'while';
TRUE: 'True';
FALSE: 'False';
THREADED: '@Threaded';
LOCK: 'Lock';
PLUS: '+';
MINUS: '-';
MULT: '*';
GT: '>';
LT: '<';
EQ: '==';
NEQ: '!=';


fragment LETTER: [a-zA-Z];
fragment DIGIT: [0-9];

NUM: DIGIT+;
ID: LETTER (LETTER | DIGIT)*;
WS: [ \t\r\n]+ -> skip;







