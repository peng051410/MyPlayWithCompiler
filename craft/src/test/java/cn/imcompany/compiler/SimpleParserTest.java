package cn.imcompany.compiler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by tomyli on 2023/11/25.
 */
class SimpleParserTest {

    @Test
    public void testNormalParser() {

        SimpleParser parser = new SimpleParser();
        String script;
        ASTNode tree;

        try {
            script = "int age = 45+2; age= 20; age+10*2;";
            System.out.println("解析：" + script);

            tree = parser.parse(script);

            StringBuilder sb = new StringBuilder();
            String result = parser.dumpASTV2(tree, "", sb);

            String expect = "Programm pwc\n" +
                    "\tIntDeclaration age\n" +
                    "\t\tAdditive +\n" +
                    "\t\t\tIntLiteral 45\n" +
                    "\t\t\tIntLiteral 2\n" +
                    "\tAssignmentStmt age\n" +
                    "\t\tIntLiteral 20\n" +
                    "\tAdditive +\n" +
                    "\t\tIdentifier age\n" +
                    "\t\tMultiplicative *\n" +
                    "\t\t\tIntLiteral 10\n" +
                    "\t\t\tIntLiteral 2\n";

            assertEquals(expect, result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testErrorParser() {

        SimpleParser parser = new SimpleParser();
        String script;

        //测试异常语法
        script = "2+3+;";
        System.out.println("解析：" + script);

        Exception exception = assertThrows(Exception.class, () -> {
            ASTNode tree = parser.parse(script);
            parser.dumpAST(tree, "");
        });

        String expectedMessage = "invalid additive expression, expecting the right part.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testErrorParser2() {

        SimpleParser parser = new SimpleParser();
        String script;

        //测试异常语法
        script = "2+3*;";
        System.out.println("解析：" + script);

        Exception exception = assertThrows(Exception.class, () -> {
            ASTNode tree = parser.parse(script);
            parser.dumpAST(tree, "");
        });

        String expectedMessage = "invalid multiplicative expression, expecting the right part.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}