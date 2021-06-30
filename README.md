## General
Since this is probably already ur millionth README today, I'll keep it short.
- To test all the test classes/code generation parts, easiest is to simply open the pp-project folder in IntelliJ 
- The report, located at the top level explaining the tests. Code is also commented to help navigation.
- Grammar is in `pp-project/src/ANTLR/Grammar.g4` or in `Appendix A - Grammar Specification` in the report.
- Required [Peterson's] algorithm and [Banking] system have source code and generated Haskell files. 
    * Source code: `pp-project/src/Sample/Required/*`
    * SprIl code: `pp-project/src/sprockell-master/src/Required/*`
- All ready-to-run Haskell files are in `pp-project/src/sprockell-master/src/*` (please see below for more details)


## Structure
[File] - README.md -----------------------------------------> The readme (duh)

[File] - Project-Documentation-PP-67.pdf -------------------> The report

[Folder] - pp-project
    [Folder] - Gen -----------------------------------------> folder containing ANTLR Generated classes
    [Folder] - src
        [Folder] - ANTLR
            [File] - Grammar.g4 ----------------------------> Grammar for our language
        [Folder] - Elaboration
            [File] - Pair.java -----------------------------> Pair<A,B> implementation to be used by SymbolTableClass
            [File] - SymbolTableClass.java -----------------> Adjusted symboltable class from tutorials
            [File] - Type.java -----------------------------> Enum containing legal types in language
            [File] - TypeCheck.java ------------------------> Class extending GrammarBaseListener to TypeCheck
            [File] - TypeCheckTest.java --------------------> Test class to check TypeCheck
        [Folder] - Generation
            [File] - Assembler.java ------------------------> Compiles generated code into read-to-run Haskell file
            [File] - Generator.java ------------------------> Generates code using GrammarBaseVisitor
            [File] - MemoryOutOfBoundsException.java -------> Exception
            [File] - Scope.java ----------------------------> Modeling a scope to implement correct scoping features
            [File] - TooManyThreadsException.java ----------> Exception
        [Folder] - Parser
            [File] - Parse.java ----------------------------> empty class extending GrammarBaseListener
            [File] - ParserTest.java -----------------------> Test class to check syntax
            [Folder] - Tests -------------------------------> Folder containing text files for ParserTest
        [Folder] - Sample
            [Folder] - CompleteProgram
                [File] - complete.txt ----------------------> Same code as test in `Appendix C - Extended Test Program`
            [Folder] - Required ----------------------------> Contains text files for Peterson's alg and Banking system
            [Folder] - Tests -------------------------------> Contains text files for all Semantic and Typecheck tests
        [Folder] - sprockell-master
            [Folder] - src ---------------------------------> contains ready-to-run Haskell files using Sprockell
                [Folder] - CompleteProgram -----------------> `Appendix C - Extended Test Program` generated code
                [Folder] - Required ------------------------> generated code for Peterson's alg and Banking system
                [Folder] - Tests ---------------------------> generated code for all Semantic error tests