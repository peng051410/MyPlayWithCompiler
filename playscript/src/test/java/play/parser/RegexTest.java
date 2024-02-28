package play.parser;

import static play.parser.Regex.NFA2DFA;
import static play.parser.Regex.matchWithDFA;
import static play.parser.Regex.matchWithNFA;
import static play.parser.Regex.regexToNFA;
import static play.parser.Regex.sampleGrammar1;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * @author tomyli
 * @date 2024/2/27 enjoy
 */
class RegexTest {

    @Test
    public void testSimple1() {
        ///////////////////
        //第一个例子
        //正则表达式：int|[a-zA-Z][a-zA-Z0-9]*|[0-9]+
        //也就是：int关键字、标识符或数字字面量
        GrammarNode rootNode = sampleGrammar1();
        rootNode.dump();
    }

    @Test
    public void testRegexToNFA() {
        GrammarNode rootNode = sampleGrammar1();
        State[] states = regexToNFA(rootNode);
        states[0].dump();
    }

    @Test
    public void testMatchWithNFA() {

        GrammarNode rootNode = sampleGrammar1();
        State[] states = regexToNFA(rootNode);
        states[0].dump();

        //用NFA来匹配
        matchWithNFA(states[0], "int");
        matchWithNFA(states[0], "intA");
        matchWithNFA(states[0], "23");
        matchWithNFA(states[0], "0A");
    }

    @Test
    public void testNFA2DFA() {
        //第一个例子
        //正则表达式：int|[a-zA-Z][a-zA-Z0-9]*|[0-9]+
        //也就是：int关键字、标识符或数字字面量
        GrammarNode rootNode = sampleGrammar1();
        rootNode.dump();

        System.out.println("\nNFA states:");
        State[] states = regexToNFA(rootNode);
        states[0].dump();

        //用NFA来匹配
        matchWithNFA(states[0], "int");
        matchWithNFA(states[0], "intA");
        matchWithNFA(states[0], "23");
        matchWithNFA(states[0], "0A");

        //转换成DFA
        System.out.println("\nNFA to DFA:");
        List<DFAState> dfaStates = NFA2DFA(states[0], CharSet.letterAndDigits);
        dfaStates.get(0).dump();

        //用DFA来匹配
        matchWithDFA(dfaStates.get(0), "int");
        matchWithDFA(dfaStates.get(0), "intA");
        matchWithDFA(dfaStates.get(0), "23");
        matchWithDFA(dfaStates.get(0), "0A");
    }
}
