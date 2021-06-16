grammar Grammar;

text : items EOF; // start symbol

item : WORD EXCL?    #wordItem  // word with optional exclamation
     | '(' items ')' #textItem  // text in new scope
     | '^' WORD      #raiseItem // intensity-raising declaration
     ;

items : item items // non-empty sequence of items
      |            // empty sequence
      ;

WORD : [a-z]+; // word token
EXCL : [!?]+; // exclamation sequence
WS : [ \t\n\r] -> skip; // whitespace