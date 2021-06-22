package Generation;

import ANTLR.GrammarBaseVisitor;
import ANTLR.GrammarParser;
import Elaboration.Type;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
// TODO :: visitWhileLoop lengthStat+1???????
// TODO Check if continue scope is everywhere!
// TODO Dont forget to add registers to regs ParseTreeProperty
// TODO: Check whether getChild() function actually does what I think it does... (for add/compExpr)
// HARDCODED 4 in visitGetIndex
// HARDCODED 4 in visitArrContents

public class Generator extends GrammarBaseVisitor<List<String>> {

    private ParseTreeProperty<String> regs  = new ParseTreeProperty<>();
    private ParseTreeProperty<Scope> scope = new ParseTreeProperty<>();
    private String varDec = "";


    private void setReg(ParseTree node, String reg) {
        this.regs.put(node, reg);
    }
    private String getReg(ParseTree node) {
        return this.regs.get(node);
    }
    private void continueScope(ParseTree node){
        this.scope.put(node, this.scope.get(node.getParent()));
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    @Override public List<String>  visitThreadedBlock(GrammarParser.ThreadedBlockContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitPutLock(GrammarParser.PutLockContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitPutUnlock(GrammarParser.PutUnlockContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitNotExpr(GrammarParser.NotExprContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitGetThreadId(GrammarParser.GetThreadIdContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitEmptyArr(GrammarParser.EmptyArrContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitIntArray(GrammarParser.IntArrayContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitBoolArray(GrammarParser.BoolArrayContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitInt(GrammarParser.IntContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitBool(GrammarParser.BoolContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitIsLocal(GrammarParser.IsLocalContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitIsShared(GrammarParser.IsSharedContext ctx) { return visitChildren(ctx); }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override public List<String>  visitArrayExpr(GrammarParser.ArrayExprContext ctx) {
        continueScope(ctx);
        regs.put(ctx, regs.get(ctx.arr()));
        return visit(ctx.arr());
    }
    @Override public List<String>  visitParExpr(GrammarParser.ParExprContext ctx) {
        continueScope(ctx);
        List<String> exprCode = visit(ctx.expr());
        String reg = regs.get(ctx.expr());
        regs.put(ctx, reg);
        return exprCode;
    }
    @Override public List<String>  visitGetIndex(GrammarParser.GetIndexContext ctx) {
        continueScope(ctx);
        List<String> current =  new LinkedList<>();
        Scope currScope = scope.get(ctx);
        int addressA = currScope.address(ctx.ID().getText());
        List<String> exprCode = visit(ctx.expr());
        String reg0 = regs.get(ctx.expr());
        String reg1 = reg0.equals("regA")? "regB":"regA";
        String loadFour = "Load (ImmValue 4) "+reg1+"";
        String offsetComp = "Compute Mul " +reg0+" " + reg1+" " + reg0;
        String addressComp1 = "Load (ImmValue " +  addressA + " ) " +reg1;
        String addressComp2 = "Compute Add "+ reg1 + " " + reg0 + " " + reg0;
        String loadInstr = "Load ( IndAddr " + reg0 +" ) " + reg0;
        current.addAll(exprCode);
        current.add(loadFour);
        current.add(offsetComp);
        current.add(addressComp1);
        current.add(addressComp2);
        current.add(loadInstr);
        regs.put(ctx, reg0);
        return current;
    }
    @Override public List<String>  visitIfStatement(GrammarParser.IfStatementContext ctx) {
        continueScope(ctx);
        List<String> current = new LinkedList<>();
        List<String> exprCode = visit(ctx.expr());      // Code for expr must be executed 1st
        String reg = getReg(ctx.expr());                   // Register to be compared.
        List <String> ifCode = visit(ctx.stat(0));
        int lengthIf = ifCode.size();
        int lengthElse = 0;
        List<String> elseCode = new LinkedList<>();
        if (ctx.stat().size()==2) {
            elseCode = visit(ctx.stat(1));
            lengthElse = elseCode.size();
        }
        // TODO might need to fix plus 1 or plus 0
        String branch = "Branch "+reg+" (Rel "+ (lengthIf+2) +")";
        String endIfJump = "Jump (Rel "+ (lengthElse+1) +" )";
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
        String reg = getReg(ctx.expr());                   // Register to be compared.
        List <String> statCode = visit(ctx.stat());
        int lengthStat = statCode.size();
        int lengthExpr = exprCode.size();
        // TODO might need to fix plus 1 or plus 0
        String branch = "Branch "+reg+" (Rel "+ (lengthStat+2) +")";
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
        for (int i = 0; i < ctx.stat().size();i++)
            current.addAll(visit(ctx.stat(i)));
        return current;
    }
    @Override public List<String>  visitVarDec(GrammarParser.VarDecContext ctx) {
        continueScope(ctx);

        Scope currScope = this.scope.get(ctx);
        boolean isShared = ctx.mem().getText().equals("Shared"); //checks whether variable is shared or not
        String ID = ctx.ID().toString();
        String reg = regs.get(ctx.expr());
        List<String> current = new LinkedList<>();
        List<String> exprCode = visit(ctx.expr());

        varDec = ctx.ID().getText();
        currScope.putShared(ID,isShared); //saves whether ID is shared or not
        current.addAll(exprCode);

        if (ctx.type().getText().equals("Int") || ctx.type().getText().equals("Bool")){
            currScope.put(ID,isShared); //modified it to now add the correct offsets, depending on isShared
            String store = (isShared?"WriteInstr":"Store") + " " + reg +" (DirAddr "+ currScope.address(ID)+" )"; //modifies depending on isShared
            current.add(store);
        }
        varDec = "";
        return current;
    }

    @Override public List<String>  visitCopyOver(GrammarParser.CopyOverContext ctx) {
        continueScope(ctx);
        List<String> current = new LinkedList<>();

        List<String> exprCode = visit(ctx.expr());
        String ID = ctx.ID().toString();
        String reg = regs.get(ctx.expr());
        Scope currScope = scope.get(ctx);
        String store = "Store "+reg+" (DirAddr "+ currScope.address(ID)+" )";
        current.addAll(exprCode);
        current.add(store);
        return current;
    }
    @Override public List<String>  visitIdExpr(GrammarParser.IdExprContext ctx) {
        continueScope(ctx);
        List<String> current = new LinkedList<>();
        Scope currScope = this.scope.get(ctx);
        int address = currScope.address(ctx.ID().getText());
        String instr = "Load (DirAddr "+ address+ " ) regA";
        setReg(ctx,"regA");
        current.add(instr);
        return current;
    }
    @Override public List<String>  visitAddExpr(GrammarParser.AddExprContext ctx) {
        continueScope(ctx);

        List<String> current = new LinkedList<>();
        List<String> lhs  = visit(ctx.expr(0));
        String reg0 = regs.get(ctx.expr(0));
        List<String> rhs  = visit(ctx.expr(1));
        String reg1 = regs.get(ctx.expr(1));

        String op = ctx.getChild(1).getText();
        String instr = op.equals("+")?"Add":"Sub";

        // Getting child correct?
        // Might still need fixing if -- memory allocation ting
        String save = "Push "+ reg0;
        String reg2 = reg1.equals(reg0)?(reg0.equals("regA")?"regB":"regA"):reg0;
        String get = "Pop "+ reg2;
        String addInstr = "Compute " + instr + " " + reg2 + " " + reg1 + " " + reg0;
        current.addAll(lhs);
        current.add(save);
        current.addAll(rhs);
        current.add(get);
        current.add(addInstr);
        regs.put(ctx,reg0);
        return current;
    }
    @Override public List<String>  visitArrContents(GrammarParser.ArrContentsContext ctx) {
        continueScope(ctx);
        List<String> current = new LinkedList<>();
        boolean isShared = ctx.getParent().getParent().getChild(0).getText().equals("Shared");


        Scope s = scope.get(ctx);
        s.put(varDec, isShared);
        int baseAddress = s.address(varDec);
        for (int i =  0; i < ctx.expr().size();i++){
            if (i != 0)
                s.put(varDec + "[" + i + "]", isShared);
            List<String> currExpr = visit(ctx.expr(i));
            // currExpr.remove(currExpr.size()-1);
            String reg0 = regs.get(ctx.expr(i));
            String saveToMem = (isShared?"WriteInstr":"Store") + " " + reg0 + " (DirAddr " + (baseAddress + i * 4) + " )";

            current.addAll(currExpr);
            current.add(saveToMem);
        }
        return current; }
    @Override public List<String>  visitClassDec(GrammarParser.ClassDecContext ctx) {
        scope.put(ctx, new Scope());
        List <String> ret = visit(ctx.stat());
        return ret;
    }
    @Override public List<String>  visitBeginDec(GrammarParser.BeginDecContext ctx) {
        continueScope(ctx);
        return visit(ctx.def());
    }
    @Override public List<String>  visitCompExpr(GrammarParser.CompExprContext ctx) {
        continueScope(ctx);

        List<String> current = new LinkedList<>();
        List<String> lhs  = visit(ctx.expr(0));
        String reg0 = regs.get(ctx.expr(0));
        List<String> rhs  = visit(ctx.expr(1));
        String reg1 = regs.get(ctx.expr(1));

        String op = ctx.getChild(1).getText();
        String instr = op.equals(">")?"Gt":(op.equals("<")?"Lt":(op.equals("==")?"Equal":"NEq"));

        // Getting child correct?
        // Might still need fixing if -- memory allocation ting
        String save = "Push "+ reg0;
        String reg2 = reg1.equals(reg0)?(reg0.equals("regA")?"regB":"regA"):reg0;
        String get = "Pop "+ reg2;
        String addInstr = "Compute " + instr + " " + reg2 + " " + reg1 + " " + reg0;
        current.addAll(lhs);
        current.add(save);
        current.addAll(rhs);
        current.add(get);
        current.add(addInstr);
        regs.put(ctx,reg0);
        return current;
    }
    @Override public List<String>  visitOrExpr(GrammarParser.OrExprContext ctx) {
        continueScope(ctx);
        List<String> current = new LinkedList<>();
        List<String> lhs  = visit(ctx.expr(0));
        String reg0 = regs.get(ctx.expr(0));
        List<String> rhs  = visit(ctx.expr(1));
        String reg1 = regs.get(ctx.expr(1));

        // Might still need fixing if -- memory allocation ting
        String save = "Push "+ reg0;
        String reg2 = reg1.equals(reg0)?(reg0.equals("regA")?"regB":"regA"):reg0;
        String get = "Pop "+ reg2;
        String addInstr = "Compute Or "+reg2+ " " + reg1 + " " + reg0;
        current.addAll(lhs);
        current.add(save);
        current.addAll(rhs);
        current.add(get);
        current.add(addInstr);
        regs.put(ctx,reg0);
        return current;
    }
    @Override public List<String>  visitConstExpr(GrammarParser.ConstExprContext ctx) {
        continueScope(ctx);
        List<String> current = new LinkedList<>();
        String reg = "regA";
        String str = ctx.getText();
        if (str.equals("True") || str.equals("False")){
            int val =  str.equals("True") ? 1 : 0;
            String load = "Load (ImmValue "+ val + " ) " + reg;
            current.add(load);
        }
        else {
            String load = "Load (ImmValue "+ str + " ) " + reg;
            current.add(load);
        }
        regs.put(ctx,reg);
        return current;
    }
    @Override public List<String>  visitAndExpr(GrammarParser.AndExprContext ctx) {
        continueScope(ctx);
        List<String> current = new LinkedList<>();
        List<String> lhs  = visit(ctx.expr(0));
        String reg0 = regs.get(ctx.expr(0));
        List<String> rhs  = visit(ctx.expr(1));
        String reg1 = regs.get(ctx.expr(1));

        // Might still need fixing if -- memory allocation ting
        String save = "Push " + reg0;
        String reg2 = reg1.equals(reg0) ? (reg0.equals("regA") ? "regB" : "regA") : reg0;
        String get = "Pop " + reg2;
        String addInstr = "Compute And " + reg2 + " " + reg1 + " " + reg0;
        current.addAll(lhs);
        current.add(save);
        current.addAll(rhs);
        current.add(get);
        current.add(addInstr);
        regs.put(ctx,reg0);
        return current;
    }

}
