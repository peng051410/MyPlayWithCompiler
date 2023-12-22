/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
 ***/

import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class RefPhase extends CymbolBaseListener {

    ParseTreeProperty<Scope> scopes;
    GlobalScope globals;
    Scope currentScope; // resolve symbols starting in this scope

    public RefPhase(GlobalScope globals, ParseTreeProperty<Scope> scopes) {
        this.scopes = scopes;
        this.globals = globals;
    }

    @Override
    public void enterFile(CymbolParser.FileContext ctx) {
        System.out.println("enterFile ref:" + ctx.getText());
        currentScope = globals;
    }

    @Override
    public void enterFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
        System.out.println("enterFunctionDecl ref:" + ctx.getText());
        currentScope = scopes.get(ctx);
    }

    @Override
    public void exitFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
        System.out.println("exitFunctionDecl ref:" + ctx.getText());
        currentScope = currentScope.getEnclosingScope();
    }

    @Override
    public void enterBlock(CymbolParser.BlockContext ctx) {
        System.out.println("enterBlock ref:" + ctx.getText());
        currentScope = scopes.get(ctx);
    }

    @Override
    public void exitBlock(CymbolParser.BlockContext ctx) {
        System.out.println("exitBlock ref:" + ctx.getText());
        currentScope = currentScope.getEnclosingScope();
    }

    @Override
    public void exitVar(CymbolParser.VarContext ctx) {
        String name = ctx.ID().getSymbol().getText();
        System.out.println("exitVar ref:" + name);
        Symbol var = currentScope.resolve(name);
        if (var == null) {
            CheckSymbols.error(ctx.ID().getSymbol(), "no such variable: " + name);
        }
        if (var instanceof FunctionSymbol) {
            CheckSymbols.error(ctx.ID().getSymbol(), name + " is not a variable");
        }
    }

    @Override
    public void exitCall(CymbolParser.CallContext ctx) {
        // can only handle f(...) not expr(...)
        String funcName = ctx.ID().getText();
        System.out.println("exitCall ref:" + funcName);
        Symbol meth = currentScope.resolve(funcName);
        if (meth == null) {
            CheckSymbols.error(ctx.ID().getSymbol(), "no such function: " + funcName);
        }
        if (meth instanceof VariableSymbol) {
            CheckSymbols.error(ctx.ID().getSymbol(), funcName + " is not a function");
        }
    }
}
