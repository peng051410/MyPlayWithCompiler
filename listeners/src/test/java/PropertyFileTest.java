import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author tomyli
 * @date 2023/12/15 enjoy
 */
public class PropertyFileTest {

    @Test
    public void testFilePrinter() {
        
        CodePointCharStream input = CharStreams.fromString("a=1;b=2;");
        PropertyFileLexer lexer = new PropertyFileLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PropertyFilePrinter parser = new PropertyFilePrinter(tokens);
        parser.file(); // launch our special version of the parser
    }

    @Test
    public void testFileVisitor() {

        CodePointCharStream input = CharStreams.fromString("a=\"1\"\n");
        PropertyFileLexer lexer = new PropertyFileLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PropertyFileParser parser = new PropertyFileParser(tokens);
        ParseTree tree = parser.file();

        // create a visitor then feed it to the walker
        PropertyFileWithVisitor loader = new PropertyFileWithVisitor();
        loader.visit(tree); // walk parse tree
        System.out.println(loader.props); // print results

    }

    @Test
    public void testPropertyFileLoader() {

        CodePointCharStream input = CharStreams.fromString("a=\"1\"\n");
        PropertyFileLexer lexer = new PropertyFileLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PropertyFileParser parser = new PropertyFileParser(tokens);
        ParseTree tree = parser.file();

        // create a standard ANTLR parse tree walker
        ParseTreeWalker walker = new ParseTreeWalker();
        // create listener then feed to walker
        PropertyFileLoader loader = new PropertyFileLoader();
        walker.walk(loader, tree);        // walk parse tree
        System.out.println(loader.props); // print results

        Assertions.assertEquals("{a=\"1\"}", loader.props.toString(), "a=\"1\"");
    }

}
