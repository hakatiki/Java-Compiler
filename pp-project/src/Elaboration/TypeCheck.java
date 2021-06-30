package Elaboration;

import ANTLR.GrammarBaseListener;
import ANTLR.GrammarParser;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import java.util.LinkedList;
import java.util.List;

public class TypeCheck extends GrammarBaseListener {
    private SymbolTableClass table = new SymbolTableClass();
    private ParseTreeProperty<Type> tree = new ParseTreeProperty<>();
    public List<String> errorList = new LinkedList<>();

// =====================================================================================================================

    @Override public void exitGetIndex(GrammarParser.GetIndexContext ctx) { //get index of an array
        Type index = tree.get(ctx.expr());
        if (index != Type.Int) { //check whether index is an Integer
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error = "Error on line: " + line + " : "+ pos+". Expected an integer!";
            errorList.add( error );
        }
        Type array = table.getValue(ctx.ID().getText());
        if (array !=Type.BoolArray && array != Type.IntArray) { //check whether it is an array that is indexed
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error = "Error on line: " + line + " : "+ pos+". You can only index an array!";
            errorList.add( error );
        }
        Type val = array==Type.BoolArray? Type.Bool:Type.Int;
        tree.put(ctx, val);
    }

    @Override public void exitVarDec(GrammarParser.VarDecContext ctx) { //check expected and given type
        Type expected = tree.get(ctx.type());
        Type exprType = tree.get(ctx.expr());
        if (expected !=  exprType) {
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error =  "Error on line: " + line + " : "+ pos+". Type missmatch!";
            errorList.add(error);
        }
        table.add( ctx.ID().getText(),expected);
        tree.put(ctx, expected );
    }

    @Override public void exitIfStatement(GrammarParser.IfStatementContext ctx) { //check type of condition
        Type t = tree.get(ctx.expr());
        if (t != Type.Bool) {
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error =  "Error on line: " + line + " : "+ pos+". Type error in if statement, expected boolean!";
            errorList.add( error );
        }
    }

    @Override public void exitWhileLoop(GrammarParser.WhileLoopContext ctx) { //check type of condition
        table.closeScope();

        Type t = tree.get(ctx.expr());
        if (t != Type.Bool){
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error = " Error on line: " + line + " and the position: "+ pos+". Type error in while, expected boolean!";
            errorList.add( error );
        }
    }

    @Override public void exitNotExpr(GrammarParser.NotExprContext ctx) { //check whether negation is done on a boolean
        Type first = tree.get(ctx.expr());
        int line = ctx.start.getLine();
        int pos = ctx.start.getCharPositionInLine();
        if (! (first == Type.Bool)) {
            String error =  "Error on line: " + line + " : "+ pos+". Type error while applying NOT!";
            errorList.add( error );
        }
        tree.put(ctx, Type.Bool);
    }

    @Override public void exitAddExpr(GrammarParser.AddExprContext ctx) { // check whether add is done with two integers
        Type first = tree.get(ctx.expr(0));
        Type second = tree.get(ctx.expr(1));
        int line = ctx.start.getLine();
        int pos = ctx.start.getCharPositionInLine();
        if (! (first == Type.Int && second == Type.Int)) {
            String error =  "Error on line: " + line + " : "+ pos+". Type error while adding two expressions!";
            errorList.add( error );
        }
        tree.put(ctx, Type.Int);
    }

    @Override public void exitMultExpr(GrammarParser.MultExprContext ctx) { // check whether mult is done with two integers
        Type first = tree.get(ctx.expr(0));
        Type second = tree.get(ctx.expr(1));
        int line = ctx.start.getLine();
        int pos = ctx.start.getCharPositionInLine();
        if (! (first == Type.Int && second == Type.Int)) {
            String error =  "Error on line: " + line + " : "+ pos+". Type error while adding two expressions!";
            errorList.add( error );
        }
        tree.put(ctx, Type.Int);
    }

    @Override public void exitOrExpr(GrammarParser.OrExprContext ctx) { // check whether or is done with two booleans
        Type first = tree.get(ctx.expr(0));
        Type second = tree.get(ctx.expr(1));
        int line = ctx.start.getLine();
        int pos = ctx.start.getCharPositionInLine();
        if (! (first == Type.Bool && second == Type.Bool)) {
            String error = "Error on line: " + line + " : "+ pos+". Type error while applying OR!";
            errorList.add( error );
        }
        tree.put(ctx, Type.Bool);
    }

    @Override public void exitAndExpr(GrammarParser.AndExprContext ctx) { // check whether and is done with two booleans
        Type first = tree.get(ctx.expr(0));
        Type second = tree.get(ctx.expr(1));
        int line = ctx.start.getLine();
        int pos = ctx.start.getCharPositionInLine();
        if (! (first == Type.Bool && second == Type.Bool)) {
            String error =  "Error on line: " + line + " : "+ pos+". Type error while applying OR!";
            errorList.add( error );
        }
        tree.put(ctx, Type.Bool);
    }

    @Override public void exitConstExpr(GrammarParser.ConstExprContext ctx) { // check what type the expr is
        String str = ctx.getText();
        if (str.equals("True") || str.equals("False")) { tree.put(ctx, Type.Bool); }
        else { tree.put(ctx, Type.Int); }
    }

