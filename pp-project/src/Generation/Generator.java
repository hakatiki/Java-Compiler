package Generation;

import ANTLR.GrammarBaseVisitor;
import ANTLR.GrammarParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



public class Generator extends GrammarBaseVisitor<List<String>> {

    private ParseTreeProperty<String> regs  = new ParseTreeProperty<>();
    private ParseTreeProperty<Scope> scope = new ParseTreeProperty<>();
    public HashMap<Integer,Boolean> activeThreads = new HashMap<>();
    private int masterThread;
    private String varDec = "";
    private final String LOCK = "lock";


    private void setReg(ParseTree node, String reg) {
        this.regs.put(node, reg);
    }
    private String getReg(ParseTree node) {
        return this.regs.get(node);
    }
    private void continueScope(ParseTree node){
        this.scope.put(node, this.scope.get(node.getParent()));
    }


//==========================================================================================================================================

    @Override public List<String>  visitBeginDec(GrammarParser.BeginDecContext ctx) {
        scope.put(ctx, new Scope()); //define first scope
        return visit(ctx.def());
    }

    @Override public List<String> visitClassDec(GrammarParser.ClassDecContext ctx) {
        this.continueScope(ctx); //take parent scope
        List<String> current = new LinkedList<>();
        int val = Integer.parseInt(ctx.thread().getChild(2).getText());
        String temp; //string to use for adding lines to current
        Scope currScope = scope.get(ctx);
        masterThread = 0;
        for (int i = 0; i < (val+1); i++) { //every thread in the program gets a place in memory | LOCK gets first spot, that's why for-loop starts at 0.
            temp = "Load (ImmValue "+ (i) +") regA";
            current.add(temp);
            temp = "WriteInstr regA (DirAddr " + (i) + ")";
            current.add(temp);

            try{
                if (i > 0) {
                    this.activeThreads.put(i, false);
                    currScope.putShared("Thread"+i,true); //reserve spot for every thread in Scope
                    currScope.put("Thread"+i,true);
                } else {
                    scope.get(ctx).put(LOCK, true); //put lock into Scope
                }
            } catch (MemoryOutOfBoundsException e) { //catch exception
                e.printStackTrace();
            }

        }

        // --- Loop for non-master threads ---
        temp = "ReadInstr (IndAddr regSprID)";
        current.add(temp);
        temp = "Receive regA";
        current.add(temp);
        temp = "Compute Equal regSprID reg0 regB";
        current.add(temp);
        temp = "Branch regB (Rel 4)";
        current.add(temp);
        temp = "Compute NEq regA regSprID regB";
        current.add(temp);
        temp = "Branch regB (Ind regA)";
        current.add(temp);
        temp = "Jump (Rel (-6))";
        current.add(temp);
        current.addAll(visit(ctx.stat()));

        // --- EndProg ---
        temp = "Compute Add reg0 regPC regA";
        current.add(temp);
        temp = "Compute Equal regSprID reg0 regB";
        current.add(temp);
        temp = "Branch regB (Rel 2)";
        current.add(temp);
        temp = "EndProg";
        current.add(temp);

        for (int i = 1; i < (val+1); i++) { //every thread is told where to go to end their program
            temp = "WriteInstr regA (DirAddr " + (i) + ")";
            current.add(temp);
        }
        return current;
    }

