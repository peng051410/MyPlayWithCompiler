import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

/**
 * @author tomyli
 * @date 2024/1/29 enjoy
 */
public class EnumTest {

    @Test
    public void testBeforeJava5() {

        EnumParser.java5 = false; // assume non-Java5 mode by default
        CharStream input = CharStreams.fromString("enum = 0;");
        EnumLexer lexer = new EnumLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EnumParser parser = new EnumParser(tokens);
        parser.prog();
    }
    @Test
    public void testBeforeJava5_2() {

        EnumParser.java5 = false; // assume non-Java5 mode by default
        CharStream input = CharStreams.fromString("enum Temp { HOT, COLD }");
        EnumLexer lexer = new EnumLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EnumParser parser = new EnumParser(tokens);
        parser.prog();
    }
    @Test
    public void testJava5() {

        EnumParser.java5 = true; // assume non-Java5 mode by default
        CharStream input = CharStreams.fromString("enum = 0;");
        EnumLexer lexer = new EnumLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EnumParser parser = new EnumParser(tokens);
        parser.prog();
    }
    @Test
    public void testJava5_2() {

        EnumParser.java5 = true; // assume non-Java5 mode by default
        CharStream input = CharStreams.fromString("enum Temp { HOT, COLD }");
        EnumLexer lexer = new EnumLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EnumParser parser = new EnumParser(tokens);
        parser.prog();
    }

    @Test
    public void testBeforeJava5WithLexer() {

        Enum2Lexer.java5 = false; // assume non-Java5 mode by default
        CharStream input = CharStreams.fromString("enum = 0;");
        Enum2Lexer lexer = new Enum2Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Enum2Parser parser = new Enum2Parser(tokens);
        parser.prog();
    }

    @Test
    public void testBeforeJava5WithLexer_2() {

        Enum2Lexer.java5 = false; // assume non-Java5 mode by default
        CharStream input = CharStreams.fromString("enum Temp { HOT, COLD }");
        Enum2Lexer lexer = new Enum2Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Enum2Parser parser = new Enum2Parser(tokens);
        parser.prog();
    }
    @Test
    public void testJava5WithLexer() {

        Enum2Lexer.java5 = true; // assume non-Java5 mode by default
        CharStream input = CharStreams.fromString("enum = 0;");
        Enum2Lexer lexer = new Enum2Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Enum2Parser parser = new Enum2Parser(tokens);
        parser.prog();
    }
    @Test
    public void testJava5WithLexer_2() {

        Enum2Lexer.java5 = true; // assume non-Java5 mode by default
        CharStream input = CharStreams.fromString("enum Temp { HOT, COLD }");
        Enum2Lexer lexer = new Enum2Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Enum2Parser parser = new Enum2Parser(tokens);
        parser.prog();
    }
}
