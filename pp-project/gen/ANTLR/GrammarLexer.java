// Generated from D:/Mine/University/Modules/Module 8 - Programming Paradigms/mod8-final-project/pp-project/src/ANTLR\Grammar.g4 by ANTLR 4.9.1
package ANTLR;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, WS=9, 
		ID=10, NUM=11, THREADED=12, LOCK=13, DOT=14, SEMI=15, COMMA=16, LSQ=17, 
		RSQ=18, ASSIGN=19, NOT=20, OR=21, AND=22, BIT_OR=23, BIT_AND=24, PLUS=25, 
		MINUS=26, LTEQ=27, GTEQ=28, LT=29, GT=30, EQ=31, NE=32, LCURLY=33, RCURLY=34, 
		LPAR=35, RPAR=36, PRINT=37, WHILE=38, IF=39, ELSE=40, TRUE=41, FALSE=42, 
		CLASS=43;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "WS", 
			"ID", "NUM", "THREADED", "LOCK", "DOT", "SEMI", "COMMA", "LSQ", "RSQ", 
			"ASSIGN", "NOT", "OR", "AND", "BIT_OR", "BIT_AND", "PLUS", "MINUS", "LTEQ", 
			"GTEQ", "LT", "GT", "EQ", "NE", "LCURLY", "RCURLY", "LPAR", "RPAR", "PRINT", 
			"WHILE", "IF", "ELSE", "TRUE", "FALSE", "CLASS", "LETTER", "DIGIT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'main'", "'new'", "'lock'", "'unlock'", "'Int[]'", "'Bool[]'", 
			"'Int'", "'Bool'", null, null, null, "'@Threaded'", "'Lock'", "'.'", 
			"';'", "','", "'['", "']'", "'='", "'!'", "'||'", "'&&'", "'|'", "'&'", 
			"'+'", "'-'", "'<='", "'>='", "'<'", "'>'", "'=='", "'!='", "'{'", "'}'", 
			"'('", "')'", "'printf'", "'while'", "'if'", "'else'", "'True'", "'False'", 
			"'class'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "WS", "ID", "NUM", 
			"THREADED", "LOCK", "DOT", "SEMI", "COMMA", "LSQ", "RSQ", "ASSIGN", "NOT", 
			"OR", "AND", "BIT_OR", "BIT_AND", "PLUS", "MINUS", "LTEQ", "GTEQ", "LT", 
			"GT", "EQ", "NE", "LCURLY", "RCURLY", "LPAR", "RPAR", "PRINT", "WHILE", 
			"IF", "ELSE", "TRUE", "FALSE", "CLASS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2-\u0109\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\6\n\u008a\n\n\r\n"+
		"\16\n\u008b\3\n\3\n\3\13\3\13\3\13\7\13\u0093\n\13\f\13\16\13\u0096\13"+
		"\13\3\f\6\f\u0099\n\f\r\f\16\f\u009a\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30"+
		"\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3&\3&\3&"+
		"\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*"+
		"\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3-\3-\3.\3.\2\2/\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K"+
		"\'M(O)Q*S+U,W-Y\2[\2\3\2\5\5\2\13\f\17\17\"\"\4\2C\\c|\3\2\62;\2\u010a"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\3]\3\2\2\2\5b\3\2\2\2\7f\3\2\2\2\tk\3\2\2\2\13r\3"+
		"\2\2\2\rx\3\2\2\2\17\177\3\2\2\2\21\u0083\3\2\2\2\23\u0089\3\2\2\2\25"+
		"\u008f\3\2\2\2\27\u0098\3\2\2\2\31\u009c\3\2\2\2\33\u00a6\3\2\2\2\35\u00ab"+
		"\3\2\2\2\37\u00ad\3\2\2\2!\u00af\3\2\2\2#\u00b1\3\2\2\2%\u00b3\3\2\2\2"+
		"\'\u00b5\3\2\2\2)\u00b7\3\2\2\2+\u00b9\3\2\2\2-\u00bc\3\2\2\2/\u00bf\3"+
		"\2\2\2\61\u00c1\3\2\2\2\63\u00c3\3\2\2\2\65\u00c5\3\2\2\2\67\u00c7\3\2"+
		"\2\29\u00ca\3\2\2\2;\u00cd\3\2\2\2=\u00cf\3\2\2\2?\u00d1\3\2\2\2A\u00d4"+
		"\3\2\2\2C\u00d7\3\2\2\2E\u00d9\3\2\2\2G\u00db\3\2\2\2I\u00dd\3\2\2\2K"+
		"\u00df\3\2\2\2M\u00e6\3\2\2\2O\u00ec\3\2\2\2Q\u00ef\3\2\2\2S\u00f4\3\2"+
		"\2\2U\u00f9\3\2\2\2W\u00ff\3\2\2\2Y\u0105\3\2\2\2[\u0107\3\2\2\2]^\7o"+
		"\2\2^_\7c\2\2_`\7k\2\2`a\7p\2\2a\4\3\2\2\2bc\7p\2\2cd\7g\2\2de\7y\2\2"+
		"e\6\3\2\2\2fg\7n\2\2gh\7q\2\2hi\7e\2\2ij\7m\2\2j\b\3\2\2\2kl\7w\2\2lm"+
		"\7p\2\2mn\7n\2\2no\7q\2\2op\7e\2\2pq\7m\2\2q\n\3\2\2\2rs\7K\2\2st\7p\2"+
		"\2tu\7v\2\2uv\7]\2\2vw\7_\2\2w\f\3\2\2\2xy\7D\2\2yz\7q\2\2z{\7q\2\2{|"+
		"\7n\2\2|}\7]\2\2}~\7_\2\2~\16\3\2\2\2\177\u0080\7K\2\2\u0080\u0081\7p"+
		"\2\2\u0081\u0082\7v\2\2\u0082\20\3\2\2\2\u0083\u0084\7D\2\2\u0084\u0085"+
		"\7q\2\2\u0085\u0086\7q\2\2\u0086\u0087\7n\2\2\u0087\22\3\2\2\2\u0088\u008a"+
		"\t\2\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\b\n\2\2\u008e\24\3\2\2"+
		"\2\u008f\u0094\5Y-\2\u0090\u0093\5Y-\2\u0091\u0093\5[.\2\u0092\u0090\3"+
		"\2\2\2\u0092\u0091\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\26\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u0099\5[.\2"+
		"\u0098\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b"+
		"\3\2\2\2\u009b\30\3\2\2\2\u009c\u009d\7B\2\2\u009d\u009e\7V\2\2\u009e"+
		"\u009f\7j\2\2\u009f\u00a0\7t\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7c\2\2"+
		"\u00a2\u00a3\7f\2\2\u00a3\u00a4\7g\2\2\u00a4\u00a5\7f\2\2\u00a5\32\3\2"+
		"\2\2\u00a6\u00a7\7N\2\2\u00a7\u00a8\7q\2\2\u00a8\u00a9\7e\2\2\u00a9\u00aa"+
		"\7m\2\2\u00aa\34\3\2\2\2\u00ab\u00ac\7\60\2\2\u00ac\36\3\2\2\2\u00ad\u00ae"+
		"\7=\2\2\u00ae \3\2\2\2\u00af\u00b0\7.\2\2\u00b0\"\3\2\2\2\u00b1\u00b2"+
		"\7]\2\2\u00b2$\3\2\2\2\u00b3\u00b4\7_\2\2\u00b4&\3\2\2\2\u00b5\u00b6\7"+
		"?\2\2\u00b6(\3\2\2\2\u00b7\u00b8\7#\2\2\u00b8*\3\2\2\2\u00b9\u00ba\7~"+
		"\2\2\u00ba\u00bb\7~\2\2\u00bb,\3\2\2\2\u00bc\u00bd\7(\2\2\u00bd\u00be"+
		"\7(\2\2\u00be.\3\2\2\2\u00bf\u00c0\7~\2\2\u00c0\60\3\2\2\2\u00c1\u00c2"+
		"\7(\2\2\u00c2\62\3\2\2\2\u00c3\u00c4\7-\2\2\u00c4\64\3\2\2\2\u00c5\u00c6"+
		"\7/\2\2\u00c6\66\3\2\2\2\u00c7\u00c8\7>\2\2\u00c8\u00c9\7?\2\2\u00c98"+
		"\3\2\2\2\u00ca\u00cb\7@\2\2\u00cb\u00cc\7?\2\2\u00cc:\3\2\2\2\u00cd\u00ce"+
		"\7>\2\2\u00ce<\3\2\2\2\u00cf\u00d0\7@\2\2\u00d0>\3\2\2\2\u00d1\u00d2\7"+
		"?\2\2\u00d2\u00d3\7?\2\2\u00d3@\3\2\2\2\u00d4\u00d5\7#\2\2\u00d5\u00d6"+
		"\7?\2\2\u00d6B\3\2\2\2\u00d7\u00d8\7}\2\2\u00d8D\3\2\2\2\u00d9\u00da\7"+
		"\177\2\2\u00daF\3\2\2\2\u00db\u00dc\7*\2\2\u00dcH\3\2\2\2\u00dd\u00de"+
		"\7+\2\2\u00deJ\3\2\2\2\u00df\u00e0\7r\2\2\u00e0\u00e1\7t\2\2\u00e1\u00e2"+
		"\7k\2\2\u00e2\u00e3\7p\2\2\u00e3\u00e4\7v\2\2\u00e4\u00e5\7h\2\2\u00e5"+
		"L\3\2\2\2\u00e6\u00e7\7y\2\2\u00e7\u00e8\7j\2\2\u00e8\u00e9\7k\2\2\u00e9"+
		"\u00ea\7n\2\2\u00ea\u00eb\7g\2\2\u00ebN\3\2\2\2\u00ec\u00ed\7k\2\2\u00ed"+
		"\u00ee\7h\2\2\u00eeP\3\2\2\2\u00ef\u00f0\7g\2\2\u00f0\u00f1\7n\2\2\u00f1"+
		"\u00f2\7u\2\2\u00f2\u00f3\7g\2\2\u00f3R\3\2\2\2\u00f4\u00f5\7V\2\2\u00f5"+
		"\u00f6\7t\2\2\u00f6\u00f7\7w\2\2\u00f7\u00f8\7g\2\2\u00f8T\3\2\2\2\u00f9"+
		"\u00fa\7H\2\2\u00fa\u00fb\7c\2\2\u00fb\u00fc\7n\2\2\u00fc\u00fd\7u\2\2"+
		"\u00fd\u00fe\7g\2\2\u00feV\3\2\2\2\u00ff\u0100\7e\2\2\u0100\u0101\7n\2"+
		"\2\u0101\u0102\7c\2\2\u0102\u0103\7u\2\2\u0103\u0104\7u\2\2\u0104X\3\2"+
		"\2\2\u0105\u0106\t\3\2\2\u0106Z\3\2\2\2\u0107\u0108\t\4\2\2\u0108\\\3"+
		"\2\2\2\7\2\u008b\u0092\u0094\u009a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}