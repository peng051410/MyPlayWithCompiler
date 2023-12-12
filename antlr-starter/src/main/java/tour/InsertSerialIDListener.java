package tour;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import tour.JavaParser.ClassBodyContext;

/**
 * @author tomyli
 * @date 2023/12/12 enjoy
 */
public class InsertSerialIDListener extends JavaBaseListener {

    TokenStreamRewriter rewriter;

    public InsertSerialIDListener(CommonTokenStream tokens) {
        rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public void enterClassBody(ClassBodyContext ctx) {
        String field = "\n\tpublic static final long serialVersionUID = 1L;";
        rewriter.insertAfter(ctx.start, field);
    }
}
