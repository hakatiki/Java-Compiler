// Generated from D:/Mine/University/Modules/Module 8 - Programming Paradigms/take-home-exam/pp-project/src\Grammar.g4 by ANTLR 4.9.1
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
	 * Visit a parse tree produced by {@link GrammarParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText(GrammarParser.TextContext ctx);
	/**
	 * Visit a parse tree produced by the {@code wordItem}
	 * labeled alternative in {@link GrammarParser#item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWordItem(GrammarParser.WordItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textItem}
	 * labeled alternative in {@link GrammarParser#item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextItem(GrammarParser.TextItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code raiseItem}
	 * labeled alternative in {@link GrammarParser#item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRaiseItem(GrammarParser.RaiseItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#items}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItems(GrammarParser.ItemsContext ctx);
}