    @Override public void exitIdExpr(GrammarParser.IdExprContext ctx) { //check whether the ID is in scope
        if (table.getValue(ctx.ID().getText()) == Type.NotInScope) {
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error =  "Error on line: " + line + " : "+ pos+ ". Variable [" +
                    ctx.ID().getText() + "] out of scope!";
            errorList.add(error);
            tree.put(ctx, Type.Empty);
        }
        else { tree.put(ctx, table.getValue(ctx.ID().getText())); }
    }

    @Override public void exitArrContents(GrammarParser.ArrContentsContext ctx) { // check whether array contents are of correct type
        boolean good = true;
        Type prev = tree.get(ctx.expr(0));
        Type curr;
        for (int i = 1; i < ctx.children.size()/2; i++){
            curr = tree.get(ctx.expr(i));
            if (curr != prev) {
                int line = ctx.start.getLine();
                int pos = ctx.start.getCharPositionInLine();
                String error =  "Error on line: " + line + " : "+ pos+". Contents of array does not match!";
                errorList.add( error );
                good = false;
            } else { prev = curr; }
        }
        if (good){
            Type type = prev==Type.Bool?Type.BoolArray:Type.IntArray;
            tree.put(ctx, type );
        }
    }

    @Override public void exitCompExpr(GrammarParser.CompExprContext ctx) { // check whether compare is done with two integers
        Type first = tree.get(ctx.expr(0));
        Type second = tree.get(ctx.expr(1));
        int line = ctx.start.getLine();
        int pos = ctx.start.getCharPositionInLine();
        if (first != second){
            String error =  "Error on line: " + line + " : "+ pos+". Compared expressions do not match!";
            errorList.add( error );
        }

        tree.put(ctx, Type.Bool);
    }

    @Override public void exitCopyOver(GrammarParser.CopyOverContext ctx) { //check whether value is of matching type
        Type type = tree.get(ctx.expr());
        if (table.getValue(ctx.ID().getText()) == Type.NotInScope) {
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error =  "Error on line: " + line + " : "+ pos+ ". Variable [" +
                    ctx.ID().getText() + "] out of scope!";
            errorList.add(error);
            tree.put(ctx, Type.Empty);
        } else if (table.getValue(ctx.ID().getText()) != type) {
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error =  "Error on line: " + line + " : "+ pos+ ". Variable [" +
                    ctx.ID().getText() + "] has a different type!";
            errorList.add(error);
            tree.put(ctx, Type.Empty);
        } else { tree.put(ctx, table.getValue(ctx.ID().getText())); }
    }

    @Override public void exitSetIndex(GrammarParser.SetIndexContext ctx) {
        Type ind = tree.get(ctx.expr(0));
        Type type = tree.get(ctx.expr(1));
        String temp = type.toString() + "Array";
        boolean clean = true;
        if (table.getValue(ctx.ID().getText()) == Type.NotInScope) { // check whether ID is in scope
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error =  "Error on line: " + line + " : "+ pos+ ". Variable [" +
                    ctx.ID().getText() + "] out of scope!";
            errorList.add(error);
            tree.put(ctx, Type.Empty);
            clean = false;
        }
        if (!table.getValue(ctx.ID().getText()).toString().equals(temp)) { //check whether val is of correct type
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error =  "Error on line: " + line + " : "+ pos+ ". Variable [" +
                    ctx.ID().getText() + "] has a different type!";
            errorList.add(error);
            if (clean) {
                tree.put(ctx, Type.Empty);
                clean = false;
            }

        }
        if (ind != Type.Int) { //check if index is of type integer
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error =  "Error on line: " + line + " : "+ pos+ ". [" +
                    ctx.expr(0).getText() + "] is not of type Int!";
            errorList.add(error);
            if (clean) {
                tree.put(ctx, Type.Empty);
                clean = false;
            }
        }
        if (clean) {
            tree.put(ctx, table.getValue(ctx.ID().getText()));
        }
    }

    @Override public void exitOutput(GrammarParser.OutputContext ctx) { //check whether value given to printf is an integer
        Type type = tree.get(ctx.expr());
        if (type != Type.Int) {
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            String error =  "Error on line: " + line + " : "+ pos+ ". Can only print numbers!";
            errorList.add(error);
        }
    }

// =====================================================================================================================

    @Override public void enterWhileLoop(GrammarParser.WhileLoopContext ctx) { table.openScope(); }

    @Override public void exitGetThreadId(GrammarParser.GetThreadIdContext ctx) { tree.put(ctx, Type.Int); }

    @Override public void enterEmptyArr(GrammarParser.EmptyArrContext ctx) { tree.put(ctx, Type.Empty); }

    @Override public void enterBool(GrammarParser.BoolContext ctx) { tree.put(ctx, Type.Bool); }

    @Override public void enterBoolArray(GrammarParser.BoolArrayContext ctx) { tree.put(ctx, Type.BoolArray); }

    @Override public void enterInt(GrammarParser.IntContext ctx) { tree.put(ctx, Type.Int); }

    @Override public void enterIntArray(GrammarParser.IntArrayContext ctx) { tree.put(ctx, Type.IntArray); }

    @Override public void exitParExpr(GrammarParser.ParExprContext ctx) { tree.put(ctx, tree.get(ctx.expr())); }

    @Override public void exitArrayExpr(GrammarParser.ArrayExprContext ctx) { tree.put(ctx, tree.get(ctx.arr())); }

    @Override public void enterBlockStat(GrammarParser.BlockStatContext ctx) { table.openScope(); }

    @Override public void exitBlockStat(GrammarParser.BlockStatContext ctx) { table.closeScope(); }
}