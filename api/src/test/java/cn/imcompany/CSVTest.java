package cn.imcompany;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import cn.imcompany.api.CSVLexer;
import cn.imcompany.api.CSVParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.antlr.v4.runtime.UnbufferedTokenStream;
import org.junit.jupiter.api.Test;

/**
 * @author tomyli
 * @date 2024/2/22 enjoy
 */
public class CSVTest {

    @Test
    public void testCSV() throws FileNotFoundException {
        String inputFile = "/Users/tomyli/github/MyPlayWithCompiler/api/src/main/java/cn/imcompany/sample.csv";
        InputStream is = new FileInputStream(inputFile);
        
        // Very useful when efficiency is the top concern.
        CharStream input = new UnbufferedCharStream(is);
        CSVLexer lex = new CSVLexer(input);
        // copy text out of sliding buffer and store in tokens
        lex.setTokenFactory(new CommonTokenFactory(true));
        TokenStream tokens = new UnbufferedTokenStream<CommonToken>(lex);
        CSVParser parser = new CSVParser(tokens);
        parser.setBuildParseTree(false);
        parser.file();
    }

}
