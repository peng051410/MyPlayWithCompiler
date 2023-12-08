import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author tomyli
 * @date 2023/12/8 enjoy
 */
public class Test {

    public static void main(String[] args) {

        // create a CharStream that reads from standard input
        CodePointCharStream input = CharStreams.fromString("{1,{2,3");

        // create a lexer that feeds off of input CharStream
        ArrayInitLexer lexer = new ArrayInitLexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        ArrayInitParser parser = new ArrayInitParser(tokens);

        // begin parsing at init rule
        ParseTree tree = parser.init();
        // print LISP-style tree
        System.out.println(tree.toStringTree(parser));
    }

}
