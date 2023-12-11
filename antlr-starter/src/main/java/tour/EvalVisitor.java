package tour;

import java.util.HashMap;
import java.util.Map;

import tour.LabeledExprParser.AddSubContext;
import tour.LabeledExprParser.AssignContext;
import tour.LabeledExprParser.ClearContext;
import tour.LabeledExprParser.IdContext;
import tour.LabeledExprParser.IntContext;
import tour.LabeledExprParser.MulDivContext;
import tour.LabeledExprParser.ParensContext;
import tour.LabeledExprParser.PrintExprContext;

/**
 * @author tomyli
 * @date 2023/12/11 enjoy
 */
public class EvalVisitor extends LabeledExprBaseVisitor<Integer> {

    Map<String, Integer> memory = new HashMap<>();

    /**
     * ID '=' expr NEWLINE
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Integer visitAssign(AssignContext ctx) {
        String id = ctx.ID().getText();
        Integer value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    /**
     * EXPR NEWLINE
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Integer visitPrintExpr(PrintExprContext ctx) {
        Integer value = visit(ctx.expr());
        System.out.println(value);
        return 0;
    }

    /**
     * INT
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Integer visitInt(IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    /**
     * ID
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Integer visitId(IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id);
        }
        return 0;
    }

    /**
     * expr op=('*'|'/') expr
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Integer visitMulDiv(MulDivContext ctx) {
        Integer left = visit(ctx.expr(0));
        Integer right = visit(ctx.expr(1));
        if (ctx.op.getType() == LabeledExprParser.MUL) {
            return left * right;
        }
        return left / right;
    }

    /**
     * expr op=('+'|'-') expr
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Integer visitAddSub(AddSubContext ctx) {
        Integer left = visit(ctx.expr(0));
        Integer right = visit(ctx.expr(1));
        if (ctx.op.getType() == LabeledExprParser.ADD) {
            return left + right;
        }
        return left - right;
    }

    /**
     * '(' expr ')'
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Integer visitParens(ParensContext ctx) {
        return visit(ctx.expr()); // return child expr's value
    }

    /**
     * 'clear'
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Integer visitClear(ClearContext ctx) {
        memory.clear();
        return 0;
    }
}
