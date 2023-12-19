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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class LoadCSV {

    public static class Loader extends CSVBaseListener {

        public static final String EMPTY = "";
        /**
         * Load a list of row maps that map field name to value
         */
        List<Map<String, String>> rows = new ArrayList<>();
        /**
         * List of column names
         */
        List<String> header;
        /**
         * Build up a list of fields in current row
         */
        List<String> currentRowFieldValues;

        @Override
        public void exitHdr(CSVParser.HdrContext ctx) {
            header = new ArrayList<>();
            header.addAll(currentRowFieldValues);
            System.out.println("exitHdr");
        }

        @Override
        public void enterRow(CSVParser.RowContext ctx) {
            currentRowFieldValues = new ArrayList<>();
            System.out.println("enterRow");
        }

        @Override
        public void exitRow(CSVParser.RowContext ctx) {
            System.out.println("exitRow");
            // If this is the header row, do nothing
            // if ( ctx.parent instanceof CSVParser.HdrContext ) return; OR:
            if (ctx.getParent().getRuleIndex() == CSVParser.RULE_hdr) {
                System.out.println("is hdr");
                return;
            }
            // It's a data row
            Map<String, String> m = new LinkedHashMap<>();
            int i = 0;
            for (String v : currentRowFieldValues) {
                m.put(header.get(i), v);
                i++;
            }
            rows.add(m);
        }

        @Override
        public void exitString(CSVParser.StringContext ctx) {
            currentRowFieldValues.add(ctx.STRING().getText());
        }

        @Override
        public void exitText(CSVParser.TextContext ctx) {
            currentRowFieldValues.add(ctx.TEXT().getText());
        }

        @Override
        public void exitEmpty(CSVParser.EmptyContext ctx) {
            currentRowFieldValues.add(EMPTY);
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
        CSVLexer lexer = new CSVLexer(CharStreams.fromStream(is));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CSVParser parser = new CSVParser(tokens);
        parser.setBuildParseTree(true); // tell ANTLR to build a parse tree
        ParseTree tree = parser.file();

        ParseTreeWalker walker = new ParseTreeWalker();
        Loader loader = new Loader();
        walker.walk(loader, tree);
        System.out.println(loader.rows);
    }
}
