package tour;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author tomyli
 * @date 2023/12/11 enjoy
 */
public class Calc {

    public static void main(String[] args) throws IOException {

        String inputFile = null;

        if (args.length > 0) {
            inputFile = args[0];
        }

        InputStream is = System.in;
        if (inputFile != null) {
            is = Files.newInputStream(Paths.get(inputFile));
        }

        CharStream charStream = CharStreams.fromStream(is);
        LabeledExprLexer lexer = new LabeledExprLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);
        ParseTree tree = parser.prog(); // parse, starting from prog

        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);

    }

}
