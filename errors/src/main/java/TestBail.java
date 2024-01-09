/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
 ***/

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.LexerNoViableAltException;

public class TestBail {

    public static class BailSimpleLexer extends SimpleLexer {

        public BailSimpleLexer(CharStream input) {
            super(input);
        }

        public void recover(LexerNoViableAltException e) {
            throw new RuntimeException(e); // Bail out
        }
    }

    public static void main(String[] args) {

//        CodePointCharStream input = CharStreams.fromString("# class T { int i; }");
        CodePointCharStream input = CharStreams.fromString("class  { }");
        BailSimpleLexer lexer = new BailSimpleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleParser parser = new SimpleParser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());
        parser.prog();
    }
}
