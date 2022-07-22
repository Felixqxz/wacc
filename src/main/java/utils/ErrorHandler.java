package utils;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import utils.Type;

public class ErrorHandler {

  /* Error codes. */
  private static final int SEMANTIC_ERROR_CODE = 200;
  private static final int SYNTAX_ERROR_CODE = 100;

  public static final int CHARACTER_MAX_VALUE = 255;

  /*-------------Error handlers for generating different error messages.-------------*/
  public void integerOverflow() {
    System.err.println("Errors detected during compilation! Exit code 100 returned.");
    System.err.println("Integer overflow, expected between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE + ".");
    System.exit(SYNTAX_ERROR_CODE);
  }

  public void characterOverflow() {
    System.err.println("Errors detected during compilation! Exit code 100 returned.");
    System.err.println("Character overflow, expected between " + 0 + " and " + CHARACTER_MAX_VALUE + ".");
    System.exit(SYNTAX_ERROR_CODE);
  }

  public void missingReturn(String ident) {
    System.err.println("Errors detected during compilation! Exit code 100 returned.");
    System.err.println("Funtion " + ident + " is not ended with a return or an exit statement.");
    System.exit(SYNTAX_ERROR_CODE);
  }

  public void typeMismatch(ParseTree ctx, Type t1, Type t2) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + "-- Incompatible type at " + ctx.getText() +
            " (expected: " + t1 +  ", actual: " + t2 + ").");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  public void typeMismatchArrayType(ParseTree ctx, Type t1) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + " -- Incompatible type at " + ctx.getText() +
            " (expected: Any[], actual: " + t1 +")");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  public void incompatibleType(ParseTree ctx, Type type) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + "-- Incompatible type " + type + ".");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  public void functionUndefined(ParseTree ctx, String ident) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + "-- Variable " + ident + " is not defined in this scope.");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  public void functionRedefined(ParseTree ctx, String ident) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + "-- \" " + ident + " \" is already defined in " +
            "this scope.");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  public void variableUndefined(ParseTree ctx, String ident) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + "-- Variable " + ident + " is not defined in this scope.");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  public void variableRedefined(ParseTree ctx, String ident) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + "-- \" " + ident + " \" is already defined in " +
            "this scope.");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  public void incorrectArgsNumber(ParseTree ctx, String ident, int expected, int actual) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + "-- Incorrect number of parameters for " + ident + "" +
            " (expected: " + expected + ", actual: " + actual + ")");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  public void accessingNullPair(ParseTree ctx) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + "-- Accessing element of a null pair");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  public void globalReturn(ParseTree ctx) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + "-- Cannot return from the global scope.");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  public void isNotAssign(ParseTree ctx) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + "-- Variable should be changed here.");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  public void isNotCondition(ParseTree ctx) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + "-- End condition is missing.");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  public void isNotDeclareOrAssign(ParseTree ctx) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + "-- Variable should be initialised here.");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  public void invalidArrayAccess(ParseTree ctx) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + "-- Invalid Array Access.");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  public void arrayOutOfBoundError(ParseTree ctx) {
    System.err.println("Errors detected during compilation! Exit code 200 returned.");
    System.err.println("Semantic Error at " + getLine(ctx) + "-- Array out of bound.");
    System.exit(SEMANTIC_ERROR_CODE);
  }

  /* Helper function for getting the line number and position in a program, given a ctx.*/
  private String getLine(ParseTree ctx) {
    int line = 0;
    int pos = 0;
    if(ctx instanceof ParserRuleContext) {
      ParserRuleContext ctxp = (ParserRuleContext) ctx;
      line = ctxp.getStart().getLine();
      pos = ctxp.getStart().getCharPositionInLine();
    } else if(ctx instanceof TerminalNode) {
      TerminalNode ctxt = (TerminalNode) ctx;
      line = ctxt.getSymbol().getLine();
      pos = ctxt.getSymbol().getCharPositionInLine();
    }
    return "line " + line + ":" + pos + " ";
  }
}
