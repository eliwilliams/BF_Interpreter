import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by eli on 7/8/15.
 */

public class FileHandler {

    private File codeFile;
    private String codeString;

    public FileHandler(String path, String codeType) throws IOException {
        codeFile = new File(path);
        codeString = interpret();
        if (codeType.matches("Ook")) {
            codeString = translateOok();
        }
        if (codeType.matches("TrollScript")) {
            codeString = translateTrollScript();
        }
    }

    public String interpret() throws IOException {
        StringBuilder chars = new StringBuilder();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(codeFile),
                        Charset.forName("UTF-8")));
        int c;
        while ((c = reader.read()) != -1) {
            char character = (char) c;
            chars.append(character);
        }
        return chars.toString();
    }

    public String translateOok() {
        StringBuilder returnCode = new StringBuilder();
        String[] tempArr = codeString.split("\\s+");
        ArrayList<String> ookTokens = new ArrayList<String>();
        for (String s : tempArr) {
            ookTokens.add(s);
        }
        for (int i = 0; i < ookTokens.size() - 1; i += 2) {
            String token = ookTokens.get(i) + " " + ookTokens.get(i + 1);
            returnCode.append(matchOokToken(token));
        }
        return returnCode.toString();
    }

    public String translateTrollScript() {
        if (codeString.substring(0, 3).toLowerCase().equals(TrollScriptTokens.OPTIONAL_PREFIX.toLowerCase())) {
            codeString = codeString.substring(3);
        }
        if (codeString.substring(codeString.length() - 2, codeString.length()).equals(TrollScriptTokens.OPTIONAL_SUFFIX)) {
            codeString = codeString.substring(0, codeString.length() - 2);
        }
        StringBuilder returnCode = new StringBuilder();
        String tempCode = codeString;
        for (int i = 0; i < tempCode.length() - 2; i += 3) {
            String token = tempCode.substring(i, i + 3);
            returnCode.append(matchTrollScriptToken(token));
        }
        return returnCode.toString();
    }

    public char matchOokToken(String s) {
        if (s.equals(OokTokens.INCREMENT))
            return Token.INCREMENT;
        if (s.equals(OokTokens.DECREMENT))
            return Token.DECREMENT;
        if (s.equals(OokTokens.MOVE_FORWARD))
            return Token.MOVE_FORWARD;
        if (s.equals(OokTokens.MOVE_BACKWARD))
            return Token.MOVE_BACKWARD;
        if (s.equals(OokTokens.PRINT))
            return Token.PRINT;
        if (s.equals(OokTokens.USER_INPUT))
            return Token.USER_INPUT;
        if (s.equals(OokTokens.START_LOOP))
            return Token.START_LOOP;
        if (s.equals(OokTokens.END_LOOP))
            return Token.END_LOOP;
        else {
            System.out.println("Encountered unexpected token: " + s);
            return ' ';
        }
    }

    public char matchTrollScriptToken(String s) {
        if (s.equals(TrollScriptTokens.INCREMENT))
            return Token.INCREMENT;
        if (s.equals(TrollScriptTokens.DECREMENT))
            return Token.DECREMENT;
        if (s.equals(TrollScriptTokens.MOVE_FORWARD))
            return Token.MOVE_FORWARD;
        if (s.equals(TrollScriptTokens.MOVE_BACKWARD))
            return Token.MOVE_BACKWARD;
        if (s.equals(TrollScriptTokens.PRINT))
            return Token.PRINT;
        if (s.equals(TrollScriptTokens.USER_INPUT))
            return Token.USER_INPUT;
        if (s.equals(TrollScriptTokens.START_LOOP))
            return Token.START_LOOP;
        if (s.equals(TrollScriptTokens.END_LOOP))
            return Token.END_LOOP;
        else {
            System.out.println("Encountered unexpected token: " + s);
            return ' ';
        }
    }

    public String getCodeString() {
        return codeString;
    }
}
