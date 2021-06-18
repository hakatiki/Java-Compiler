
package Elaboration;

import ANTLR.GrammarLexer;
import ANTLR.GrammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestClass {
    private ParseTreeWalker walker = new ParseTreeWalker();
    private TypeCheck tool = new TypeCheck();

    @Test
    public void tests(){
        testIf(false);
        testWrongIf(false);
        testWhile(false);
        testThreaded(false); //Doesn't get Thread.id correctly as type Int
        testLocks(false);
        testArrays(true); //Doesn't correctly define array's + doesn't get index correctly
        //testEqArrays(false); //same as above
        testIfScope(false);
        testWrongIfScope(false);
        testWhileScope(false);
        testWrongWhileScope(true);
    }


    // Tests if-statement
    public void testIf(boolean print) {
        assertEquals(0,check("src/Sample/if.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    // Test whether if-statement reacts on wrong type in condition
    public void testWrongIf(boolean print) {
        assertEquals(1, check("src/Sample/wrongIf.txt"));
        if (print)
            for (int i = 0; i < tool.errorList.size(); i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    // Test while-loop
    public void testWhile(boolean print){
        assertEquals(0,check("src/Sample/while.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    // Test threaded block
    public void testThreaded(boolean print){
        assertEquals(1,check("src/Sample/threaded.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    // Test lock structure
    public void testLocks(boolean print){
        assertEquals(0,check("src/Sample/locks.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }


    // Test creation of arrays
    public void testArrays(boolean print){
        assertEquals(0,check("src/Sample/arrays.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    // Test checking equality of arrays
    public void testEqArrays(boolean print){
        assertEquals(17,check("src/Sample/eqArrays.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    public void testIfScope(boolean print){
        assertEquals(0,check("src/Sample/ifScope.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    public void testWrongIfScope(boolean print){
        assertEquals(1,check("src/Sample/wrongIfScope.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    public void testWhileScope(boolean print){
        assertEquals(0,check("src/Sample/whileScope.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }

    public void testWrongWhileScope(boolean print){
        assertEquals(1,check("src/Sample/wrongWhileScope.txt"));
        if (print)
            for (int i = 0; i <tool.errorList.size();i++)
                System.out.println(tool.errorList.get(i));
        reset();
    }


    private void reset(){
        walker = new ParseTreeWalker();
        tool = new TypeCheck();
    }
    private int  check(String file){
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
    private  ParseTree parse(String text) {
        CharStream chars = CharStreams.fromString(text);
        Lexer lexer = new GrammarLexer(chars);
        TokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        return parser.program();
    }



    private  String readFile(String path) throws IOException {
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
