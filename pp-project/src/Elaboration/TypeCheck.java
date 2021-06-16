package Elaboration;

import ANTLR.GrammarBaseListener;
import ANTLR.GrammarParser;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.LinkedList;
import java.util.List;
// TODO: exitCompExpr
// TODO: exitOrExpr
// TODO: exitAndExpr
// TODO: exitAddExpr
// TODO: SymbolTableType

public class TypeCheck extends GrammarBaseListener {
    private SymbolTable table = new SymbolTableClass();
    private ParseTreeProperty<Type> tree = new ParseTreeProperty<>();
    public List<String> errorList = new LinkedList<String>();

    @Override public void enterClassDec(GrammarParser.ClassDecContext ctx) { }

    @Override public void exitClassDec(GrammarParser.ClassDecContext ctx) { }


    @Override public void exitVarDec(GrammarParser.VarDecContext ctx) {
        Type expected = tree.get(ctx.type());
        Type exprType = tree.get(ctx.expr());
        if (expected !=  exprType){
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error = " Error on line: " + line + " and the position: "+ pos+". Type missmatch!";
            errorList.add( error );
        }
        // TODO FIX THIS
        table.add( ctx.ID().getText());
        tree.put(ctx, expected );
    }



    @Override public void exitIfStatement(GrammarParser.IfStatementContext ctx) {
        Type t = tree.get(ctx.expr());
        if (t != Type.Bool){
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error = " Error on line: " + line + " and the position: "+ pos+". Type error in while, expected boolean!";
            errorList.add( error );
        }
    }




    @Override public void enterWhileLoop(GrammarParser.WhileLoopContext ctx) {
        //table.openScope();
        // ADDING IT THERE MIGHT BE A GOOD IDEA IDK YET
        Type t = tree.get(ctx.expr());
        if (t != Type.Bool){
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error = " Error on line: " + line + " and the position: "+ pos+". Type error in while, expected boolean!";
            errorList.add( error );
        }
    }
    @Override public void exitWhileLoop(GrammarParser.WhileLoopContext ctx) {
        //table.closeScope();
        // MATCH IT UP WITH enterWhileLoop
    }
    @Override public void exitNotExpr(GrammarParser.NotExprContext ctx) {
        Type first = tree.get(ctx.expr());
        int line = ctx.start.getLine();
        int pos = ctx.start.getCharPositionInLine();
        if (! (first == Type.Bool)){
            String error = " Error on line: " + line + " and the position: "+ pos+". Type error while applying NOT!";
            errorList.add( error );
        }
        // TODO: Add more stuff here

        tree.put(ctx, Type.Bool);
    }
    @Override public void exitAddExpr(GrammarParser.AddExprContext ctx) {
        Type first = tree.get(ctx.expr(0));
        Type second = tree.get(ctx.expr(1));
        int line = ctx.start.getLine();
        int pos = ctx.start.getCharPositionInLine();
        if (! (first == Type.Int && second == Type.Int)){
            String error = " Error on line: " + line + " and the position: "+ pos+". Type error while adding two expressions!";
            errorList.add( error );
        }
        // TODO: Add more stuff here

        tree.put(ctx, Type.Int);
    }
    @Override public void exitOrExpr(GrammarParser.OrExprContext ctx) {
        Type first = tree.get(ctx.expr(0));
        Type second = tree.get(ctx.expr(1));
        int line = ctx.start.getLine();
        int pos = ctx.start.getCharPositionInLine();
        if (! (first == Type.Bool && second == Type.Bool)){
            String error = " Error on line: " + line + " and the position: "+ pos+". Type error while applying OR!";
            errorList.add( error );
        }
        // TODO: Add more stuff here

        tree.put(ctx, Type.Bool);
    }
    @Override public void enterBlockStat(GrammarParser.BlockStatContext ctx) {
        table.openScope();
    }
    @Override public void exitBlockStat(GrammarParser.BlockStatContext ctx) {
        table.closeScope();
    }
    @Override public void exitAndExpr(GrammarParser.AndExprContext ctx) {
        Type first = tree.get(ctx.expr(0));
        Type second = tree.get(ctx.expr(1));
        int line = ctx.start.getLine();
        int pos = ctx.start.getCharPositionInLine();
        if (! (first == Type.Bool && second == Type.Bool)){
            String error = " Error on line: " + line + " and the position: "+ pos+". Type error while applying OR!";
            errorList.add( error );
        }
        // TODO: Add more stuff here

        tree.put(ctx, Type.Bool);
    }
    @Override public void exitParExpr(GrammarParser.ParExprContext ctx) {
        tree.put(ctx, tree.get(ctx.expr()));
    }
    @Override public void exitConstExpr(GrammarParser.ConstExprContext ctx) {
        String str = ctx.getText();
        if (str.equals("True") || str.equals("False"))
            tree.put(ctx, Type.Bool);
        else
            tree.put(ctx, Type.Int);
    }
    @Override public void exitArrayExpr(GrammarParser.ArrayExprContext ctx) {
        tree.put(ctx, tree.get(ctx.arr()));
    }
    @Override public void exitIdExpr(GrammarParser.IdExprContext ctx) {
        if (!table.contains(ctx.ID().getText())){
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error = " Error on line: " + line + " and the position: "+ pos+". Variable [" +
                    ctx.ID().getText()  +"] out of scope!";
            errorList.add( error );
        }

    }
    @Override public void exitArrContents(GrammarParser.ArrContentsContext ctx) {
        boolean good = true;
        Type prev = tree.get(ctx.expr(0));
        Type curr;
        for (int i = 1; i < ctx.children.size(); i++){
            curr = tree.get(ctx.expr(i));
            if (curr != prev){
                int line = ctx.start.getLine();
                int pos = ctx.start.getCharPositionInLine();
                String error = " Error on line: " + line + " and the position: "+ pos+". Contents of array does not match!";
                errorList.add( error );
                good = false;
            }
            else
                prev = curr;
        }
        if (good){
            tree.put(ctx,prev );
        }
    }
    @Override public void enterEmptyArr(GrammarParser.EmptyArrContext ctx) {
        tree.put(ctx, Type.Empty);
    }
    @Override public void enterBool(GrammarParser.BoolContext ctx) {
        tree.put(ctx, Type.Bool);
    }
    @Override public void enterBoolArray(GrammarParser.BoolArrayContext ctx) {
        tree.put(ctx, Type.BoolArray);
    }
    @Override public void enterInt(GrammarParser.IntContext ctx) {
        tree.put(ctx, Type.Int);
    }
    @Override public void enterIntArray(GrammarParser.IntArrayContext ctx) {
        tree.put(ctx, Type.IntArray);
    }
    @Override public void exitCompExpr(GrammarParser.CompExprContext ctx) {
        Type first = tree.get(ctx.expr(0));
        Type second = tree.get(ctx.expr(1));
        int line = ctx.start.getLine();
        int pos = ctx.start.getCharPositionInLine();
        if (first != second){
            String error = " Error on line: " + line + " and the position: "+ pos+". Compared expressions do not match!";
            errorList.add( error );
        }
        // TODO: Add more stuff here

        tree.put(ctx, Type.Bool);
    }

