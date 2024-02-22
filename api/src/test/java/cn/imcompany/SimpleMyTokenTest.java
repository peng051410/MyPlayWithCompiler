package cn.imcompany;

import java.io.IOException;
import java.util.List;

import cn.imcompany.api.SimpleLexer;
import cn.imcompany.api.SimpleParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

/**
 * @author tomyli
 * @date 2024/2/22 enjoy
 */
public class SimpleMyTokenTest {

    @Test
    public void testMyToken() throws IOException {
		CharStream input = CharStreams.fromFileName(
				"/Users/tomyli/github/MyPlayWithCompiler/api/src/main/Simple-input");
		SimpleLexer lexer = new SimpleLexer(input);
		MyTokenFactory factory = new MyTokenFactory(input);
		lexer.setTokenFactory(factory);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// now, print all tokens
		tokens.fill();
		List<Token> alltokens = tokens.getTokens();
		for (Token t : alltokens) System.out.println(t.toString());

		// now parse
		SimpleParser parser = new SimpleParser(tokens);
		parser.setTokenFactory(factory);
		ParseTree t = parser.s();
		System.out.println(t.toStringTree(parser));
    }
}
