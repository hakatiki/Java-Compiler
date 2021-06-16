// Generated from /Users/takatsbalint/Dokumentumok/Hazi/UofT/BigProject/mod8-final-project/pp-project/src/ANTLR/Grammar.g4 by ANTLR 4.9.1
package ANTLR;
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
	 * Visit a parse tree produced by {@link GrammarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(GrammarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classDec}
	 * labeled alternative in {@link GrammarParser#def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDec(GrammarParser.ClassDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(GrammarParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decLock}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecLock(GrammarParser.DecLockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callLock}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallLock(GrammarParser.CallLockContext ctx);
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
	T visitThreadedBlock(GrammarParser.ThreadedBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link GrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStat(GrammarParser.BlockStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code putLock}
	 * labeled alternative in {@link GrammarParser#lock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPutLock(GrammarParser.PutLockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code putUnlock}
	 * labeled alternative in {@link GrammarParser#lock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPutUnlock(GrammarParser.PutUnlockContext ctx);
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
	 * Visit a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(GrammarParser.TypeContext ctx);
}