package Generation;

import ANTLR.GrammarLexer;
import ANTLR.GrammarParser;
import Elaboration.TypeCheck;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.*;
import java.util.List;

public class Assembler {
    private ParseTreeWalker walker = new ParseTreeWalker();
    private Generator tool = new Generator();



    private void reset(){
        walker = new ParseTreeWalker();
        tool = new Generator();
    }
    // "src/Sample/easy.txt"
    @Test
    public void Test(){
        // Required files
        CompileFile("src/Sample/Required/bankingsystem.txt", "src/sprockell-master/src/Required/bankingsystem.hs");
        CompileFile("src/Sample/Required/petersons.txt", "src/sprockell-master/src/Required/petersons.hs");

        // Additional tests
        CompileFile("src/Sample/Tests/negation.txt", "src/sprockell-master/src/Tests/negation.hs");
        CompileFile("src/Sample/Tests/arrays.txt", "src/sprockell-master/src/Tests/arrays.hs");
        CompileFile("src/Sample/Tests/if.txt", "src/sprockell-master/src/Tests/if.hs");
        CompileFile("src/Sample/Tests/ifScope.txt", "src/sprockell-master/src/Tests/ifScope.hs");
        CompileFile("src/Sample/Tests/locks.txt", "src/sprockell-master/src/Tests/locks.hs");
        CompileFile("src/Sample/Tests/checkSorted.txt", "src/sprockell-master/src/Tests/checkSorted.hs");
        CompileFile("src/Sample/Tests/threaded.txt", "src/sprockell-master/src/Tests/threaded.hs");
        CompileFile("src/Sample/Tests/while.txt", "src/sprockell-master/src/Tests/while.hs");
        CompileFile("src/Sample/Tests/whileScope.txt", "src/sprockell-master/src/Tests/whileScope.hs");
    }


    public void CompileFile(String src, String dst) {
        List<String> prog = check(src);

        String additional = "";
        for (int i = 0; i < tool.activeThreads.size(); i++) {
            additional = additional + ",prog";
        }
        String code = String.join(",\n    ", prog );
        String pre = "import Sprockell\n" +
                "prog :: [Instruction]\n" +
                "prog = [\n    ";
        String post =  ",\n    EndProg]\n"+
                "main = run [prog" + additional + "]";
        String comb = pre + code + post;
        try {
            PrintWriter out = new PrintWriter(dst);
            out.println(comb);
            out.close();
        } catch (FileNotFoundException e){
            // We are sad:((
        }
        for (int i = 0; i < prog.size();i++){
            System.out.println(prog.get(i));
        }
        reset();
    }
    private List<String> check(String file){
        String str = "";
        try{
            str = readFile(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        ParseTree tree = parse(str);
        return tool.visit(tree);
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
