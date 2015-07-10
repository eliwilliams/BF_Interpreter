import java.io.IOException;

/**
 * Created by eli on 6/13/15.
 */

public class Main {

    // configure for desired file type

    public enum CodeType {
        BF, Ook, TrollScript
    }

    public static void main(String[] args) throws IOException {
        FileHandler BFHandler = new FileHandler("BFHelloWorld.txt", CodeType.BF.name()); // pass file path and code type
        FileHandler OokHandler = new FileHandler("OokHelloWorld.txt", CodeType.Ook.name());
        FileHandler TrollScriptHandler = new FileHandler("TrollFibonacci.txt", CodeType.TrollScript.name());
        BFLexer lex = new BFLexer(TrollScriptHandler); // pass desired file handler
        Parser p = new Parser(lex); // pass lexer
        p.parse();
    }
}