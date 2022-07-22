import java.io.FileInputStream;

import java.nio.file.Path;
import java.nio.file.Paths;

import codeGenerator.CodeGenerator;
import node.ProgNode;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {

  public static void main(String[] args) throws Exception {

    //  create a CharStream that reads from standard input.
    CharStream input = CharStreams.fromStream(new FileInputStream(args[0]));

    //  create a lexer that feeds off of input CharStream
    WaccLexer lexer = new WaccLexer(input);

    //  create a buffer of tokens pulled from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    //  create a parser that feeds off the tokens buffer
    WaccParser parser = new WaccParser(tokens);

    //  create and add new listener to report syntax error
    WaccListener listener = new WaccListener();
    parser.removeErrorListeners();
    parser.addErrorListener(listener);

    //  begin parsing at program rule
    ParseTree tree = parser.prog();
    listener.printSyntaxErrors();

    //  create new visitor to report semantic error
    WaccVisitor visitor = new WaccVisitor();
    ProgNode progNode = (ProgNode) visitor.visit(tree);

    //  translate assembly code
    CodeGenerator codeGenerator = new CodeGenerator(visitor.getTopTable());
    progNode.translate();

    //  create and write to assembly file
    Path p = Paths.get(args[0]);
    String fileName = p.getFileName().toString();
    String file = fileName.substring(0, fileName.lastIndexOf("."));
    codeGenerator.createAndWriteFile(file);
  }
}
