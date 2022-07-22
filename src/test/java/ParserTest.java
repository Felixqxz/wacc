import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

public class ParserTest {

  private String processTree(String file) throws IOException {
    InputStream stream = new FileInputStream(file);
    CharStream input = CharStreams.fromStream(stream);
    WaccLexer lexer = new WaccLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    WaccParser parser = new WaccParser(tokens);
    ParseTree tree = parser.prog();
    return tree.toStringTree(parser);
  }

  @Test
  public void BasicExitTest() throws IOException {
    String file = "wacc_examples/valid/basic/exit/exit-1.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat exit (expr (int_liter - 1))) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void AndExpressionTest() throws IOException {
    String file = "wacc_examples/valid/expressions/andExpr.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (stat (stat (stat (stat (type bool) (ident a) " +
            "= (assign_rhs (expr (bool_liter true)))) ; (stat (type bool) (ident b) = " +
            "(assign_rhs (expr (bool_liter false))))) ; (stat println (expr (expr (ident a))" +
            " && (expr (ident b))))) ; (stat println (expr (expr (ident a)) && (expr " +
            "(bool_liter true))))) ; (stat println (expr (expr (ident b)) && (expr " +
            "(bool_liter false))))) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void BoolExpressionTest() throws IOException {
    String file = "wacc_examples/valid/expressions/boolExpr1.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (stat (type bool) (ident b) = (assign_rhs (expr ! (expr " +
            "( (expr (expr ( (expr (expr (bool_liter true)) && (expr (bool_liter false))) )) || " +
            "(expr ( (expr (expr (bool_liter true)) && (expr (bool_liter false))) ))) ))))) ; " +
            "(stat if (expr (expr (ident b)) == (expr (bool_liter true))) then (stat println " +
            "(expr \"Correct\")) else (stat println (expr \"Wrong\")) fi)) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void CharExpressionTest() throws IOException {
    String file = "wacc_examples/valid/expressions/charComparisonExpr.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (stat (stat (stat (stat (stat (stat (stat (type char) " +
            "(ident c1) = (assign_rhs (expr 'a'))) ; (stat (type char) (ident c2) = (assign_rhs " +
            "(expr 'z')))) ; (stat println (expr (expr (ident c1)) == (expr (ident c2))))) ; " +
            "(stat println (expr (expr (ident c1)) != (expr (ident c2))))) ; (stat println (expr " +
            "(expr (ident c1)) < (expr (ident c2))))) ; (stat println (expr (expr (ident c1)) <= " +
            "(expr (ident c2))))) ; (stat println (expr (expr (ident c1)) > (expr (ident c2))))) ; " +
            "(stat println (expr (expr (ident c1)) >= (expr (ident c2))))) end <EOF>)";
    assertEquals(expected, result);
  }


