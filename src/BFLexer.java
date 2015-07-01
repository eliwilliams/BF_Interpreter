import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by eli on 6/13/15.
 */

public class BFLexer {

    private final File code;

    public BFLexer(String path) {
        code = new File(path);
    }

    public char[] interpret() throws IOException {
        String chars = "";
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(code),
                        Charset.forName("UTF-8")));
        int c;
        while ((c = reader.read()) != -1) {
            char character = (char) c;
            chars += character;
        }
        char[] returnChars = chars.toCharArray();
        return returnChars;
    }
}