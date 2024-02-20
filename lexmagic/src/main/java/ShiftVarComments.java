/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
 ***/

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ShiftVarComments {

    public static class CommentShifter extends CymbolBaseListener {

        BufferedTokenStream tokens;
        TokenStreamRewriter rewriter;

        /**
         * Create TokenStreamRewriter attached to token stream sitting between the Cymbol lexer and parser.
         */
        public CommentShifter(BufferedTokenStream tokens) {
            this.tokens = tokens;
            rewriter = new TokenStreamRewriter(tokens);
        }

        @Override
        public void exitVarDecl(CymbolParser.VarDeclContext ctx) {
            Token semi = ctx.getStop();
            int i = semi.getTokenIndex();
            // look hidden token
            List<Token> cmtChannel = tokens.getHiddenTokensToRight(i, CymbolLexer.COMMENTS);
            if (cmtChannel != null) {
                Token cmt = cmtChannel.get(0);
                if (cmt != null) {
                    String txt = cmt.getText().substring(2);
                    String newCmt = "/* " + txt.trim() + " */\n";
                    rewriter.insertBefore(ctx.start, newCmt);
                    // erase original
                    rewriter.replace(cmt, "\n");
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0) {
            inputFile = args[0];
        }
        InputStream is = System.in;
        if (inputFile != null) {
            is = new FileInputStream(inputFile);
        }
        CharStream input = CharStreams.fromString("int n = 0; // define a counter\n"
                + "int i = 9;\n");
        CymbolLexer lexer = new CymbolLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CymbolParser parser = new CymbolParser(tokens);
        RuleContext tree = parser.file();

        ParseTreeWalker walker = new ParseTreeWalker();
        CommentShifter shifter = new CommentShifter(tokens);
        walker.walk(shifter, tree);
        System.out.print(shifter.rewriter.getText());
    }

}
