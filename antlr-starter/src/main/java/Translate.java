import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * @author tomyli
 * @date 2023/12/11 enjoy
 */
public class Translate {

    public static void main(String[] args) {

        CodePointCharStream input = CharStreams.fromString("{1,2,4}");
        ArrayInitLexer lexer = new ArrayInitLexer(input);
        CommonTokenStream token = new CommonTokenStream(lexer);
        ArrayInitParser parser = new ArrayInitParser(token);
        ParseTree tree = parser.init();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new ShortToUnicodeString(), tree);
        System.out.println();
    }

}
