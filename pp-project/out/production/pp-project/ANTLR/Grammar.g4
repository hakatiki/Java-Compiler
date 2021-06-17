grammar Grammar;

program : def EOF; // start symbol

def     : CLASS 'main' stat                     #classDec
        ;

stat    : '{' stat* '}'                         #blockStat
        | LOCK ID '=' 'new' LOCK ';'            #decLock
        | type ID '=' expr ';'                  #varDec
        | IF '(' expr ')' stat (ELSE stat)?     #ifStatement   //expr must be oof type Bool
        | WHILE '(' expr ')' stat               #whileLoop     //expr must be oof type Bool
        | THREADED '(' NUM ')' stat             #threadedBlock //Threaded
        | ID '.' 'lock' '(' ')' ';'             #putLock
        | ID '.' 'unlock' '(' ')' ';'           #putUnlock
        | ID '=' expr ';'                       #copyOver
        | ID '++' ';'                           #increment
        | ID '--' ';'                           #decrement
        ;

expr:   '!' expr                                #notExpr
        | expr ('+' | '-') expr                 #addExpr
        | expr '&&' expr                        #andExpr
        | expr '||'  expr                       #orExpr
        | expr ('<' | '>' | '==' | '!=') expr   #compExpr
        | '(' expr ')'                          #parExpr
        | (NUM | TRUE | FALSE)                  #constExpr
        | '[' arr ']'                           #arrayExpr
        | 'Thread.id'                           #getThreadId
        | ID '[' (expr) ']'                     #getIndex    //expr must be of type int
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

CLASS: 'class';
IF: 'if';
ELSE: 'else';
WHILE: 'while';
TRUE: 'True';
FALSE: 'False';
THREADED: '@Threaded';
LOCK: 'Lock';

fragment LETTER: [a-zA-Z];
fragment DIGIT: [0-9];

NUM: DIGIT+;
ID: LETTER (LETTER | DIGIT)*;
WS: [ \t\r\n]+ -> skip;







