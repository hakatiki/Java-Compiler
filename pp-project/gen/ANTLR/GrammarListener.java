// Generated from D:/Mine/University/Modules/Module 8 - Programming Paradigms/mod8-final-project/pp-project/src/ANTLR\Grammar.g4 by ANTLR 4.9.1
package ANTLR;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(GrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(GrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classDec}
	 * labeled alternative in {@link GrammarParser#def}.
	 * @param ctx the parse tree
	 */
	void enterClassDec(GrammarParser.ClassDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classDec}
	 * labeled alternative in {@link GrammarParser#def}.
	 * @param ctx the parse tree
	 */
	void exitClassDec(GrammarParser.ClassDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBlockStat(GrammarParser.BlockStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBlockStat(GrammarParser.BlockStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decLock}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterDecLock(GrammarParser.DecLockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decLock}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitDecLock(GrammarParser.DecLockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(GrammarParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(GrammarParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(GrammarParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(GrammarParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileLoop}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(GrammarParser.WhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileLoop}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(GrammarParser.WhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code threadedBlock}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterThreadedBlock(GrammarParser.ThreadedBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code threadedBlock}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitThreadedBlock(GrammarParser.ThreadedBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callLock}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterCallLock(GrammarParser.CallLockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callLock}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitCallLock(GrammarParser.CallLockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code copyOver}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterCopyOver(GrammarParser.CopyOverContext ctx);
	/**
	 * Exit a parse tree produced by the {@code copyOver}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitCopyOver(GrammarParser.CopyOverContext ctx);
	/**
	 * Enter a parse tree produced by the {@code putLock}
	 * labeled alternative in {@link GrammarParser#lock}.
	 * @param ctx the parse tree
	 */
	void enterPutLock(GrammarParser.PutLockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code putLock}
	 * labeled alternative in {@link GrammarParser#lock}.
	 * @param ctx the parse tree
	 */
	void exitPutLock(GrammarParser.PutLockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code putUnlock}
	 * labeled alternative in {@link GrammarParser#lock}.
	 * @param ctx the parse tree
	 */
	void enterPutUnlock(GrammarParser.PutUnlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code putUnlock}
	 * labeled alternative in {@link GrammarParser#lock}.
	 * @param ctx the parse tree
	 */
	void exitPutUnlock(GrammarParser.PutUnlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParExpr(GrammarParser.ParExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParExpr(GrammarParser.ParExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(GrammarParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(GrammarParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpr(GrammarParser.ArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpr(GrammarParser.ArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(GrammarParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(GrammarParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompExpr(GrammarParser.CompExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompExpr(GrammarParser.CompExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(GrammarParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(GrammarParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstExpr(GrammarParser.ConstExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstExpr(GrammarParser.ConstExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(GrammarParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(GrammarParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(GrammarParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(GrammarParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrContents}
	 * labeled alternative in {@link GrammarParser#arr}.
	 * @param ctx the parse tree
	 */
	void enterArrContents(GrammarParser.ArrContentsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrContents}
	 * labeled alternative in {@link GrammarParser#arr}.
	 * @param ctx the parse tree
	 */
	void exitArrContents(GrammarParser.ArrContentsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyArr}
	 * labeled alternative in {@link GrammarParser#arr}.
	 * @param ctx the parse tree
	 */
	void enterEmptyArr(GrammarParser.EmptyArrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyArr}
	 * labeled alternative in {@link GrammarParser#arr}.
	 * @param ctx the parse tree
	 */
	void exitEmptyArr(GrammarParser.EmptyArrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intArray}
	 * labeled alternative in {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntArray(GrammarParser.IntArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intArray}
	 * labeled alternative in {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntArray(GrammarParser.IntArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolArray}
	 * labeled alternative in {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBoolArray(GrammarParser.BoolArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolArray}
	 * labeled alternative in {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBoolArray(GrammarParser.BoolArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterInt(GrammarParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitInt(GrammarParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bool}
	 * labeled alternative in {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBool(GrammarParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBool(GrammarParser.BoolContext ctx);
}