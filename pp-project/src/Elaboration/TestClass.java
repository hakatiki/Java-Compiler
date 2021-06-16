package pp.block5.cc.homework;


import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import pp.block5.cc.antlr.EmoteLexer;
import pp.block5.cc.antlr.EmoteParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestClass {
    private  final ParseTreeWalker walker = new ParseTreeWalker();
    private  final EmoteChecker tool = new EmoteChecker();
    @Test
    public  void test1(){
        SymbolTableClass table = new SymbolTableClass();
        table.openScope();
        table.add("alma",0);
        assertEquals (table.getValue("alma"),0);
        table.add("korte",0);
            table.openScope();
            assertEquals (table.getValue("korte"),0);
            table.raiseItem("alma");
            assertEquals (table.getValue("alma"),1);
            table.raiseItem("alma");
            assertEquals (table.getValue("alma"),2);
                table.openScope();
                table.raiseItem("alma");
                assertEquals (table.getValue("alma"),3);
                table.closeScope();
            assertEquals (table.getValue("alma"),2);
            table.closeScope();
        assertEquals (table.getValue("alma"),0);

    }
    @Test
    public void test2(){
        assertEquals(check("src/pp/block5/cc/sample/ok1.txt"),0);
        assertEquals(check("src/pp/block5/cc/sample/err2.txt"), 1 );
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
        Lexer lexer = new EmoteLexer(chars);
        TokenStream tokens = new CommonTokenStream(lexer);
        EmoteParser parser = new EmoteParser(tokens);
        return parser.text();
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
