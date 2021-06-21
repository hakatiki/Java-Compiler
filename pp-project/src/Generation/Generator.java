package Generation;

import ANTLR.GrammarBaseVisitor;
import ANTLR.GrammarParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
// TODO :: visitWhileLoop lengthStat+1???????

public class Generator extends GrammarBaseVisitor<List<String>> {

    /** The base register. */
    private Reg arp = new Reg("r_arp");
    /** The outcome of the checker phase. */
    private Result checkResult;
    /** Association of statement nodes to labels. */
    private ParseTreeProperty<Label> labels;
    /** The program being built. */
    private ParseTreeProperty<Reg> regs;
    private ParseTreeProperty<Scope> scope;

    public void generate(ParseTree tree, Result checkResult) {
        this.checkResult = checkResult;
        this.regs = new ParseTreeProperty<>();
        this.labels = new ParseTreeProperty<>();
        tree.accept
                (this);
    }
    private Num offset(ParseTree node) {
        return new Num(this.checkResult.getOffset(node));
    }

    private void setReg(ParseTree node, Reg reg) {
        this.regs.put(node, reg);
    }
    private Reg getReg(ParseTree node) {
        return this.regs.get(node);
    }
    private void continueScope(ParseTree node){
        this.scope.put(node, this.scope.get(node.getParent()));
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override public List<String> visitProgram(GrammarParser.ProgramContext ctx) { return visitChildren(ctx); }

    @Override public List<String> visitClassDec(GrammarParser.ClassDecContext ctx) { return visitChildren(ctx); }


    @Override public List<String>  visitVarDec(GrammarParser.VarDecContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitThreadedBlock(GrammarParser.ThreadedBlockContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitPutLock(GrammarParser.PutLockContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitPutUnlock(GrammarParser.PutUnlockContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitCopyOver(GrammarParser.CopyOverContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitParExpr(GrammarParser.ParExprContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitNotExpr(GrammarParser.NotExprContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitArrayExpr(GrammarParser.ArrayExprContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitGetThreadId(GrammarParser.GetThreadIdContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitGetIndex(GrammarParser.GetIndexContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitAddExpr(GrammarParser.AddExprContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitCompExpr(GrammarParser.CompExprContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitOrExpr(GrammarParser.OrExprContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitConstExpr(GrammarParser.ConstExprContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitIdExpr(GrammarParser.IdExprContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitAndExpr(GrammarParser.AndExprContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitArrContents(GrammarParser.ArrContentsContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitEmptyArr(GrammarParser.EmptyArrContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitIntArray(GrammarParser.IntArrayContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitBoolArray(GrammarParser.BoolArrayContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitInt(GrammarParser.IntContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitBool(GrammarParser.BoolContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitIsLocal(GrammarParser.IsLocalContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitIsShared(GrammarParser.IsSharedContext ctx) { return visitChildren(ctx); }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override public List<String>  visitIfStatement(GrammarParser.IfStatementContext ctx) {
        continueScope(ctx);
        List<String> current = new LinkedList<>();
        List<String> exprCode = visit(ctx.expr());      // Code for expr must be executed 1st
        Reg reg = getReg(ctx.expr());                   // Register to be compared.
        List <String> ifCode = visit(ctx.stat(0));
        int lengthIf = ifCode.size();
        int lengthElse = 0;
        List<String> elseCode = new LinkedList<>();
        if (ctx.stat().size()==2) {
            elseCode = visit(ctx.stat(1));
            lengthElse = elseCode.size();
        }
        // TODO might need to fix plus 1 or plus 0
        String branch = "Branch "+reg.toString()+" (Rel "+ (lengthIf+1) +")";
        String endIfJump = "Jump (Rel ( "+ (lengthElse+1) +"))";
        current.addAll(exprCode);
        current.add(branch);
        current.addAll(ifCode);
        current.add(endIfJump);
        current.addAll(elseCode);
        return current;
    }
    @Override public List<String>  visitWhileLoop(GrammarParser.WhileLoopContext ctx) {
        continueScope(ctx);
        List<String> current = new LinkedList<>();
        List<String> exprCode = visit(ctx.expr());      // Code for expr must be executed 1st
        Reg reg = getReg(ctx.expr());                   // Register to be compared.
        List <String> statCode = visit(ctx.stat());
        int lengthStat = statCode.size();
        int lengthExpr = statCode.size();
        // TODO might need to fix plus 1 or plus 0
        String branch = "Branch "+reg.toString()+" (Rel "+ (lengthStat+1) +")";
        String back = "Jump (Rel ( "+ -(lengthStat+lengthExpr+1) + "))";
        current.addAll(exprCode);
        current.add(branch);
        current.addAll(statCode);
        current.add(back);
        return current;
    }
    @Override public List<String>  visitBlockStat(GrammarParser.BlockStatContext ctx) {
        Scope oldScope = scope.get(ctx.parent);
        Scope newScope = oldScope.getCopy();
        scope.put(ctx, newScope);
        List<String> current = new LinkedList<>();
        for (int i = 0; i < ctx.stat().size();i++){
            current.addAll(visit(ctx.stat(i)));
        }

        return current;
    }

}