    @Override public List<String> visitThreadedBlock(GrammarParser.ThreadedBlockContext ctx) throws TooManyThreadsException {
        continueScope(ctx);
        List<String> current = new LinkedList<>();
        int neededThreads = Integer.parseInt(ctx.NUM().getText());
        List<Integer> usedThreads = new LinkedList<>();
        String temp;

        for (int i = 1; i <= activeThreads.size(); i++) { //checks what Threads are free to use for the Threaded block
            if (neededThreads == 0) { //no needed threads left -> stop looking
                break;
            }
            if (!activeThreads.get(i)) { //if thread isn't active already
                activeThreads.put(i,true); // make it active
                usedThreads.add(i); // save that we are using this one
                neededThreads--; // and look for other free threads
            }
        }

        for (int i : usedThreads) { //write block of code for every newly spawned thread

            int oldMaster = this.masterThread; //check who the old master is of the thread
            this.masterThread = i; //newly spawned thread is master of nested block

            List<String> inside = visit(ctx.tstat());
            joinThreads(current, i);
            temp = "Branch regB (Rel (-4))";
            current.add(temp);
            temp = "WriteInstr regPC (DirAddr "+ i +")";
            current.add(temp);
            temp = "Load (ImmValue " + oldMaster + ") regA";  //get MASTER THREAD
            current.add(temp);

            temp = "Compute Equal regSprID regA regB";
            current.add(temp);
            temp = "Branch regB (Rel " + (inside.size()+3) + ")";
            current.add(temp);

            current.addAll(inside);

            temp = "WriteInstr regSprID (DirAddr "+ i +")";
            current.add(temp);
            temp = "Jump (Abs " + ((activeThreads.size()+1)*2) + ")";
            current.add(temp);
            this.masterThread = oldMaster; // put old master back as master role
            activeThreads.put(i,false); // declare thread as free-to-use for other threads
        }

        if (neededThreads > 0) { //if there are no threads that can be used/ have reserved spots
            throw new TooManyThreadsException("Threaded block contains more threads than created!");
        }

        // --- Master thread waits on Children to return ---
        int it = 0;
        for (int i : usedThreads) {
            joinThreads(current, i);
            temp = "Branch regB (Rel (-"+ (4 + (it*5)) +"))";
            current.add(temp);
            it++;
        }
        return current;
    }

    private void joinThreads(List<String> current, int i) { //used by Threaded block -> helps with children return to master return
        String temp;
        temp = "ReadInstr (DirAddr "+ i + ")";
        current.add(temp);
        temp = "Receive regA";
        current.add(temp);
        temp = "Load (ImmValue " + i + ") regC";
        current.add(temp);
        temp = "Compute NEq regA regC regB";
        current.add(temp);
    }

    @Override public List<String> visitNotExpr(GrammarParser.NotExprContext ctx) {
        continueScope(ctx);
        List<String> current = visit(ctx.expr());
        String reg = regs.get(ctx.expr());
        String temp = "Compute Equal " + reg + " reg0 regA";
        regs.put(ctx,"regA");
        current.add(temp);
        return current;
    }

    @Override public List<String>  visitPutLock(GrammarParser.PutLockContext ctx) {
        continueScope(ctx);

        String test = "TestAndSet (DirAddr 0)";
        String recieve = "Receive regD";
        String loadOne = "Load (ImmValue 1) regC";
        String comp = "Compute NEq regD regC regD";
        String branch = "Branch regD (Rel (-4))";
        List<String> code = new LinkedList<>();
        code.add(test);
        code.add(recieve);
        code.add(loadOne);
        code.add(comp);
        code.add(branch);

        return code;
    }

    @Override public List<String>  visitPutUnlock(GrammarParser.PutUnlockContext ctx) {
        continueScope(ctx);
        int lockAddress =  scope.get(ctx).address(LOCK);
        String loadZero = "Load (ImmValue 0) regA";
        String storeZero = "WriteInstr regA (DirAddr "+ lockAddress + ")";
        List<String> code = new LinkedList<>();
        code.add(loadZero);
        code.add(storeZero);

        return code;
    }

    @Override public List<String>  visitGetThreadId(GrammarParser.GetThreadIdContext ctx) {
        continueScope(ctx);
        String temp = "Compute Add regSprID reg0 regA";
        List<String> current = new LinkedList<>();
        current.add(temp);
        regs.put(ctx,"regA");
        return current;
    }

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
        String id = ctx.ID().getText();
        System.out.println(currScope.getOffsets());
        int addressA = currScope.address(id);
        List<String> exprCode = visit(ctx.expr());
        String reg0 = regs.get(ctx.expr());
        String reg1 = reg0.equals("regA")? "regB":"regA";
        current.addAll(exprCode);
        String temp;

