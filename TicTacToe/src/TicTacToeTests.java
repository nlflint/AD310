import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class TicTacToeTests extends TicTacToe {



    @Before public void BeforeEachTest() {
        resetBoard();
    }

    @Test
    public void IsGameOver_WhenCatsGame_IsTrue() {
        // arrange
        playCatsGame();

        // Assert
        assertTrue(isGameOver());
    }

    @Test
    public void IsGameOver_WhenXWinsAfter5Moves_IsTrue() {
        // arrange
        playXWins();

        // Assert
        assertTrue(isGameOver());
    }

    private void playXWins() {
        playSquares(5, 1, 2, 7, 8);
    }

    @Test
    public void IsGameOver_WhenNoWinnerAndGameNotDone_IsFalse() {
        // arrange
        playXWins();

        // Assert
        assertTrue(isGameOver());
    }

    @Test
    public void IsGameOver_WhenOWinsAfter6Moves_IsFalse() {
        // arrange
        playOWins();

        // Assert
        assertTrue(isGameOver());
    }

    private void playOWins() {
        playSquares(5, 1, 2, 7, 3, 4);
    }

    @Test
    public void IsGameOver_WithNoPlays_IsFalse() {
        // arrange nothing

        // Assert
        assertFalse(isGameOver());
    }

    @Test
    public void resetBoard_AfterGameOver_GameShallNotBeGameOver() {
        // arrange
        playCatsGame();

        // Act
        boolean beforeReset = isGameOver();
        resetBoard();
        boolean afterReset = isGameOver();

        // Assert
        assertTrue(beforeReset);
        assertFalse(afterReset);
    }

    @Test
    public void getWinner_WhenCatsGame_IsCatsGame() {
        // arrange cats game
        playCatsGame();

        // Assert
        assertEquals("cat game", getWinner());
    }

    @Test
    public void getWinner_WhenXWins_IsX() {
        // arrange cats game
        playXWins();

        // Assert
        assertEquals("X", getWinner());
    }

    @Test
    public void getWinner_WhenOWins_IsO() {
        // arrange cats game
        playOWins();

        // Assert
        assertEquals("O", getWinner());
    }

    @Test
    public void isLegalMove_WhenXHasAlreadyPlayedSpace_ReturnsFalse() {
        // arrange
        playSquares(1,2,3);

        // assert
        assertFalse(isLegalMove(1));

    }

    @Test
    public void isLegalMove_WhenOHasAlreadyPlayedSpace_ReturnsFalse() {
        // arrange
        playSquares(1,2,3);

        // assert
        assertFalse(isLegalMove(2));

    }

    @Test
    public void isLegalMove_WhenSquareIsUnplayed_ReturnsTrue() {
        // arrange
        playSquares(1,2,3);

        // assert
        assertTrue(isLegalMove(4));

    }

    @Test
    public void isLegalMove_WithEmptyBoardAndSquareIsInBounds_ReturnsTrue() {
        // assert
        for (int square : new int[] {1,2,3,4,5,6,7,8,9}) {
            assertTrue(
                    String.format("Square %s should be legal!", square),
                    isLegalMove(square));
        }
    }

    @Test
    public void isLegalMove_WithEmptyBoardAndSquareIsOutOfBounds_ReturnsFalse() {
        // assert
        assertFalse(isLegalMove(0));
        assertFalse(isLegalMove(-1));
        assertFalse(isLegalMove(10));
        assertFalse(isLegalMove(100));

    }

    @Test
    public void printBoard_AfterCatsGame_ShowsAllPositionsPlayed() {
        // arrange
        playCatsGame();

        // act
        String result = capturePrintBoard();

        // assert
        assertEquals("" +
                " O | X | O \n" +
                "---+---+---\n" +
                " O | X | X \n" +
                "---+---+---\n" +
                " X | O | X \n",
                result
                );
    }

    @Test
    public void printBoard_AfterIncompleteGame_ShowsAllPlayedAndUnplayedSquares() {
        // arrange
        playSquares(1,2,3,4,5);

        // act
        String result = capturePrintBoard();

        // assert
        assertEquals("" +
                " X | O | X \n" +
                "---+---+---\n" +
                " O | X |   \n" +
                "---+---+---\n" +
                "   |   |   \n",
                result
        );
    }

    @Test
    public void printBoard_BeforePlaying_ShowsBlankBoard() {
        // arrange nothing

        // act
        String result = capturePrintBoard();

        // assert
        assertEquals("" +
                "   |   |   \n" +
                "---+---+---\n" +
                "   |   |   \n" +
                "---+---+---\n" +
                "   |   |   \n",
                result
        );
    }

    private String capturePrintBoard() {

        // Create a custom System.out
        ByteArrayOutputStream fakeOutStream;
        fakeOutStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(fakeOutStream);
        PrintStream old = System.out;
        System.setOut(ps);

        printBoard();

        //Restore original system.out
        System.out.flush();
        String result = fakeOutStream.toString();
        System.setOut(old);

        return result;
    }

    private void playCatsGame() {
        playSquares(5, 1, 2, 8, 7, 3, 6, 4, 9);
    }

    private void playSquares(int... squares) {
        char mark = 'X';
        for (int square : squares){
            place(mark, square);
            mark = toggleMark(mark);
        }
    }

    private char toggleMark(char mark) {
        return mark == 'X' ? 'O' : 'X';
    }
}

