package cn.imcompany;

import java.io.IOException;

import cn.imcompany.api.SimpleLexer;
import cn.imcompany.api.SimpleParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

/**
 * @author tomyli
 * @date 2024/2/22 enjoy
 */
public class SimpleTest {

    @Test
    public void testSimple() throws IOException {
        CharStream input = CharStreams.fromFileName(
                "/Users/tomyli/github/MyPlayWithCompiler/api/src/main/Simple-input");
        SimpleLexer lexer = new SimpleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleParser parser = new SimpleParser(tokens);
        ParseTree t = parser.s();
        System.out.println(t.toStringTree(parser));

    }
}
