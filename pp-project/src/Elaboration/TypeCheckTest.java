
package Elaboration;

import ANTLR.GrammarLexer;
import ANTLR.GrammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TypeCheckTest {
    private ParseTreeWalker walker = new ParseTreeWalker();
    private TypeCheck tool = new TypeCheck();

    /*
        The tests take a boolean, which decides whether it should return the error messages
        ~ the integer is the expected amount of errors
     */
    @Test
    public void goodCode(){
        testIf(false, 0);
        testWhile(false, 0);
        testThreaded(false, 0);
        testLocks(false, 0);
        testArrays(false, 0);
        testEqArrays(false, 0);
        testIfScope(false, 0);
        testNegation(false, 0);
        testSetIndex(false, 0);
        testWhileScope(false,0);
    }

    @Test
    public void wrongCode() {
        testWrongIf(true, 1);               // wrong type in condition
        testWrongIfScope(true,1);           // var in if-statement declared, but called outside
        testWrongWhileScope(true,1);        // var in while-statement declared, but called outside
        testWrongType(true,1);              // attempt to put wrong-type value in variable
        testWrongNegation(true,2);          // try negation of Integer
        testWrongUndeclaredVar(true,1);     // try put value in uninitialised variable
    }


    //  =========================== Correct Code Tests ===========================


    // Tests if-statement
    public void testIf(boolean print, int x) {
        assertEquals(0,check("src/Sample/Tests/if.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    public void testWhile(boolean print, int x){
        assertEquals(x,check("src/Sample/Tests/while.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    public void testThreaded(boolean print, int x){
        assertEquals(x,check("src/Sample/Tests/threaded.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    public void testLocks(boolean print, int x){
        assertEquals(x,check("src/Sample/Tests/locks.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    public void testArrays(boolean print, int x){
        assertEquals(x,check("src/Sample/Tests/arrays.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    // Test checking equality of arrays
    public void testEqArrays(boolean print, int x){
        assertEquals(x,check("src/Sample/Tests/eqArrays.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    public void testIfScope(boolean print, int x){
        assertEquals(x,check("src/Sample/Tests/ifScope.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    public void testSetIndex(boolean print, int x) {
        assertEquals(x, check("src/Sample/Tests/setIndex.txt"));
        if (print)
            for (int i = 0; i < tool.errorList.size(); i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    public void testWhileScope(boolean print, int x){
        assertEquals(x,check("src/Sample/Tests/whileScope.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    public void testNegation(boolean print, int x) {
        assertEquals(x, check("src/Sample/Tests/negation.txt"));
        if (print)
            for (int i = 0; i < tool.errorList.size(); i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }




    // =========================== Wrong Code Tests ===========================

    // wrong type in condition
    public void testWrongIf(boolean print, int x) {
        assertEquals(x, check("src/Sample/Tests/wrongIf.txt"));
        if (print)
            for (int i = 0; i < tool.errorList.size(); i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    // var in if-statement declared, but called outside
    public void testWrongIfScope(boolean print, int x){
        assertEquals(x,check("src/Sample/Tests/wrongIfScope.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    // var in while-statement declared, but called outside
    public void testWrongWhileScope(boolean print, int x){
        assertEquals(x,check("src/Sample/Tests/wrongWhileScope.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    // attempt to put wrong-type value in variable
    public void testWrongType(boolean print, int x){
        assertEquals(x,check("src/Sample/Tests/wrongType.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    // try negation of Integer
    public void testWrongNegation(boolean print, int x) {
        assertEquals(x, check("src/Sample/Tests/wrongNegation.txt"));
        if (print)
            for (int i = 0; i < tool.errorList.size(); i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    // try put value in uninitialised variable
    public void testWrongUndeclaredVar(boolean print, int x) {
        assertEquals(x, check("src/Sample/Tests/wrongUndeclaredVar.txt"));
        if (print)
            for (int i = 0; i < tool.errorList.size(); i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }





    // =================================== Rest ===================================



    private void reset(){ //resets walker and tool
        walker = new ParseTreeWalker();
        tool = new TypeCheck();
    }

    /**
     *
     * @param file: FilePath of file that needs to be checked
     * @return integer == size of errorList
     */
    private int check(String file){ //run Walker using TypeCheck -> return errorList.size()
        String str = "";
        try{
            str = readFile(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        ParseTree tree = parse(str);
        this.walker.walk(this.tool, tree);
        return tool.errorList.size();
    }

    private  ParseTree parse(String text) { //parse the content
        CharStream chars = CharStreams.fromString(text);
        Lexer lexer = new GrammarLexer(chars);
        TokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        return parser.program();
    }

    private  String readFile(String path) throws IOException { //read file using FilePath
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();
        return content;
    }

}
