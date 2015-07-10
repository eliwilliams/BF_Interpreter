import java.io.*;

/**
 * Created by eli on 6/13/15.
 */

public class BFLexer {

    private char[] tokens;

    public BFLexer(FileHandler f) throws IOException {
        tokens = f.getCodeString().toCharArray();
    }

    public char[] getTokens() {
        return tokens;
    }
}