import java.io.IOException;
import java.util.Scanner;

/**
 * Created by eli on 6/13/15.
 */

public class Parser {

    private byte[] cells;
    private int cellIndex;
    private int tokenIndex;
    private BFLexer Blex;
    private char[] tokens;

    public Parser(BFLexer l) throws IOException {
        cells = new byte[30000];
        cellIndex = 0;
        tokenIndex = 0;
        Blex = l;
        tokens = Blex.getTokens();
    }

    public void parse() {
        while(tokenIndex < tokens.length) {
            performAction(tokens[tokenIndex]);
        }
    }

    public void performAction(char token) {
        switch(token) {
            case Token.INCREMENT:
                incrementData();
                break;
            case Token.DECREMENT:
                decrementData();
                break;
            case Token.MOVE_FORWARD:
                incrementIndex();
                break;
            case Token.MOVE_BACKWARD:
                decrementIndex();
                break;
            case Token.PRINT:
                printToConsole();
                break;
            case Token.USER_INPUT:
                handleUserInput();
                break;
            case Token.START_LOOP:
                startLoop();
                break;
            case Token.END_LOOP:
                endLoop();
                break;
            default:
                // do nothing; anything other than defined token is considered a comment
                break;
        }
        tokenIndex++;
    }

    public void incrementData() {
        cells[cellIndex]++;
    }

    public void decrementData() {
        cells[cellIndex]--;
    }

    public void incrementIndex() {
        cellIndex++;
    }

    public void decrementIndex() {
        cellIndex--;
    }

    public void printToConsole() {
        System.out.printf("%c", (char) cells[cellIndex]);
    }

    public void handleUserInput() {
        Scanner s = new Scanner(System.in);
        cells[cellIndex] = (byte) s.next().charAt(0);
    }

    public void startLoop() {
        if(cells[cellIndex] == 0) {
            int loopCount = 1;
            while(loopCount > 0) {
                if(tokens[tokenIndex + 1] == Token.START_LOOP) {
                    loopCount++;
                }
                if(tokens[tokenIndex + 1] == Token.END_LOOP) {
                    loopCount--;
                }
                tokenIndex++;
            }
        }
    }

    public void endLoop() {
        int loopCount = 1;
        while(loopCount > 0) {
            if(tokens[tokenIndex - 1] == Token.START_LOOP) {
                loopCount--;
            }
            if(tokens[tokenIndex - 1] == Token.END_LOOP) {
                loopCount++;
            }
            tokenIndex--;
        }
        tokenIndex--;
    }
}