// Generated from D:/Mine/University/Modules/Module 8 - Programming Paradigms/mod8-final-project/pp-project/src/ANTLR\Grammar.g4 by ANTLR 4.9.1
package ANTLR;
import Generation.TooManyThreadsException;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code beginDec}
	 * labeled alternative in {@link GrammarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginDec(GrammarParser.BeginDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classDec}
	 * labeled alternative in {@link GrammarParser#def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDec(GrammarParser.ClassDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code threadedDec}
	 * labeled alternative in {@link GrammarParser#thread}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThreadedDec(GrammarParser.ThreadedDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStat(GrammarParser.BlockStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(GrammarParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(GrammarParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileLoop}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoop(GrammarParser.WhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code threadedBlock}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThreadedBlock(GrammarParser.ThreadedBlockContext ctx) throws TooManyThreadsException;
	/**
	 * Visit a parse tree produced by the {@code putLock}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPutLock(GrammarParser.PutLockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code putUnlock}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPutUnlock(GrammarParser.PutUnlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code copyOver}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCopyOver(GrammarParser.CopyOverContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setIndex}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetIndex(GrammarParser.SetIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code output}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutput(GrammarParser.OutputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code threadedBlockStat}
	 * labeled alternative in {@link GrammarParser#tstat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThreadedBlockStat(GrammarParser.ThreadedBlockStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpr(GrammarParser.ParExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(GrammarParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExpr(GrammarParser.ArrayExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getThreadId}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetThreadId(GrammarParser.GetThreadIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getIndex}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetIndex(GrammarParser.GetIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(GrammarParser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpr(GrammarParser.CompExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(GrammarParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr(GrammarParser.MultExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstExpr(GrammarParser.ConstExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(GrammarParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(GrammarParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrContents}
	 * labeled alternative in {@link GrammarParser#arr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrContents(GrammarParser.ArrContentsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptyArr}
	 * labeled alternative in {@link GrammarParser#arr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyArr(GrammarParser.EmptyArrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intArray}
	 * labeled alternative in {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntArray(GrammarParser.IntArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolArray}
	 * labeled alternative in {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolArray(GrammarParser.BoolArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(GrammarParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(GrammarParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isLocal}
	 * labeled alternative in {@link GrammarParser#mem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsLocal(GrammarParser.IsLocalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isShared}
	 * labeled alternative in {@link GrammarParser#mem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsShared(GrammarParser.IsSharedContext ctx);
}