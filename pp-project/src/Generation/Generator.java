package Generation;

import ANTLR.GrammarBaseVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.junit.Test;

public class Generator extends GrammarBaseVisitor {

    /** The base register. */
    private Reg arp = new Reg("r_arp");
    /** The outcome of the checker phase. */
    private Result checkResult;
    /** Association of statement nodes to labels. */
    private ParseTreeProperty<Label> labels;
    /** The program being built. */
    private Program prog;
    /** Register count, used to generate fresh registers. */
    private int regCount;
    /** Association of expression and target nodes to registers. */
    private ParseTreeProperty<Reg> regs;

    public Program generate(ParseTree tree, Result checkResult) {
        this.prog = new Program();
        this.checkResult = checkResult;
        this.regs = new ParseTreeProperty<>();
        this.labels = new ParseTreeProperty<>();
        this.regCount = 0;
        tree.accept
                (this);
        return this.prog;
    }

    /** Constructs an operation from the parameters
     * and adds it to the program under construction. */
    private Op emit(Label label, OpCode opCode, Operand... args) {
        Op result = new Op(label, opCode, args);
        this.prog.addInstr(result);
        return result;
    }


    @Test
    public void instrTest(){
        this.prog = new Program();
        this.regs = new ParseTreeProperty<>();
        this.labels = new ParseTreeProperty<>();
        this.regCount = 0;
        Reg r0 = new Reg("reg_a");
        Reg r1 = new Reg("reg_b");
        Reg result = new Reg("reg_r");
        Num n = new Num(12);
        Op i = emit(OpCode.Add, r0, r1);
        Op j = emit(OpCode.Add, r1, result);
        Op k = emit(OpCode.AddI, r1, n, result);
        System.out.println(prog.prettyPrint());
    }


    /** Constructs an operation from the parameters
     * and adds it to the program under construction. */
    private Op emit(OpCode opCode, Operand... args) {
        return emit((Label) null, opCode, args);
    }

    /**
     * Looks up the label for a given parse tree node,
     * creating it if none has been created before.
     * The label is actually constructed from the entry node
     * in the flow graph, as stored in the checker result.
     */
    private Label label(ParserRuleContext node) {
        Label result = this.labels.get(node);
        if (result == null) {
            ParserRuleContext entry = this.checkResult.getEntry(node);
            result = createLabel(entry, "n");
            this.labels.put(node, result);
        }
        return result;
    }

    /** Creates a label for a given parse tree node and prefix. */
    private Label createLabel(ParserRuleContext node, String prefix) {
        Token token = node.getStart();
        int line = token.getLine();
        int column = token.getCharPositionInLine();
        String result = prefix + "_" + line + "_" + column;
        return new Label(result);
    }

    /** Retrieves the offset of a variable node from the checker result,
     * wrapped in a {@link Num} operand. */
    private Num offset(ParseTree node) {
        return new Num(this.checkResult.getOffset(node));
    }

    /** Returns a register for a given parse tree node,
     * creating a fresh register if there is none for that node. */
    private Reg reg(ParseTree node) {
        Reg result = this.regs.get(node);
        if (result == null) {
            result = new Reg("r_" + this.regCount);
            this.regs.put(node, result);
            this.regCount++;
        }
        return result;
    }

    /** Assigns a register to a given parse tree node. */
    private void setReg(ParseTree node, Reg reg) {
        this.regs.put(node, reg);
    }

}
