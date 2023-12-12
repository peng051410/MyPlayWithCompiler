import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import tour.RowsLexer;
import tour.RowsParser;

/**
 * @author tomyli
 * @date 2023/12/12 enjoy
 */
public class Col {

    public static void main(String[] args) throws IOException {

        String inputFile = null;

        if (args.length > 0) {
            inputFile = args[0];
        }

        InputStream is = System.in;
        if (inputFile != null) {
            is = Files.newInputStream(Paths.get(inputFile));
        }

        CharStream charStream = CharStreams.fromStream(is);

        RowsLexer lexer = new RowsLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        int col = Integer.parseInt(args[1]);
        RowsParser parser = new RowsParser(tokens, col); // parse column number
        parser.setBuildParseTree(false); // don't waste time building a tree
        parser.file(); // parse
    }

}
