
* Types
    - [M] Booleans
    - [M] Integers
    - [S] Arrays

* [M] Simple expressions and variables
    - Denotations for primitive values of the type.  For instance, for array types there should be a way to construct an array (something like[1,2,3]for an array of integers), and likewise for strings, records etc.

    - Operators for equality and inequality of values of the type.  For instance,  it should be possible to compare different values of the same array or record type.  Such a comparison should be content-based rather than identity-based; in other words, it is not enough to compare the memory addresses where two (say) array values reside, they must be compared element by element.

        Examples:
        - 5 == 5, True == True
        - 5 < 7, [1,2,3] != [1,2,4], [4,5,6] == [4,5,6]
        - This ofc also holds for variables


    - Your language should support typed variables. 
        - imo we should do strictly typed, makes it easier. Then u don't have to keep track of them, but can just reuse

    - Your language should support local, nested scopes.
        - ofc, variables in a higher scope shouldn't be visible to a var on a lower scope
        - they want that you can reuse a name for a var in a higher scope, aka redefine it. The program should look for which one to use (the one with the closest scope), this will be difficult I think oof

    - Variables should also be checked for initialisation
        - Variables should only be used once they've been declared/ given a value!

* [M] Basic statements: Assignments, if and while
    - Basically implement if and while

    - Figure out what we want for legal assignments (please copy stuff only, call-by-reference will fuck me up)



* [M] Concurrency
    - For concurrency, you should add features to your language that make it possible to start and stop processes(a.k.a. threads), and to lock and unlock them. These should be compiled to the special SPRIL instructions provided for this purpose (see CANVAS).

    - Each process runs on a dedicated SPROCKELL; in other words,the architecture does not support dynamic thread creation.

    - Starting  and  stopping  threads  can  either  be  offered  (in  your  source  language)  on  the  basis  of  afork/join-construct  (see,  e.g.,https://en.wikipedia.org/wiki/Fork-join_model)  or  on  the  basisof aparbegin/parend-construct — essentially a block in which the statements should not be executed insequence but in parallel

    - it should be possible to nest threads — i.e., a new thread can beforked from analreadyforked thread or oneparbegin/parendis be allowed within another.

    - Inter-process communication in SPRIL is exclusively done through WriteInstr- and ReadInstr/Receive-instructions and an atomicTestAndSet, which all access a global, shared memory.

    Concurrency things that are mandatory to add:
    - An implementation of Peterson’s algorithm for mutual exclusion
    - An elementary banking system, consisting of several processes trying to transfer money simultaneously. Your implementation should ensure that concurrent transfers do not mess up the state of the bank account.