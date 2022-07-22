import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.BitSet;
import java.util.Collections;
import java.util.List;

public class WaccListener extends BaseErrorListener {

    /* To count total number of syntax errors */
    private int errorCounter = 0;
    private static final int SYNTAX_ERROR_CODE = 100;

    /* To print total number of syntax errors */
    public void printSyntaxErrors() {
        if (errorCounter != 0) {
            System.err.println(errorCounter + " parser error(s) detected, no further compilation attempted.");
        }
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        System.err.println("Errors detected during compilation! Exit code 100 returned.\n" +
                "Syntactic Error at " + line + ":" + charPositionInLine + " " + msg);
        errorCounter++;
        System.exit(SYNTAX_ERROR_CODE);
    }
}
