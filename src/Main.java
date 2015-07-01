import java.io.IOException;

/**
 * Created by eli on 6/13/15.
 */

public class Main {

    // TODO: add classes to translate BF derivatives to work with Parser class

    // configure for desired file and lexer type
    public static void main(String[] args) {
        BFLexer lex = new BFLexer("code.txt");  // pass file path in string form
        try {
            Parser p = new Parser(lex); // pass lexer type (Brainfuck in this case)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}