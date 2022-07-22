import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class SemanticTest {
  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  public ParseTree processTree(String file) throws IOException {
    InputStream stream = new FileInputStream(file);
    CharStream input = CharStreams.fromStream(stream);
    WaccLexer lexer = new WaccLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    WaccParser parser = new WaccParser(tokens);
    return parser.prog();
  }

  @Test
  public void TestExit1() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/exit/badCharExit.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestExit2() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/exit/exitNonInt.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestExit3() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/exit/globalReturn.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestExpression1() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/expressions/exprTypeErr.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestExpression2() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/expressions/lessPairExpr.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestExpression3() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/expressions/stringElemErr.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestFunction1() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/function/functionAssign.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestFunction2() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/function/functionBadArgUse.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestFunction3() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/function/functionBadCall.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestIf() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/if/ifIntCondition.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestIO() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/IO/readTypeErr.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestMultiple1() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/multiple/funcMess.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestMultiple2() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/multiple/ifAndWhileErrs.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestMultiple3() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/multiple/messyExpr.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestPairs1() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/pairs/freeNonPair.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestPairs2() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/pairs/fstNull.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestPairs3() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/pairs/sndNull.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestPrint() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/print/printTypeErr01.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestRead() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/read/readTypeErr01.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestScope() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/scope/badScopeRedefine.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestVariables1() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/variables/basicTypeErr01.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestVariables2() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/variables/basicTypeErr02.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestVariables3() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/variables/basicTypeErr03.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestWhile1() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/while/falsErr.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestWhile2() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/while/truErr.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }

  @Test
  public void TestWhile3() throws IOException {

    String file = "wacc_examples/invalid/semanticErr/while/whileIntCondition.wacc";
    ParseTree tree = processTree(file);

    exit.expectSystemExitWithStatus(200);
    WaccVisitor visitor = new WaccVisitor();
    visitor.visit(tree);

  }
}