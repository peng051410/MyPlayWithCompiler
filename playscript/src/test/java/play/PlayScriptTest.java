package play;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author tomyli
 * @date 2023/12/4 enjoy
 */
class PlayScriptTest {

    @Test
    public void testForScope() {
        
        String script = "int age = 44; for(int i = 0;i<10;i++) { age = age + 2;} int i = 8;";

        PlayScriptCompiler compiler = new PlayScriptCompiler();
        AnnotatedTree at = compiler.compile(script, true, true);

        if (!at.hasCompilationError()) {
            Object result = compiler.Execute(at);
            System.out.println(result);

            assertEquals(8, result);
        }
    }

    @Test
    public void testFunction() {

        String script = "int b= 10; int myfunc(int a) {return a+b+3;} myfunc(2);";

        PlayScriptCompiler compiler = new PlayScriptCompiler();
        AnnotatedTree at = compiler.compile(script, true, true);

        if (!at.hasCompilationError()) {
            Object result = compiler.Execute(at);
            System.out.println(result);

            assertEquals(15, result);
        }
    }

    @Test
    public void testClass() {

        String script = "class myclass{int a=2; int b; myclass(){ b = 3;} }  myclass c = myclass(); c.b;";

        PlayScriptCompiler compiler = new PlayScriptCompiler();
        AnnotatedTree at = compiler.compile(script, true, true);

        if (!at.hasCompilationError()) {
            Object result = compiler.Execute(at);
            System.out.println(result);

            assertEquals("LValue of b : 3", result.toString());
        }
    }


}