  @Test
  public void DivExpressionTest() throws IOException {
    String file = "wacc_examples/valid/expressions/divExpr.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (stat (stat (type int) (ident x) = (assign_rhs (expr " +
            "(int_liter 5)))) ; (stat (type int) (ident y) = (assign_rhs (expr (int_liter 3))))) ; " +
            "(stat println (expr (expr (ident x)) / (expr (ident y))))) end <EOF>)";
    assertEquals(expected, result);
  }
  @Test
  public void LongExpressionTest() throws IOException {
    String file = "wacc_examples/valid/expressions/longExpr.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (stat (type int) (ident x) = (assign_rhs (expr " +
            "(expr (int_liter 1)) + (expr ( (expr (expr (int_liter 2)) + (expr ( (expr " +
            "(expr (int_liter 3)) + (expr ( (expr (expr (int_liter 4)) + (expr ( (expr " +
            "(expr (int_liter 5)) + (expr ( (expr (expr (int_liter 6)) + (expr ( (expr " +
            "(expr (int_liter 7)) + (expr ( (expr (expr (int_liter 8)) + (expr ( (expr " +
            "(expr (int_liter 9)) + (expr ( (expr (expr (int_liter 1 0)) + (expr ( (expr " +
            "(expr (int_liter 1 1)) + (expr ( (expr (expr (int_liter 1 2)) + (expr ( (expr " +
            "(expr (int_liter 1 3)) + (expr ( (expr (expr (int_liter 1 4)) + (expr ( (expr " +
            "(expr (int_liter 1 5)) + (expr ( (expr (expr (int_liter 1 6)) + (expr (int_liter 1 7)))" +
            " ))) ))) ))) ))) ))) ))) ))) ))) ))) ))) ))) ))) ))) ))) ))))) ; (stat exit (expr " +
            "(ident x)))) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void MinusExpressionTest() throws IOException {
    String file = "wacc_examples/valid/expressions/minusExpr.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (stat (stat (type int) (ident x) = (assign_rhs " +
            "(expr (int_liter 1 5)))) ; (stat (type int) (ident y) = (assign_rhs (expr " +
            "(int_liter 2 0))))) ; (stat println (expr (expr (ident y)) - (expr (ident x))))) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void NegDividendModTest() throws IOException {
    String file = "wacc_examples/valid/expressions/negDividendMod.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (stat (stat (type int) (ident x) = (assign_rhs " +
            "(expr (int_liter - 5)))) ; (stat (type int) (ident y) = (assign_rhs (expr " +
            "(int_liter 3))))) ; (stat println (expr (expr (ident x)) % (expr (ident y))))) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void StringEqualsExpressionTest() throws IOException {
    String file = "wacc_examples/valid/expressions/stringEqualsExpr.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (stat (stat (stat (stat (stat (stat (type string) " +
            "(ident s1) = (assign_rhs (expr \"Hello\"))) ; (stat (type string) (ident s2) = " +
            "(assign_rhs (expr \"foo\")))) ; (stat (type string) (ident s3) = (assign_rhs (expr " +
            "\"bar\")))) ; (stat (type bool) (ident b) = (assign_rhs (expr (expr (ident s1)) == " +
            "(expr (ident s1)))))) ; (stat println (expr (ident b)))) ; (stat println (expr (expr " +
            "(ident s1)) == (expr (ident s2))))) ; (stat println (expr (expr (ident s2)) == (expr " +
            "(ident s3))))) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void NestedFuncionTest() throws IOException {
    String file = "wacc_examples/valid/function/nested_functions/functionConditionalReturn.wacc";
    String result = processTree(file);
    String expected = "(prog begin (func (type bool) (ident f) ( ) is (stat if (expr (bool_liter true)) " +
            "then (stat return (expr (bool_liter true))) else (stat return (expr (bool_liter false))) fi) end) " +
            "(stat (stat (type bool) (ident x) = (assign_rhs call (ident f) ( ))) ; (stat println (expr " +
            "(ident x)))) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void IfTest() throws IOException {
    String file = "wacc_examples/valid/if/if1.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (stat (type int) (ident a) = (assign_rhs (expr " +
            "(int_liter 1 3)))) ; (stat if (expr (expr (ident a)) == (expr (int_liter 1 3))) " +
            "then (stat println (expr \"correct\")) else (stat println (expr \"incorrect\")) fi)) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void PrintTest() throws IOException {
    String file = "wacc_examples/valid/IO/print/print-backspace.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat print (expr \"Hello\\b World!\\n\")) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void ReadTest() throws IOException {
    String file = "wacc_examples/valid/IO/read/echoInt.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (stat (stat (stat (type int) (ident x) = (assign_rhs " +
            "(expr (int_liter 1)))) ; (stat println (expr \"enter an integer to echo\"))) ; " +
            "(stat read (assign_lhs (ident x)))) ; (stat println (expr (ident x)))) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void PairsTest() throws IOException {
    String file = "wacc_examples/valid/pairs/nestedPair.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (stat (type pair ( (pair_elem_type (type int)) , " +
            "(pair_elem_type (type int)) )) (ident p) = (assign_rhs newpair ( (expr (int_liter 2)) " +
            ", (expr (int_liter 3)) ))) ; (stat (type pair ( (pair_elem_type (type int)) , " +
            "(pair_elem_type pair) )) (ident q) = (assign_rhs newpair ( (expr (int_liter 1)) , " +
            "(expr (ident p)) )))) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void SequenceTest() throws IOException {
    String file = "wacc_examples/valid/sequence/basicSeq.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (stat skip) ; (stat skip)) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void VariableTest() throws IOException {
    String file = "wacc_examples/valid/variables/boolDeclaration.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (type bool) (ident b) = (assign_rhs (expr " +
            "(bool_liter false)))) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void WhileTest() throws IOException {
    String file = "wacc_examples/valid/while/max.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (stat (stat (stat (stat (stat (type int) (ident i) " +
            "= (assign_rhs (expr (int_liter 0)))) ; (stat (type int) (ident x) = (assign_rhs " +
            "(expr (int_liter 1 0))))) ; (stat (type int) (ident y) = (assign_rhs (expr " +
            "(int_liter 1 7))))) ; (stat while (expr ( (expr (expr (expr (ident y)) > (expr " +
            "(int_liter 0))) || (expr (expr (ident x)) > (expr (int_liter 0)))) )) do (stat " +
            "(stat (stat (assign_lhs (ident x)) = (assign_rhs (expr (expr (ident x)) - (expr " +
            "(int_liter 1))))) ; (stat (assign_lhs (ident y)) = (assign_rhs (expr (expr (ident y))" +
            " - (expr (int_liter 1)))))) ; (stat (assign_lhs (ident i)) = (assign_rhs (expr " +
            "(expr (ident i)) + (expr (int_liter 1)))))) done)) ; (stat print (expr \"max value = \"))) " +
            "; (stat println (expr (ident i)))) end <EOF>)";
    assertEquals(expected, result);
  }

