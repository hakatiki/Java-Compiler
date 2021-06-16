/*package Elaboration;


import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import pp.block3.cc.antlr.TypeListenParser.HatContext;

public class TypeCalculator extends TypeListenBaseListener {
    private ParseTreeProperty<Type> vals = new ParseTreeProperty<>();

    public void init() {
        this.vals = new ParseTreeProperty<Type>();
    }

    @Override public void exitHat(TypeListenParser.HatContext ctx) {
        if (val(ctx.expr(0)) == val(ctx.expr(1)) && val(ctx.expr(0)) == Type.NUM){
            set(ctx, Type.NUM);
        }
        else if(val(ctx.expr(0)) == Type.STR && val(ctx.expr(1)) == Type.NUM){
            set(ctx, Type.STR);
        }
        else{
            set(ctx, Type.ERR);
        }
    }
    @Override public void exitAssignment(TypeListenParser.AssignmentContext ctx) {
        if (val(ctx.expr(0)) == val(ctx.expr(1))){
            set(ctx, Type.BOOL);
        }else{
            set(ctx, Type.ERR);
        }
    }
    @Override public void exitPlus(TypeListenParser.PlusContext ctx){
        if (val(ctx.expr(0))==  val(ctx.expr(1)) ){
            set(ctx, val(ctx.expr(0)));
        }
        else{
            set(ctx, Type.ERR);
        }
    }
    @Override public void exitNumber(TypeListenParser.NumberContext ctx) {
        set(ctx, Type.NUM);
    }

    @Override public void exitBoolean(TypeListenParser.BooleanContext ctx) {
        set(ctx, Type.BOOL);
    }

    @Override public void exitString(TypeListenParser.StringContext ctx) {
        set(ctx, Type.STR);
    }

    private void set(ParseTree node, Type val) {
        this.vals.put(node, val);
    }

    public Type val(ParseTree node) {
        return this.vals.get(node);
    }



}*/