        temp = "Load (ImmValue " +  addressA + ") " +reg1;
        current.add(temp);
        temp = "Compute Add "+ reg1 + " " + reg0 + " " + reg0;
        current.add(temp);
        if (currScope.getShared(ctx.ID().getText())) {
            temp = "ReadInstr (IndAddr " + reg0 + ")";
            current.add(temp);
            temp = "Receive " + reg0;
            current.add(temp);
        } else {
            temp = "Load ( IndAddr " + reg0 +") " + reg0;
            current.add(temp);
        }
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
        String eq = "Compute Equal "+ reg + " reg0 " + reg;
        String branch = "Branch "+reg+" (Rel "+ (lengthIf+2) +")";
        String endIfJump = "Jump (Rel "+ (lengthElse+1) +")";
        current.addAll(exprCode);
        current.add(eq);
        current.add(branch);
        current.addAll(ifCode);
        current.add(endIfJump);
        current.addAll(elseCode);
        return current;
    }

    @Override public List<String> visitWhileLoop(GrammarParser.WhileLoopContext ctx) {
        continueScope(ctx);
        List<String> current = new LinkedList<>();
        List<String> exprCode = visit(ctx.expr());      // Code for expr must be executed 1st
        String reg = getReg(ctx.expr());                   // Register to be compared.
        List <String> statCode = visit(ctx.stat());
        int lengthStat = statCode.size();
        int lengthExpr = exprCode.size();
        String eq = "Compute Equal "+ reg + " reg0 " + reg;
        String branch = "Branch "+reg+" (Rel "+ (lengthStat+2) +")";
        String back = "Jump (Rel  ("+ -(lengthStat+lengthExpr+2) + "))";
        current.addAll(exprCode);
        current.add(eq);
        current.add(branch);
        current.addAll(statCode);
        current.add(back);
        return current;
    }

    @Override public List<String> visitBlockStat(GrammarParser.BlockStatContext ctx) {
        Scope oldScope = scope.get(ctx.parent); //define new scope for while and if
        Scope newScope = oldScope.getCopy();
        scope.put(ctx, newScope);
        List<String> current = new LinkedList<>();
        for (int i = 0; i < ctx.stat().size();i++)
            current.addAll(visit(ctx.stat(i)));
        return current;
    }

    @Override public List<String> visitThreadedBlockStat(GrammarParser.ThreadedBlockStatContext ctx) {
        Scope oldScope = scope.get(ctx.parent); //define new scope, but clear localSize since every Thread has it's own local memory
        Scope newScope = oldScope.getCopy();
        newScope.clearLocal();
        scope.put(ctx, newScope);
        List<String> current = new LinkedList<>();
        for (int i = 0; i < ctx.stat().size();i++)
            current.addAll(visit(ctx.stat(i)));
        return current;
    }

    @Override public List<String>  visitVarDec(GrammarParser.VarDecContext ctx) {
        continueScope(ctx);
        varDec = ctx.ID().getText();
        Scope currScope = this.scope.get(ctx);
        boolean isShared = ctx.mem().getText().equals("Shared"); //checks whether variable is shared or not
        String ID = ctx.ID().toString();
        List<String> current = new LinkedList<>();
        List<String> exprCode = visit(ctx.expr());

        String reg = regs.get(ctx.expr());
        currScope.putShared(ID,isShared); //saves whether ID is shared or not
        current.addAll(exprCode);

        if (ctx.type().getText().equals("Int") || ctx.type().getText().equals("Bool")){
            try {
                currScope.put(ID,isShared); //adds the correct offsets, depending on whether variable is shared or not
            } catch (MemoryOutOfBoundsException e) {
                e.printStackTrace();
            }

            String store = (isShared?"WriteInstr":"Store") + " " + reg +" (DirAddr "+ currScope.address(ID)+")"; //modifies depending on isShared
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
        String store = (currScope.getShared(ID)?"WriteInstr":"Store") + " " + reg + " (DirAddr "+ currScope.address(ID) + ")";
        current.addAll(exprCode);
        current.add(store);
        return current;
    }

    @Override public List<String> visitIdExpr(GrammarParser.IdExprContext ctx) {
        continueScope(ctx);
        List<String> current = new LinkedList<>();
        Scope currScope = this.scope.get(ctx);

        boolean isShared = currScope.getShared(ctx.ID().getText());

        int address = currScope.address(ctx.ID().getText());
        String instr;
        if (isShared) {
            instr =  "ReadInstr (DirAddr "+ address +")";
            current.add(instr);
            instr = "Receive regA";
        } else {
            instr = "Load (DirAddr "+ address + ") regA";
        }

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

    @Override public List<String> visitMultExpr(GrammarParser.MultExprContext ctx) {
        continueScope(ctx);

        List<String> current = new LinkedList<>();
        List<String> lhs  = visit(ctx.expr(0));
        String reg0 = regs.get(ctx.expr(0));
        List<String> rhs  = visit(ctx.expr(1));
        String reg1 = regs.get(ctx.expr(1));

        String save = "Push "+ reg0;
        String reg2 = reg1.equals(reg0)?(reg0.equals("regA")?"regB":"regA"):reg0;
        String get = "Pop "+ reg2;
        String addInstr = "Compute Mul " + reg2 + " " + reg1 + " " + reg0;
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
        try {
            s.put(varDec, isShared);
        } catch (MemoryOutOfBoundsException e) {
            e.printStackTrace();
        }

        int baseAddress = s.address(varDec);
        for (int i =  0; i < ctx.expr().size();i++){
            if (i != 0) {
                try {
                    s.put(varDec + "[" + i + "]", isShared);
                } catch (MemoryOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
            List<String> currExpr = visit(ctx.expr(i));
            // currExpr.remove(currExpr.size()-1);
            String reg0 = regs.get(ctx.expr(i));
            String saveToMem = (isShared?"WriteInstr":"Store") + " " + reg0 + " (DirAddr " + (baseAddress + i) + ")";

            current.addAll(currExpr);
            current.add(saveToMem);
        }
        return current;
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
        String load;
        if (str.equals("True") || str.equals("False")){
            int val =  str.equals("True") ? 1 : 0;
            load = "Load (ImmValue "+ val + ") " + reg;
        }
        else {
            load = "Load (ImmValue "+ str + ") " + reg;
        }
        current.add(load);
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

    @Override public List<String>  visitOutput(GrammarParser.OutputContext ctx) {
        continueScope(ctx);
        List<String> current  = visit(ctx.expr());
        String myPrint = "WriteInstr regA numberIO";
        current.add(myPrint);
        return current;
    }

    @Override public List<String> visitSetIndex(GrammarParser.SetIndexContext ctx) {
        continueScope(ctx);
        Scope currScope = scope.get(ctx);
        String ID = ctx.ID().getText();
        int address = currScope.address(ID);

        List<String> current = visit(ctx.expr(0));
        String temp = "Load (ImmValue " + address + ") " + "regE";
        current.add(temp);
        temp = "Compute Add regA regE regE";
        current.add(temp);
        current.addAll(visit(ctx.expr(1)));
        temp = (currScope.getShared(ID)?"WriteInstr":"Store") + " regA (IndAddr regE)";
        current.add(temp);
        return current;
    }

    //==========================================================================================================================================================


    @Override public List<String>  visitEmptyArr(GrammarParser.EmptyArrContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitIntArray(GrammarParser.IntArrayContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitBoolArray(GrammarParser.BoolArrayContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitInt(GrammarParser.IntContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitBool(GrammarParser.BoolContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitIsLocal(GrammarParser.IsLocalContext ctx) { return visitChildren(ctx); }

    @Override public List<String>  visitIsShared(GrammarParser.IsSharedContext ctx) { return visitChildren(ctx); }

}
