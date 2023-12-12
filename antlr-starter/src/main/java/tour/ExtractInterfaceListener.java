package tour;

import org.antlr.v4.runtime.TokenStream;
import tour.JavaParser.ClassDeclarationContext;
import tour.JavaParser.ImportDeclarationContext;
import tour.JavaParser.MethodDeclarationContext;

/**
 * @author tomyli
 * @date 2023/12/12 enjoy
 */
public class ExtractInterfaceListener extends JavaBaseListener {

    JavaParser javaParser;

    public ExtractInterfaceListener(JavaParser javaParser) {
        this.javaParser = javaParser;
    }

    @Override
    public void enterClassDeclaration(ClassDeclarationContext ctx) {
        System.out.println("interface I" + ctx.Identifier() + " {");
    }

    @Override
    public void exitClassDeclaration(ClassDeclarationContext ctx) {
        System.out.println("}");
    }

    @Override
    public void enterMethodDeclaration(MethodDeclarationContext ctx) {
        TokenStream tokens = javaParser.getTokenStream();
        String type = "void";
        if (ctx.type() != null) {
            type = tokens.getText(ctx.type());
        }
        String args = tokens.getText(ctx.formalParameters());
        System.out.println("\t" + type + " " + ctx.Identifier() + args + ";");
    }

    /**
     * handle import declaration
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterImportDeclaration(ImportDeclarationContext ctx) {
        System.out.println(javaParser.getTokenStream().getText(ctx));
    }
}
