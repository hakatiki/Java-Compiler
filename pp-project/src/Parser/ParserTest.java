package Parser;

import ANTLR.GrammarLexer;
import ANTLR.GrammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.Assert.*;

public class ParserTest {
    private Parse tool = new Parse();
    private ParseTreeWalker walker = new ParseTreeWalker();


    // ---- TestCorrect -> returns nothing ----
    @Test
    public void testCorrect() {
        assertTrue(checkParse("src/Parser/Tests/arrays.txt"));
        assertTrue(checkParse("src/Parser/Tests/threadedBlocks.txt"));
    }


    // ---- TestFalse -> will return error messages for wrong syntax ----
    @Test
    public void testFalse() {
        assertTrue(checkParse("src/Parser/Tests/wrongClassDec.txt"));
        assertTrue(checkParse("src/Parser/Tests/wrongClassDec2.txt"));
        assertTrue(checkParse("src/Parser/Tests/wrongVarDec.txt"));
        assertTrue(checkParse("src/Parser/Tests/wrongWhileLoop.txt"));
    }


    // ===================================================================================================================================

    private ParseTree parse(String text) {
        CharStream chars = CharStreams.fromString(text);
        Lexer lexer = new GrammarLexer(chars);
        TokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        return parser.program();
    }

    private boolean checkParse(String file) {
        String str = "";
        try{
            str = readFile(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        ParseTree tree = parse(str);

        this.walker.walk(this.tool, tree);
        return true;
    }

    private String readFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
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
