/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
 ***/

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class DefPhase extends CymbolBaseListener {
    ParseTreeProperty<Scope> scopes = new ParseTreeProperty<>();
    GlobalScope globals;
    Scope currentScope; // define symbols in this scope

    @Override
    public void enterFile(CymbolParser.FileContext ctx) {
        System.out.println("enterFile");
        globals = new GlobalScope(null);
        currentScope = globals;
    }

    @Override
    public void exitFile(CymbolParser.FileContext ctx) {
        System.out.println("exitFile");
        System.out.println(globals);
    }

    @Override
    public void enterFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
        System.out.println("enterFunctionDecl");
        String name = ctx.ID().getText();
        int typeTokenType = ctx.type().start.getType();
        Symbol.Type type = CheckSymbols.getType(typeTokenType);

        // push new scope by making new one that points to enclosing scope
        FunctionSymbol function = new FunctionSymbol(name, type, currentScope);
        currentScope.define(function); // Define function in current scope
        saveScope(ctx, function);      // Push: set function's parent to current
        currentScope = function;       // Current scope is now function scope
    }

    void saveScope(ParserRuleContext ctx, Scope s) { scopes.put(ctx, s); }

    @Override
    public void exitFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
        System.out.println("exitFunctionDecl");
        System.out.println(currentScope);
        currentScope = currentScope.getEnclosingScope(); // pop scope
    }

    @Override
    public void enterBlock(CymbolParser.BlockContext ctx) {
        System.out.println("enterBlock");
        // push new local scope
        currentScope = new LocalScope(currentScope);
        saveScope(ctx, currentScope);
    }

    @Override
    public void exitBlock(CymbolParser.BlockContext ctx) {
        System.out.println("exitBlock");
        System.out.println(currentScope);
        currentScope = currentScope.getEnclosingScope(); // pop scope
    }

    @Override
    public void exitFormalParameter(CymbolParser.FormalParameterContext ctx) {
        System.out.println("exitFormalParameter");
        defineVar(ctx.type(), ctx.ID().getSymbol());
    }

    @Override
    public void exitVarDecl(CymbolParser.VarDeclContext ctx) {
        System.out.println("exitVarDecl");
        defineVar(ctx.type(), ctx.ID().getSymbol());
    }

    void defineVar(CymbolParser.TypeContext typeCtx, Token nameToken) {
        int typeTokenType = typeCtx.start.getType();
        Symbol.Type type = CheckSymbols.getType(typeTokenType);
        VariableSymbol var = new VariableSymbol(nameToken.getText(), type);
        currentScope.define(var); // Define symbol in current scope
    }
}
