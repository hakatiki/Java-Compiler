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
        CompileFile("src/Sample/easy.txt", "src/Compiled/easy.hs");
    }


    public void CompileFile(String src, String dst) {
        List<String>  prog = check(src);
        String code = String.join(",\n\t", prog );
        String pre = "import Sprockell\n" +
                     "prog :: [Instruction]\n" +
                     "prog = [\n\t";
        String post =  ",\n\tEndProg\n]\n"+
                     "main = run [prog]";
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
