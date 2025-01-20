package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Represents a human player in the Othello game.
 * The player provides input via the console to make a move on the game board.
 * This class extends the abstract {@link Player} class and implements the method to allow human players
 * to enter their moves (row and column) during their turn.
 *
 * The input is validated to ensure it is within the allowed range (1-8 for both rows and columns).
 * If an invalid move is entered, the player is prompted to enter it again.
 * 
 * @author Vishrut Kevadiya
 *
 */
public class PlayerHuman extends Player {
	private static final String INVALID_INPUT_MESSAGE = "Invalid number, please enter 1-8";
	private static final String IO_ERROR_MESSAGE = "I/O Error";
	private static BufferedReader stdin= new BufferedReader(new InputStreamReader(System.in));

	public PlayerHuman(Othello othello, char player) {
		super(othello, player);
	}

	public Move getMove() {
		int row = getMove("row: ");
		int col = getMove("col: ");
		return new Move(row, col);
	}

	/**
	 * Prompts the player to enter a valid number for either the row or column.
	 * This method reads the player's input, checks if the input is an integer,
	 * and ensures that the number falls within the valid range (1-8).
	 *
	 * @param message The message to prompt the player with ("row: " or "col: ").
	 * @return A valid move (row or column) if successful, or -1 in case of invalid input.
	 */

	private int getMove(String message) {
		int move, lower = 0, upper = 7;
		while (true) {
			try {
				System.out.print(message);
				String line = PlayerHuman.stdin.readLine();
				move = Integer.parseInt(line);
				if (lower <= move && move <= upper) {
					return move;
				} else {
					System.out.println(INVALID_INPUT_MESSAGE);
				}
			} catch (IOException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
				break;
			} catch (NumberFormatException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
			}
		}
		return -1;
	}
}