  @Test
  public void ArrayTest() throws IOException {
    String file = "wacc_examples/valid/array/array.wacc";
    String result = processTree(file);
    String expected = "(prog begin (stat (stat (stat (stat (stat (stat (stat (stat (type (type int) " +
            "[ ]) (ident a) = (assign_rhs (array_liter [ (expr (int_liter 0)) , (expr (int_liter 0)) ," +
            " (expr (int_liter 0)) , (expr (int_liter 0)) , (expr (int_liter 0)) , (expr (int_liter 0)) ," +
            " (expr (int_liter 0)) , (expr (int_liter 0)) , (expr (int_liter 0)) , (expr (int_liter 0)) ]))) ; " +
            "(stat (type int) (ident i) = (assign_rhs (expr (int_liter 0))))) ; (stat while (expr (expr (ident i)) < " +
            "(expr (int_liter 1 0))) do (stat (stat (assign_lhs (array_elem (ident a) [ (expr (ident i)) ])) = " +
            "(assign_rhs (expr (ident i)))) ; (stat (assign_lhs (ident i)) = (assign_rhs (expr (expr (ident i)) + " +
            "(expr (int_liter 1)))))) done)) ; (stat print (expr (ident a)))) ; (stat print (expr \" = {\"))) ; " +
            "(stat (assign_lhs (ident i)) = (assign_rhs (expr (int_liter 0))))) ; (stat while (expr (expr (ident i))" +
            " < (expr (int_liter 1 0))) do (stat (stat (stat print (expr (array_elem (ident a) [ (expr (ident i)) ])))" +
            " ; (stat if (expr (expr (ident i)) < (expr (int_liter 9))) then (stat print (expr \", \")) else " +
            "(stat skip) fi)) ; (stat (assign_lhs (ident i)) = (assign_rhs (expr (expr (ident i)) + " +
            "(expr (int_liter 1)))))) done)) ; (stat println (expr \"}\"))) end <EOF>)";
    assertEquals(expected, result);
  }

}