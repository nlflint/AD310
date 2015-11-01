import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class TicTacToeTests extends TicTacToe {
    @Before
    public void BeforeEachTest()
    {
        resetBoard();
    }

    @After
    public void AfterEach()
    {
        System.out.println("AFTER!!!");
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
        org.junit.Assert.assertTrue(isGameOver());
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
        assertEquals("cats game", getWinner());
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

    @Test(expected = IllegalStateException.class)
    public void getWinner_WhenNoWinner_ThrowsIllegalStateException() {
        getWinner();
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
    public void isLegalMove_WhenGameIsOver_ReturnsFalse() {
        // arrange
        playSquares(1,4,2,5,3);

        // assert
        assertFalse(isLegalMove(9));

    }

    @Test
    public void isLegalMove_WhenPlayingSpacesOutOfBounds_ReturnsFalse() {
        // assert
        assertFalse(isLegalMove(-100));
        assertFalse(isLegalMove(-10));
        assertFalse(isLegalMove(-1));
        assertFalse(isLegalMove(0));
        assertFalse(isLegalMove(10));
        assertFalse(isLegalMove(50));
        assertFalse(isLegalMove(100));

    }

    @Test
    public void isLegalMove_WhenPlayingSpacesinBounds_ReturnsTrue() {
        // assert
        for (int i = 1; i < 10; i++)
            assertTrue(isLegalMove(i));

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
        assertFalse(isLegalMove(Integer.MIN_VALUE));
        assertFalse(isLegalMove(-100));
        assertFalse(isLegalMove(-1));
        assertFalse(isLegalMove(0));
        assertFalse(isLegalMove(10));
        assertFalse(isLegalMove(100));
        assertFalse(isLegalMove(Integer.MAX_VALUE));

    }

    @Test(expected = PlacementOutOfBoardException.class)
    public void place_GivenValueAbove9_ThrowsAnException() {
            playSquares(10);
    }

    @Test(expected = PlacementOutOfBoardException.class)
    public void place_GivenValueBelow1_ThrowsAnException() {
        playSquares(0);
    }

    @Test(expected = DuplicatePlacementException.class)
    public void place_WhenSquareAlreadyPlayed_ThrowsAnException() {
        playSquares(1,1);
    }

    @Test
    public void toString_AfterCatsGame_ShowsAllPositionsPlayed() {
        // arrange
        playCatsGame();

        // act
        String result = toString();

        // assert
        assertEquals(buildExpectedPrintedBoard("OXOOXXXOX"), result);
    }

    @Test
    public void toString_AfterIncompleteGame_ShowsAllPlayedAndUnplayedSquares() {
        // arrange
        playSquares(1,2,3,4,5);

        // act
        String result = toString();

        // assert
        assertEquals(buildExpectedPrintedBoard("XOXOX    "), result);
    }

    private String buildExpectedPrintedBoard(String boardLayout) {
        String newLine = System.getProperty("line.separator");
        String[] plays = boardLayout.split("");
        return String.format("" +
                " %1$s | %2$s | %3$s%10$s" +
                "---+---+---%10$s" +
                " %4$s | %5$s | %6$s%10$s" +
                "---+---+---%10$s" +
                " %7$s | %8$s | %9$s%10$s",
                plays[0],
                plays[1],
                plays[2],
                plays[3],
                plays[4],
                plays[5],
                plays[6],
                plays[7],
                plays[8],
                newLine
                );
    }

    @Test
    public void toString_BeforePlaying_ShowsBlankBoard() {
        // arrange nothing

        // act
        String result = toString();

        // assert
        assertEquals(buildExpectedPrintedBoard("         "), result);
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


