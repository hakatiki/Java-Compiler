// Generated from D:/Mine/University/Modules/Module 8 - Programming Paradigms/take-home-exam/pp-project/src\Grammar.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#text}.
	 * @param ctx the parse tree
	 */
	void enterText(GrammarParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#text}.
	 * @param ctx the parse tree
	 */
	void exitText(GrammarParser.TextContext ctx);
	/**
	 * Enter a parse tree produced by the {@code wordItem}
	 * labeled alternative in {@link GrammarParser#item}.
	 * @param ctx the parse tree
	 */
	void enterWordItem(GrammarParser.WordItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code wordItem}
	 * labeled alternative in {@link GrammarParser#item}.
	 * @param ctx the parse tree
	 */
	void exitWordItem(GrammarParser.WordItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textItem}
	 * labeled alternative in {@link GrammarParser#item}.
	 * @param ctx the parse tree
	 */
	void enterTextItem(GrammarParser.TextItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textItem}
	 * labeled alternative in {@link GrammarParser#item}.
	 * @param ctx the parse tree
	 */
	void exitTextItem(GrammarParser.TextItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code raiseItem}
	 * labeled alternative in {@link GrammarParser#item}.
	 * @param ctx the parse tree
	 */
	void enterRaiseItem(GrammarParser.RaiseItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code raiseItem}
	 * labeled alternative in {@link GrammarParser#item}.
	 * @param ctx the parse tree
	 */
	void exitRaiseItem(GrammarParser.RaiseItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#items}.
	 * @param ctx the parse tree
	 */
	void enterItems(GrammarParser.ItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#items}.
	 * @param ctx the parse tree
	 */
	void exitItems(GrammarParser.ItemsContext ctx);
}