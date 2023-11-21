package cn.imcompany.compiler;

import cn.imcompany.compiler.SimpleLexer.SimpleTokenReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author tomyli
 * @date 2023/11/21 enjoy
 */
class SimpleLexerTest {

    @Test
    public void testNormalParser() {

        SimpleLexer lexer = new SimpleLexer();
        String script = "int age = 45;";
        SimpleTokenReader tokenReader = lexer.tokenize(script);
        String result = lexer.print(tokenReader);
        System.out.println(result);

        String except = "text\ttype\n"
                + "int\t\tInt\n"
                + "age\t\tIdentifier\n"
                + "=\t\tAssignment\n"
                + "45\t\tIntLiteral\n"
                + ";\t\tSemiColon\n";

        Assertions.assertEquals(except, result);
    }

    @Test
    public void testIntAParser() {

        SimpleLexer lexer = new SimpleLexer();
        String script = "inta age = 45;";
        System.out.println("\nparse :" + script);
        SimpleTokenReader tokenReader = lexer.tokenize(script);
        String result = lexer.print(tokenReader);
        System.out.println(result);

        String except = "text\ttype\n"
                + "inta\t\tIdentifier\n"
                + "age\t\tIdentifier\n"
                + "=\t\tAssignment\n"
                + "45\t\tIntLiteral\n"
                + ";\t\tSemiColon\n";

        Assertions.assertEquals(except, result);
    }

    @Test
    public void testInParser() {

        SimpleLexer lexer = new SimpleLexer();
        String script = "in age = 45;";
        System.out.println("\nparse :" + script);
        SimpleTokenReader tokenReader = lexer.tokenize(script);
        String result = lexer.print(tokenReader);
        System.out.println(result);

        String except = "text\ttype\n"
                + "in\t\tIdentifier\n"
                + "age\t\tIdentifier\n"
                + "=\t\tAssignment\n"
                + "45\t\tIntLiteral\n"
                + ";\t\tSemiColon\n";

        Assertions.assertEquals(except, result);
    }
    @Test
    public void testGreatThanEqualsParser() {

        SimpleLexer lexer = new SimpleLexer();
        String script = "age >= 45;";
        System.out.println("\nparse :" + script);
        SimpleTokenReader tokenReader = lexer.tokenize(script);
        String result = lexer.print(tokenReader);
        System.out.println(result);

        String except = "text\ttype\n"
                + "age\t\tIdentifier\n"
                + ">=\t\tGE\n"
                + "45\t\tIntLiteral\n"
                + ";\t\tSemiColon\n";

        Assertions.assertEquals(except, result);
    }

    @Test
    public void testGreatThanParser() {

        SimpleLexer lexer = new SimpleLexer();
        String script = "age > 45;";
        System.out.println("\nparse :" + script);
        SimpleTokenReader tokenReader = lexer.tokenize(script);
        String result = lexer.print(tokenReader);
        System.out.println(result);

        String except = "text\ttype\n"
                + "age\t\tIdentifier\n"
                + ">\t\tGT\n"
                + "45\t\tIntLiteral\n"
                + ";\t\tSemiColon\n";

        Assertions.assertEquals(except, result);
    }
}