    //@Override public void exitType(GrammarParser.TypeContext ctx) { }
    //@Override public void exitEmptyArr(GrammarParser.EmptyArrContext ctx) { }
    //@Override public void enterArrContents(GrammarParser.ArrContentsContext ctx) { }
    //@Override public void enterIdExpr(GrammarParser.IdExprContext ctx) { }
    //@Override public void enterArrayExpr(GrammarParser.ArrayExprContext ctx) { }
    //@Override public void enterConstExpr(GrammarParser.ConstExprContext ctx) { }
    //@Override public void enterParExpr(GrammarParser.ParExprContext ctx) { }
    //@Override public void enterCompExpr(GrammarParser.CompExprContext ctx) { }
    //@Override public void enterOrExpr(GrammarParser.OrExprContext ctx) { }
    //@Override public void enterAddExpr(GrammarParser.AddExprContext ctx) { }
    //@Override public void enterAndExpr(GrammarParser.AndExprContext ctx) { }
    //@Override public void enterNotExpr(GrammarParser.NotExprContext ctx) { }
    //@Override public void enterVarDec(GrammarParser.VarDecContext ctx) { }
    //@Override public void enterIfStatement(GrammarParser.IfStatementContext ctx) { }
    //@Override public void enterProgram(GrammarParser.ProgramContext ctx) { }
    //@Override public void exitProgram(GrammarParser.ProgramContext ctx) { }

    //// THREAD ////
    //@Override public void enterThreadedBlock(GrammarParser.ThreadedBlockContext ctx) { }

    //@Override public void exitThreadedBlock(GrammarParser.ThreadedBlockContext ctx) { }


    //// LOCKS ////
    //@Override public void enterPutLock(GrammarParser.PutLockContext ctx) { }

    //@Override public void exitPutLock(GrammarParser.PutLockContext ctx) { }

    //@Override public void enterPutUnlock(GrammarParser.PutUnlockContext ctx) { }

    //@Override public void exitPutUnlock(GrammarParser.PutUnlockContext ctx) { }

    //@Override public void enterDecLock(GrammarParser.DecLockContext ctx) { }

    //@Override public void exitDecLock(GrammarParser.DecLockContext ctx) { }

    //@Override public void enterCallLock(GrammarParser.CallLockContext ctx) { }

    //@Override public void exitCallLock(GrammarParser.CallLockContext ctx) { }
}