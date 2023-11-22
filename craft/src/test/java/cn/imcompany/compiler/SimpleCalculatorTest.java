package cn.imcompany.compiler;

import cn.imcompany.compiler.SimpleCalculator.SimpleASTNode;
import org.junit.jupiter.api.Test;

/**
 * @author tomyli
 * @date 2023/11/22 enjoy
 */
class SimpleCalculatorTest {

    @Test
    public void testVariableParse() {

        SimpleCalculator calculator = new SimpleCalculator();

        //测试变量声明语句的解析
        String script = "int a = b+3;";
        System.out.println("解析变量声明语句: " + script);
        SimpleLexer lexer = new SimpleLexer();
        TokenReader tokens = lexer.tokenize(script);
        try {
            SimpleASTNode node = calculator.intDeclare(tokens);
            calculator.dumpAST(node, "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testExpression() {
        SimpleCalculator calculator = new SimpleCalculator();
        //测试表达式
        String script = "2+3*5";
        System.out.println("\n计算: " + script + "，看上去一切正常。");
        calculator.evaluate(script);
    }

    @Test
    public void testSyntaxError() {
        SimpleCalculator calculator = new SimpleCalculator();
        //测试语法错误
        String script = "2+";
        System.out.println("\n: " + script + "，应该有语法错误。");
        calculator.evaluate(script);
    }

    @Test
    public void testCompatibleError() {
        SimpleCalculator calculator = new SimpleCalculator();
        String script = "2+3+4";
        System.out.println("\n计算: " + script + "，结合性出现错误。");
        calculator.evaluate(script);
    }

}
