package Generation;

import ANTLR.GrammarLexer;
import ANTLR.GrammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import java.io.*;
import java.util.List;

// ----- Assembler -> to compile language to SprIl code ------
public class Assembler {
    private ParseTreeWalker walker = new ParseTreeWalker();
    private Generator tool = new Generator();

// ===================== Tests ======================


    // --- Simple compile -> works ---
    @Test
    public void compile(){
        // Required files [Banking System] and [Peterson's Algorithm]
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
        CompileFile("src/Sample/Tests/mult.txt", "src/sprockell-master/src/Tests/mult.hs");
        CompileFile("src/Sample/Tests/and.txt", "src/sprockell-master/src/Tests/and.hs");
        CompileFile("src/Sample/Tests/eqArrays.txt", "src/sprockell-master/src/Tests/eqArrays.hs");
        CompileFile("src/Sample/Tests/setIndex.txt", "src/sprockell-master/src/Tests/setIndex.hs");
        CompileFile("src/Sample/Tests/sortArr.txt", "src/sprockell-master/src/Tests/sortArr.hs");
        CompileFile("src/Sample/Tests/infLoop.txt", "src/sprockell-master/src/Tests/infLoop.hs");
        CompileFile("src/Sample/Tests/isPrime.txt", "src/sprockell-master/src/Tests/isPrime.hs");

        // Appendix C
        CompileFile("src/Sample/CompleteProgram/complete.txt", "src/sprockell-master/src/CompleteProgram/complete.hs");
    }

    // --- MemoryOutOfBoundsException test -> should write error in terminal ---
    @Test
    public void memoryOutOfBounds() {
        CompileFile("src/Sample/Tests/memoryOutOfBounds.txt", "src/sprockell-master/src/Tests/memoryOutOfBounds.hs");
    }

    // --- TooManyThreadsException test -> should write error in terminal ---
    @Test
    public void tooManyThreads() {
        CompileFile("src/Sample/Tests/tooManyThreads.txt", "src/sprockell-master/src/Tests/tooManyThreads.hs");
    }



    // =================================================================================================================================================

    private void reset(){
        walker = new ParseTreeWalker();
        tool = new Generator();
    }

    public void CompileFile(String src, String dst) { //Take file, format it to ready-to-run Haskell file -> write it to Destination
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
        String comb = pre + code + post; //put all together
        try {
            PrintWriter out = new PrintWriter(dst);
            out.println(comb);
            out.close();
        } catch (FileNotFoundException e) {
            // We are sad:((
        }
        for (int i = 0; i < prog.size();i++){
            System.out.println(prog.get(i));
        }
        reset();
    }

    private List<String> check(String file){ //converts file into list of generated code lines
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

    private ParseTree parse(String text) { //parse the content
        CharStream chars = CharStreams.fromString(text);
        Lexer lexer = new GrammarLexer(chars);
        TokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        return parser.program();
    }

    private String readFile(String path) throws IOException { //read file using FilePath
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
