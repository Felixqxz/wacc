// Generated from WaccLexer.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WaccLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, WS=2, BEGIN=3, DEFINE=4, END=5, IS=6, SKIPP=7, BREAK=8, CONTINUE=9, 
		READ=10, FREE=11, RETURN=12, EXIT=13, PRINT=14, PRINTLN=15, IF=16, THEN=17, 
		ELSE=18, FI=19, FOR=20, WHILE=21, SWITCH=22, CASE=23, DEFAULT=24, DO=25, 
		DONE=26, NEWPAIR=27, CALL=28, FST=29, SND=30, INT=31, BOOL=32, CHAR=33, 
		STRING=34, PAIR=35, TRUE=36, FALSE=37, NOT=38, LEN=39, ORD=40, CHR=41, 
		BITNOT=42, MULT=43, DIV=44, MOD=45, PLUS=46, MINUS=47, EQUAL=48, GREATER=49, 
		GREATER_EQ=50, LESS=51, LESS_EQ=52, EQ=53, NOT_EQ=54, AND=55, OR=56, BITAND=57, 
		BITOR=58, OPEN_PARENTHESES=59, CLOSE_PARENTHESES=60, OPEN_BRACKETS=61, 
		CLOSE_BRACKETS=62, COMMA=63, SEMICOLON=64, COLON=65, SINGLE_QUOTE=66, 
		DOUBLE_QUOTE=67, NULL=68, IDENT=69, DIGIT=70, LETTER=71, UNDERSCORE=72, 
		CHAR_LITER=73, STRING_LITER=74;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMENT", "WS", "BEGIN", "DEFINE", "END", "IS", "SKIPP", "BREAK", "CONTINUE", 
			"READ", "FREE", "RETURN", "EXIT", "PRINT", "PRINTLN", "IF", "THEN", "ELSE", 
			"FI", "FOR", "WHILE", "SWITCH", "CASE", "DEFAULT", "DO", "DONE", "NEWPAIR", 
			"CALL", "FST", "SND", "INT", "BOOL", "CHAR", "STRING", "PAIR", "TRUE", 
			"FALSE", "NOT", "LEN", "ORD", "CHR", "BITNOT", "MULT", "DIV", "MOD", 
			"PLUS", "MINUS", "EQUAL", "GREATER", "GREATER_EQ", "LESS", "LESS_EQ", 
			"EQ", "NOT_EQ", "AND", "OR", "BITAND", "BITOR", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", 
			"OPEN_BRACKETS", "CLOSE_BRACKETS", "COMMA", "SEMICOLON", "COLON", "SINGLE_QUOTE", 
			"DOUBLE_QUOTE", "NULL", "IDENT", "DIGIT", "LETTER", "UNDERSCORE", "ESCAPED_CHAR", 
			"CHARACTER", "CHAR_LITER", "STRING_LITER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'begin'", "'define'", "'end'", "'is'", "'skip'", "'break'", 
			"'continue'", "'read'", "'free'", "'return'", "'exit'", "'print'", "'println'", 
			"'if'", "'then'", "'else'", "'fi'", "'for'", "'while'", "'switch'", "'case'", 
			"'default'", "'do'", "'done'", "'newpair'", "'call'", "'fst'", "'snd'", 
			"'int'", "'bool'", "'char'", "'string'", "'pair'", "'true'", "'false'", 
			"'!'", "'len'", "'ord'", "'chr'", "'~'", "'*'", "'/'", "'%'", "'+'", 
			"'-'", "'='", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'&&'", "'||'", 
			"'&'", "'|'", "'('", "')'", "'['", "']'", "','", "';'", "':'", "'''", 
			"'\"'", "'null'", null, null, null, "'_'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMENT", "WS", "BEGIN", "DEFINE", "END", "IS", "SKIPP", "BREAK", 
			"CONTINUE", "READ", "FREE", "RETURN", "EXIT", "PRINT", "PRINTLN", "IF", 
			"THEN", "ELSE", "FI", "FOR", "WHILE", "SWITCH", "CASE", "DEFAULT", "DO", 
			"DONE", "NEWPAIR", "CALL", "FST", "SND", "INT", "BOOL", "CHAR", "STRING", 
			"PAIR", "TRUE", "FALSE", "NOT", "LEN", "ORD", "CHR", "BITNOT", "MULT", 
			"DIV", "MOD", "PLUS", "MINUS", "EQUAL", "GREATER", "GREATER_EQ", "LESS", 
			"LESS_EQ", "EQ", "NOT_EQ", "AND", "OR", "BITAND", "BITOR", "OPEN_PARENTHESES", 
			"CLOSE_PARENTHESES", "OPEN_BRACKETS", "CLOSE_BRACKETS", "COMMA", "SEMICOLON", 
			"COLON", "SINGLE_QUOTE", "DOUBLE_QUOTE", "NULL", "IDENT", "DIGIT", "LETTER", 
			"UNDERSCORE", "CHAR_LITER", "STRING_LITER"
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


	public WaccLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "WaccLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2L\u01eb\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\3\2\3\2\7\2\u009e\n\2\f\2\16\2\u00a1\13\2"+
		"\3\2\3\2\3\2\3\2\3\3\6\3\u00a8\n\3\r\3\16\3\u00a9\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3"+
		"\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3"+
		"\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 "+
		"\3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3$\3$\3"+
		"$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3\'\3\'\3(\3(\3(\3(\3)\3)\3)"+
		"\3)\3*\3*\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62"+
		"\3\62\3\63\3\63\3\63\3\64\3\64\3\65\3\65\3\65\3\66\3\66\3\66\3\67\3\67"+
		"\3\67\38\38\38\39\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3"+
		"A\3B\3B\3C\3C\3D\3D\3E\3E\3E\3E\3E\3F\3F\5F\u01b9\nF\3F\3F\3F\7F\u01be"+
		"\nF\fF\16F\u01c1\13F\3G\3G\3H\3H\3I\3I\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3"+
		"J\3J\3J\3J\3J\3J\5J\u01d9\nJ\3K\3K\5K\u01dd\nK\3L\3L\3L\3L\3M\3M\7M\u01e5"+
		"\nM\fM\16M\u01e8\13M\3M\3M\2\2N\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60"+
		"_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083C\u0085"+
		"D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091J\u0093\2\u0095\2\u0097K\u0099"+
		"L\3\2\6\4\2\f\f\17\17\5\2\13\f\17\17\"\"\4\2C\\c|\5\2$$))^^\2\u01f7\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U"+
		"\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2"+
		"\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2"+
		"\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{"+
		"\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085"+
		"\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2"+
		"\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\3\u009b"+
		"\3\2\2\2\5\u00a7\3\2\2\2\7\u00ad\3\2\2\2\t\u00b3\3\2\2\2\13\u00ba\3\2"+
		"\2\2\r\u00be\3\2\2\2\17\u00c1\3\2\2\2\21\u00c6\3\2\2\2\23\u00cc\3\2\2"+
		"\2\25\u00d5\3\2\2\2\27\u00da\3\2\2\2\31\u00df\3\2\2\2\33\u00e6\3\2\2\2"+
		"\35\u00eb\3\2\2\2\37\u00f1\3\2\2\2!\u00f9\3\2\2\2#\u00fc\3\2\2\2%\u0101"+
		"\3\2\2\2\'\u0106\3\2\2\2)\u0109\3\2\2\2+\u010d\3\2\2\2-\u0113\3\2\2\2"+
		"/\u011a\3\2\2\2\61\u011f\3\2\2\2\63\u0127\3\2\2\2\65\u012a\3\2\2\2\67"+
		"\u012f\3\2\2\29\u0137\3\2\2\2;\u013c\3\2\2\2=\u0140\3\2\2\2?\u0144\3\2"+
		"\2\2A\u0148\3\2\2\2C\u014d\3\2\2\2E\u0152\3\2\2\2G\u0159\3\2\2\2I\u015e"+
		"\3\2\2\2K\u0163\3\2\2\2M\u0169\3\2\2\2O\u016b\3\2\2\2Q\u016f\3\2\2\2S"+
		"\u0173\3\2\2\2U\u0177\3\2\2\2W\u0179\3\2\2\2Y\u017b\3\2\2\2[\u017d\3\2"+
		"\2\2]\u017f\3\2\2\2_\u0181\3\2\2\2a\u0183\3\2\2\2c\u0185\3\2\2\2e\u0187"+
		"\3\2\2\2g\u018a\3\2\2\2i\u018c\3\2\2\2k\u018f\3\2\2\2m\u0192\3\2\2\2o"+
		"\u0195\3\2\2\2q\u0198\3\2\2\2s\u019b\3\2\2\2u\u019d\3\2\2\2w\u019f\3\2"+
		"\2\2y\u01a1\3\2\2\2{\u01a3\3\2\2\2}\u01a5\3\2\2\2\177\u01a7\3\2\2\2\u0081"+
		"\u01a9\3\2\2\2\u0083\u01ab\3\2\2\2\u0085\u01ad\3\2\2\2\u0087\u01af\3\2"+
		"\2\2\u0089\u01b1\3\2\2\2\u008b\u01b8\3\2\2\2\u008d\u01c2\3\2\2\2\u008f"+
		"\u01c4\3\2\2\2\u0091\u01c6\3\2\2\2\u0093\u01d8\3\2\2\2\u0095\u01dc\3\2"+
		"\2\2\u0097\u01de\3\2\2\2\u0099\u01e2\3\2\2\2\u009b\u009f\7%\2\2\u009c"+
		"\u009e\n\2\2\2\u009d\u009c\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2"+
		"\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2"+
		"\u00a3\t\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\b\2\2\2\u00a5\4\3\2\2\2"+
		"\u00a6\u00a8\t\3\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00a7"+
		"\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\b\3\3\2\u00ac"+
		"\6\3\2\2\2\u00ad\u00ae\7d\2\2\u00ae\u00af\7g\2\2\u00af\u00b0\7i\2\2\u00b0"+
		"\u00b1\7k\2\2\u00b1\u00b2\7p\2\2\u00b2\b\3\2\2\2\u00b3\u00b4\7f\2\2\u00b4"+
		"\u00b5\7g\2\2\u00b5\u00b6\7h\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8\7p\2\2"+
		"\u00b8\u00b9\7g\2\2\u00b9\n\3\2\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc\7p"+
		"\2\2\u00bc\u00bd\7f\2\2\u00bd\f\3\2\2\2\u00be\u00bf\7k\2\2\u00bf\u00c0"+
		"\7u\2\2\u00c0\16\3\2\2\2\u00c1\u00c2\7u\2\2\u00c2\u00c3\7m\2\2\u00c3\u00c4"+
		"\7k\2\2\u00c4\u00c5\7r\2\2\u00c5\20\3\2\2\2\u00c6\u00c7\7d\2\2\u00c7\u00c8"+
		"\7t\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7c\2\2\u00ca\u00cb\7m\2\2\u00cb"+
		"\22\3\2\2\2\u00cc\u00cd\7e\2\2\u00cd\u00ce\7q\2\2\u00ce\u00cf\7p\2\2\u00cf"+
		"\u00d0\7v\2\2\u00d0\u00d1\7k\2\2\u00d1\u00d2\7p\2\2\u00d2\u00d3\7w\2\2"+
		"\u00d3\u00d4\7g\2\2\u00d4\24\3\2\2\2\u00d5\u00d6\7t\2\2\u00d6\u00d7\7"+
		"g\2\2\u00d7\u00d8\7c\2\2\u00d8\u00d9\7f\2\2\u00d9\26\3\2\2\2\u00da\u00db"+
		"\7h\2\2\u00db\u00dc\7t\2\2\u00dc\u00dd\7g\2\2\u00dd\u00de\7g\2\2\u00de"+
		"\30\3\2\2\2\u00df\u00e0\7t\2\2\u00e0\u00e1\7g\2\2\u00e1\u00e2\7v\2\2\u00e2"+
		"\u00e3\7w\2\2\u00e3\u00e4\7t\2\2\u00e4\u00e5\7p\2\2\u00e5\32\3\2\2\2\u00e6"+
		"\u00e7\7g\2\2\u00e7\u00e8\7z\2\2\u00e8\u00e9\7k\2\2\u00e9\u00ea\7v\2\2"+
		"\u00ea\34\3\2\2\2\u00eb\u00ec\7r\2\2\u00ec\u00ed\7t\2\2\u00ed\u00ee\7"+
		"k\2\2\u00ee\u00ef\7p\2\2\u00ef\u00f0\7v\2\2\u00f0\36\3\2\2\2\u00f1\u00f2"+
		"\7r\2\2\u00f2\u00f3\7t\2\2\u00f3\u00f4\7k\2\2\u00f4\u00f5\7p\2\2\u00f5"+
		"\u00f6\7v\2\2\u00f6\u00f7\7n\2\2\u00f7\u00f8\7p\2\2\u00f8 \3\2\2\2\u00f9"+
		"\u00fa\7k\2\2\u00fa\u00fb\7h\2\2\u00fb\"\3\2\2\2\u00fc\u00fd\7v\2\2\u00fd"+
		"\u00fe\7j\2\2\u00fe\u00ff\7g\2\2\u00ff\u0100\7p\2\2\u0100$\3\2\2\2\u0101"+
		"\u0102\7g\2\2\u0102\u0103\7n\2\2\u0103\u0104\7u\2\2\u0104\u0105\7g\2\2"+
		"\u0105&\3\2\2\2\u0106\u0107\7h\2\2\u0107\u0108\7k\2\2\u0108(\3\2\2\2\u0109"+
		"\u010a\7h\2\2\u010a\u010b\7q\2\2\u010b\u010c\7t\2\2\u010c*\3\2\2\2\u010d"+
		"\u010e\7y\2\2\u010e\u010f\7j\2\2\u010f\u0110\7k\2\2\u0110\u0111\7n\2\2"+
		"\u0111\u0112\7g\2\2\u0112,\3\2\2\2\u0113\u0114\7u\2\2\u0114\u0115\7y\2"+
		"\2\u0115\u0116\7k\2\2\u0116\u0117\7v\2\2\u0117\u0118\7e\2\2\u0118\u0119"+
		"\7j\2\2\u0119.\3\2\2\2\u011a\u011b\7e\2\2\u011b\u011c\7c\2\2\u011c\u011d"+
		"\7u\2\2\u011d\u011e\7g\2\2\u011e\60\3\2\2\2\u011f\u0120\7f\2\2\u0120\u0121"+
		"\7g\2\2\u0121\u0122\7h\2\2\u0122\u0123\7c\2\2\u0123\u0124\7w\2\2\u0124"+
		"\u0125\7n\2\2\u0125\u0126\7v\2\2\u0126\62\3\2\2\2\u0127\u0128\7f\2\2\u0128"+
		"\u0129\7q\2\2\u0129\64\3\2\2\2\u012a\u012b\7f\2\2\u012b\u012c\7q\2\2\u012c"+
		"\u012d\7p\2\2\u012d\u012e\7g\2\2\u012e\66\3\2\2\2\u012f\u0130\7p\2\2\u0130"+
		"\u0131\7g\2\2\u0131\u0132\7y\2\2\u0132\u0133\7r\2\2\u0133\u0134\7c\2\2"+
		"\u0134\u0135\7k\2\2\u0135\u0136\7t\2\2\u01368\3\2\2\2\u0137\u0138\7e\2"+
		"\2\u0138\u0139\7c\2\2\u0139\u013a\7n\2\2\u013a\u013b\7n\2\2\u013b:\3\2"+
		"\2\2\u013c\u013d\7h\2\2\u013d\u013e\7u\2\2\u013e\u013f\7v\2\2\u013f<\3"+
		"\2\2\2\u0140\u0141\7u\2\2\u0141\u0142\7p\2\2\u0142\u0143\7f\2\2\u0143"+
		">\3\2\2\2\u0144\u0145\7k\2\2\u0145\u0146\7p\2\2\u0146\u0147\7v\2\2\u0147"+
		"@\3\2\2\2\u0148\u0149\7d\2\2\u0149\u014a\7q\2\2\u014a\u014b\7q\2\2\u014b"+
		"\u014c\7n\2\2\u014cB\3\2\2\2\u014d\u014e\7e\2\2\u014e\u014f\7j\2\2\u014f"+
		"\u0150\7c\2\2\u0150\u0151\7t\2\2\u0151D\3\2\2\2\u0152\u0153\7u\2\2\u0153"+
		"\u0154\7v\2\2\u0154\u0155\7t\2\2\u0155\u0156\7k\2\2\u0156\u0157\7p\2\2"+
		"\u0157\u0158\7i\2\2\u0158F\3\2\2\2\u0159\u015a\7r\2\2\u015a\u015b\7c\2"+
		"\2\u015b\u015c\7k\2\2\u015c\u015d\7t\2\2\u015dH\3\2\2\2\u015e\u015f\7"+
		"v\2\2\u015f\u0160\7t\2\2\u0160\u0161\7w\2\2\u0161\u0162\7g\2\2\u0162J"+
		"\3\2\2\2\u0163\u0164\7h\2\2\u0164\u0165\7c\2\2\u0165\u0166\7n\2\2\u0166"+
		"\u0167\7u\2\2\u0167\u0168\7g\2\2\u0168L\3\2\2\2\u0169\u016a\7#\2\2\u016a"+
		"N\3\2\2\2\u016b\u016c\7n\2\2\u016c\u016d\7g\2\2\u016d\u016e\7p\2\2\u016e"+
		"P\3\2\2\2\u016f\u0170\7q\2\2\u0170\u0171\7t\2\2\u0171\u0172\7f\2\2\u0172"+
		"R\3\2\2\2\u0173\u0174\7e\2\2\u0174\u0175\7j\2\2\u0175\u0176\7t\2\2\u0176"+
		"T\3\2\2\2\u0177\u0178\7\u0080\2\2\u0178V\3\2\2\2\u0179\u017a\7,\2\2\u017a"+
		"X\3\2\2\2\u017b\u017c\7\61\2\2\u017cZ\3\2\2\2\u017d\u017e\7\'\2\2\u017e"+
		"\\\3\2\2\2\u017f\u0180\7-\2\2\u0180^\3\2\2\2\u0181\u0182\7/\2\2\u0182"+
		"`\3\2\2\2\u0183\u0184\7?\2\2\u0184b\3\2\2\2\u0185\u0186\7@\2\2\u0186d"+
		"\3\2\2\2\u0187\u0188\7@\2\2\u0188\u0189\7?\2\2\u0189f\3\2\2\2\u018a\u018b"+
		"\7>\2\2\u018bh\3\2\2\2\u018c\u018d\7>\2\2\u018d\u018e\7?\2\2\u018ej\3"+
		"\2\2\2\u018f\u0190\7?\2\2\u0190\u0191\7?\2\2\u0191l\3\2\2\2\u0192\u0193"+
		"\7#\2\2\u0193\u0194\7?\2\2\u0194n\3\2\2\2\u0195\u0196\7(\2\2\u0196\u0197"+
		"\7(\2\2\u0197p\3\2\2\2\u0198\u0199\7~\2\2\u0199\u019a\7~\2\2\u019ar\3"+
		"\2\2\2\u019b\u019c\7(\2\2\u019ct\3\2\2\2\u019d\u019e\7~\2\2\u019ev\3\2"+
		"\2\2\u019f\u01a0\7*\2\2\u01a0x\3\2\2\2\u01a1\u01a2\7+\2\2\u01a2z\3\2\2"+
		"\2\u01a3\u01a4\7]\2\2\u01a4|\3\2\2\2\u01a5\u01a6\7_\2\2\u01a6~\3\2\2\2"+
		"\u01a7\u01a8\7.\2\2\u01a8\u0080\3\2\2\2\u01a9\u01aa\7=\2\2\u01aa\u0082"+
		"\3\2\2\2\u01ab\u01ac\7<\2\2\u01ac\u0084\3\2\2\2\u01ad\u01ae\7)\2\2\u01ae"+
		"\u0086\3\2\2\2\u01af\u01b0\7$\2\2\u01b0\u0088\3\2\2\2\u01b1\u01b2\7p\2"+
		"\2\u01b2\u01b3\7w\2\2\u01b3\u01b4\7n\2\2\u01b4\u01b5\7n\2\2\u01b5\u008a"+
		"\3\2\2\2\u01b6\u01b9\5\u0091I\2\u01b7\u01b9\5\u008fH\2\u01b8\u01b6\3\2"+
		"\2\2\u01b8\u01b7\3\2\2\2\u01b9\u01bf\3\2\2\2\u01ba\u01be\5\u0091I\2\u01bb"+
		"\u01be\5\u008fH\2\u01bc\u01be\5\u008dG\2\u01bd\u01ba\3\2\2\2\u01bd\u01bb"+
		"\3\2\2\2\u01bd\u01bc\3\2\2\2\u01be\u01c1\3\2\2\2\u01bf\u01bd\3\2\2\2\u01bf"+
		"\u01c0\3\2\2\2\u01c0\u008c\3\2\2\2\u01c1\u01bf\3\2\2\2\u01c2\u01c3\4\62"+
		";\2\u01c3\u008e\3\2\2\2\u01c4\u01c5\t\4\2\2\u01c5\u0090\3\2\2\2\u01c6"+
		"\u01c7\7a\2\2\u01c7\u0092\3\2\2\2\u01c8\u01c9\7^\2\2\u01c9\u01d9\7\62"+
		"\2\2\u01ca\u01cb\7^\2\2\u01cb\u01d9\7d\2\2\u01cc\u01cd\7^\2\2\u01cd\u01d9"+
		"\7p\2\2\u01ce\u01cf\7^\2\2\u01cf\u01d9\7h\2\2\u01d0\u01d1\7^\2\2\u01d1"+
		"\u01d9\7t\2\2\u01d2\u01d3\7^\2\2\u01d3\u01d9\5\u0087D\2\u01d4\u01d5\7"+
		"^\2\2\u01d5\u01d9\5\u0085C\2\u01d6\u01d7\7^\2\2\u01d7\u01d9\7^\2\2\u01d8"+
		"\u01c8\3\2\2\2\u01d8\u01ca\3\2\2\2\u01d8\u01cc\3\2\2\2\u01d8\u01ce\3\2"+
		"\2\2\u01d8\u01d0\3\2\2\2\u01d8\u01d2\3\2\2\2\u01d8\u01d4\3\2\2\2\u01d8"+
		"\u01d6\3\2\2\2\u01d9\u0094\3\2\2\2\u01da\u01dd\n\5\2\2\u01db\u01dd\5\u0093"+
		"J\2\u01dc\u01da\3\2\2\2\u01dc\u01db\3\2\2\2\u01dd\u0096\3\2\2\2\u01de"+
		"\u01df\5\u0085C\2\u01df\u01e0\5\u0095K\2\u01e0\u01e1\5\u0085C\2\u01e1"+
		"\u0098\3\2\2\2\u01e2\u01e6\5\u0087D\2\u01e3\u01e5\5\u0095K\2\u01e4\u01e3"+
		"\3\2\2\2\u01e5\u01e8\3\2\2\2\u01e6\u01e4\3\2\2\2\u01e6\u01e7\3\2\2\2\u01e7"+
		"\u01e9\3\2\2\2\u01e8\u01e6\3\2\2\2\u01e9\u01ea\5\u0087D\2\u01ea\u009a"+
		"\3\2\2\2\13\2\u009f\u00a9\u01b8\u01bd\u01bf\u01d8\u01dc\u01e6\4\b\2\2"+
		"\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}