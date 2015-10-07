public class TicTacToe extends TicTacToeBase {

    public static final String PLAYER_ONE = "X";
    public static final String PLAYER_TWO = "O";
    public static final String CATS_GAME = "cat game";

    public static void main(String[] args) {
        TicTacToe theGame = new TicTacToe();
        theGame.play();
    }

    public TicTacToe() {
        super();
        board = new int[10];
        winnableRows = new int[][] {
            {1,2,3},
            {4,5,6},
            {7,8,9},
            {1,4,7},
            {2,5,8},
            {3,6,9},
            {1,5,9},
            {3,5,7}
        };
    }

    private int[] board;
    private int turnCount;
    private int[][] winnableRows;

    /**
     * Check the state of the current game.
     * @return <tt>true</tt> if the came is over.
     */
    @Override
    public boolean isGameOver() {
        if (turnCount >= 9)
            return true;
        else if (hasPlayerWon(PLAYER_ONE))
            return true;
        else if (hasPlayerWon(PLAYER_TWO))
            return true;
        return false;
    }

    private boolean hasPlayerWon(String mark) {
        int winningSum = getPlayerIntFromMark(mark) * 3;
        for (int[] winnableRow : winnableRows)
            if (isWinningRow(winningSum, winnableRow)) return true;

        return false;
    }

    private boolean isWinningRow(int winningSum, int[] rows) {
        int rowSum = 0;

        for (int square : rows)
            rowSum += board[square];

        if (rowSum == winningSum)
            return true;
        return false;
    }

    /**
     * Place a mark at a given location.
     * @param mark The character to display
     * @param square The square for the mark
     */
    public void place(char mark, int square) {
        turnCount++;
        System.out.println("Move " + turnCount + ": " +
                mark + " at location " + square);

        int playerInt = getPlayerIntFromMark(String.valueOf(mark));
        board[square] = playerInt;
    }

    private int getPlayerIntFromMark(String mark) {
        return mark.equals(PLAYER_ONE) ? 1 : -1;
    }

    /**
     * Reset the board to start a new game.
     */
    public void resetBoard() {
        turnCount = 0;
        board = new int[10];
    }

    /**
     * Get the winner of the current game.
     * @return The winner, the player with three in a row
     * or the value "cat game" if there is no winner and
     * no more spaces left to play
     * @throws IllegalStateException when there is is no
     * winner and there are still open squares
     */
    public String getWinner() {
        if (hasPlayerWon(PLAYER_ONE))
            return PLAYER_ONE;
        else if (hasPlayerWon(PLAYER_TWO))
            return PLAYER_TWO;
        else
            return CATS_GAME;
    }

    /**
     * Checks to see if the square would be a legal move.
     * @param square The square to check
     * @return <tt>true</tt> if the move is legal
     */
    public boolean isLegalMove(int square) {
        return isWithinBounds(square) &&
                isNotPlayed(square);
    }

    private boolean isNotPlayed(int square) {
        return board[square] == 0;
    }

    private boolean isWithinBounds(int square) {
        return square >= 1 &&
                square <= 9;
    }

    /**
     * Prints out the current state of the tic-tac-toe board.
     */
    public void printBoard() {
        System.out.println(String.format(" %s | %s | %s ", getPlayer(1), getPlayer(2), getPlayer(3)));
        System.out.println("---+---+---");
        System.out.println(String.format(" %s | %s | %s ", getPlayer(4), getPlayer(5), getPlayer(6)));
        System.out.println("---+---+---");
        System.out.println(String.format(" %s | %s | %s ", getPlayer(7), getPlayer(8), getPlayer(9)));
    }

    private String getPlayer(int square) {
        if (board[square] == 1)
            return PLAYER_ONE;
        else if (board[square] == -1)
            return PLAYER_TWO;
        else
            return " ";
    }
}