/**
 * This is the basis for the second assignment for AD 310.
 * It's a simple implementation of a tic-tac-toe game. It
 * handles the input and output. The student responsibility
 * is the logic for the game.
 * <p>This implementation uses integers to indentify the 
 * squares of the tic-tac-toe board.
 * <blockquote><pre>
 *  1 | 2 | 3 
 * ---+---+---
 *  4 | 5 | 6 
 * ---+---+---
 *  7 | 8 | 9 
 * </pre></blockquote>
 */
public abstract class TicTacToeBase {
    
    // The Scanner object handles gets text typed by the user
    private java.util.Scanner scan;
    
    // This variable supports a dummy implementation so that
    // the game can eventually end. It represents the number
    // of moves in the current game.
    private int count;
    
    /**
     * Initialize the game.
     */
    public TicTacToeBase() {
        // Set scan to read from the standard input stream
        scan = new java.util.Scanner(System.in);
        // Set the move counter to zero. No moves yet.
        count = 0;
    }
    
    /**
     * Check the state of the current game.
     * @return <tt>true</tt> if the came is over.
     * that is, one of the two players has three in a row
     * or all of the squares are filled in.
     */
    public boolean isGameOver() {
        
        // placeholder implementation
        
        // end the game after half a dozen moves
        return count > 5;
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
        
        // placeholder implementation
        
        
        return "cat game";
    }
    
    /**
     * Checks to see if the square would be a legal move.
     * @param square The square to check
     * @return <tt>true</tt> if the move is legal
     */
    public boolean isLegalMove(int square) {
        
        // placeholder implementation
        
        // check for non-negative values
        return square > 0;
    }
    
    /**
     * Place a mark at a given location. 
     * @param mark The character to display
     * @param square The square for the mark
     */
    public void place(char mark, int square) {
        
        // placeholder implementation
        
        // register that a move has occurred
        count ++;
        // print a notification about the move
        System.out.println("Move " + count + ": " +
                           mark + " at location " + square);
    }
    
    /**
     * Prints out the current state of the tic-tac-toe board.
     */
    public void printBoard() {
        
        // placeholder implementation
        
        // the board is always empty
        System.out.println("   |   |   ");
        System.out.println("---+---+---");
        System.out.println("   |   |   ");
        System.out.println("---+---+---");
        System.out.println("   |   |   ");
    }

    /**
     * Generates a string representation of the the current 
     * state of the tic-tac-toe board.
     * @return A representation of the current board.
     */
    public String toString() {
        
        // placeholder implementation
        
        // the board is always empty
        return "   |   |   \n" +
            "---+---+---\n" +
            "   |   |   \n" + 
            "---+---+---\n" +
            "   |   |   \n";
    }

    /**
     * Reset the board to start a new game.
     */
    public void resetBoard() {
        
        // placeholder implementation
        
        // reset the move counter to zero
        count = 0;
    }
    
    /**
     * Helper method to prompt the user for an integer value.
     * @return The integer value entered by the user
     */
    public final int getInput() {
        // prompt the user for input
        System.out.print("Enter a number: ");
        // verify that the input is an integer
        while( ! scan.hasNextInt()) {
            // throw away the input value
            scan.next();
            // report the error
            System.out.println("Non-numeric input. Please try again.");
            // prompt again
            System.out.print("Enter a number: ");
        }
        // return the numeric (integer) value
        return scan.nextInt();
    }

    /**
     * The main play method. This method will repeatedly 
     * prompt for a square and place the marks in the corresponding
     * square, alternating between 'X' and 'O'. This continues
     * until the game is over. Then the winner is announced and
     * the method ends.
     */
    public final void play() {
        // make sure that the game is ready
        resetBoard();
        // keep track of whose turn it is
        boolean xTurn = true;
        // the mark associated with the current player
        char player;
        // repeat until someone wins
        while( ! isGameOver()) {
            // set player to the appropriate character
            if(xTurn) player = 'X';
            else player = 'O';
            // announce whose turn it is
            System.out.println(player + "\'s turn:");
            // get user input
            int location = getInput();
            // check user input
            while( ! isLegalMove(location)) {
                // print error message
                System.out.println("Unacceptable move. Try again.");
                // announce whose turn it is
                System.out.println("Still " + player + "\'s turn:");
                // get user input again
                location = getInput();
            }
            // place a mark on the board
            place(player, location);
            // print out the current board
            System.out.println(this);
            // change to the other player
            xTurn = ! xTurn;
        }
        // The game is done. Print a blank line.
        System.out.println();
        // announce the winner.
        System.out.println("Game over. The winner: " + getWinner());
    }
    
}
