package model;

import javafx.scene.layout.GridPane;
import util.Observable;
import viewcontroller.UndoOperator;

import java.util.ArrayList;

/**
 * The main model class for the Othello game, responsible for managing the game state,
 * handling moves, and interfacing with the controller and view.
 */
public class OthelloModel extends Observable {

    /**
     * The controller for the Othello game.
     */
    public OthelloController othelloController;

    /**
     * The undo operator to manage move history for undo functionality.
     */
    private final UndoOperator undoOperator;

    /**
     * The grid layout for the game board.
     */
    private GridPane grid;

    /**
     * Name of the first player.
     */
    private String player1 = "Human";

    /**
     * Name of the second player.
     */
    private String player2 = "Human";

    /**
     * Flag to indicate if the game has started.
     */
    private boolean hasStarted = false;

    /**
     * Flag to indicate if the restart button has been pressed.
     */
    private boolean restartButtonPressed = false;

    /**
     * A copy of the board state for undo functionality.
     */
    private Othello boardCopy;

    /**
     * A flag used to notify observers of board updates.
     */
    public boolean flag = false;

    /**
     * Constructor to initialize the OthelloModel.
     */
    public OthelloModel() {
        this.othelloController = initializeGame();
        this.undoOperator = new UndoOperator();
        this.boardCopy = this.othelloController.othello.copy(); // Store initial board state
    }

    /**
     * Sets the players for the game and reinitializes the game.
     *
     * @param player1 Name of player 1.
     * @param player2 Name of player 2.
     */
    public void setPlayers(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.othelloController = initializeGame();
        doMove(3, 3);
    }

    /**
     * Retrieves the name of player 1.
     *
     * @return Name of player 1.
     */
    public String getPlayer1() {
        return this.player1;
    }

    /**
     * Retrieves the name of player 2.
     *
     * @return Name of player 2.
     */
    public String getPlayer2() {
        return this.player2;
    }

    /**
     * Initializes the game based on the player types.
     *
     * @return The OthelloController for the game.
     */
    private OthelloController initializeGame() {
        switch (player1 + "_" + player2) {
            case "Human_Human":
                return new OthelloControllerHumanVSHuman();
            case "Human_Greedy":
                return new OthelloControllerHumanVSGreedy();
            case "Human_Random":
                return new OthelloControllerHumanVSRandom();
            case "Greedy_Human":
                return new OthelloControllerGreedyVSHuman();
            case "Random_Greedy":
                return new OthelloControllerRandomVSGreedy();
            case "Random_Random":
                return new OthelloControllerRandomVSRandom();
            case "Random_Human":
                return new OthelloControllerRandomVSHuman();
            case "Greedy_Random":
                return new OthelloControllerGreedyVSRandom();
            case "Greedy_Greedy":
                return new OthelloControllerGreedyVSGreedy();
            default:
                throw new IllegalArgumentException("Unsupported player combination.");
        }
    }

    /**
     * Initializes the players based on their types and notifies observers.
     */
    public void initPlayers() {
        othelloController.player1 = createPlayer(player1, OthelloBoard.P1);
        othelloController.player2 = createPlayer(player2, OthelloBoard.P2);
        this.notifyObservers();
    }

    /**
     * Creates a player of the specified type and symbol.
     *
     * @param type   The type of player (e.g., "Human", "Greedy", "Random").
     * @param symbol The symbol representing the player on the board.
     * @return A Player instance.
     */
    private Player createPlayer(String type, char symbol) {
        switch (type) {
            case "Human":
                return new PlayerHuman(othelloController.othello, symbol);
            case "Greedy":
                return new PlayerGreedy(othelloController.othello, symbol);
            case "Random":
                return new PlayerRandom(othelloController.othello, symbol);
            default:
                throw new IllegalArgumentException("Invalid player type.");
        }
    }

    /**
     * Checks if the game has started.
     *
     * @return True if the game has started, false otherwise.
     */
    public boolean hasGameStarted() {
        return hasStarted;
    }

    /**
     * Sets the game started state and resets the restart flag.
     *
     * @param hasStarted True to indicate the game has started.
     */
    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
        this.restartButtonPressed = false;
    }


    /**
     * Executes a move for the current player at the specified row and column.
     * If the player cannot move, and it is the computer's turn, the computer makes its move.
     *
     * @param row the row where the move should be made
     * @param col the column where the move should be made
     */
    public void doMove(int row, int col) {
        if (canPlayerMove()) {
            performMove(row, col);
        } else if (isComputerTurn()) {
            computerMove();
        }
    }

    /**
     * Checks if the current player can make a move.
     *
     * @return true if the current player is human and can make a move, false otherwise
     */
    private boolean canPlayerMove() {
        char turn = othelloController.othello.getWhosTurn();
        return (turn == 'X' && player1.equals("Human")) || (turn == 'O' && player2.equals("Human"));
    }

    /**
     * Performs a move at the specified row and column.
     * The move is recorded, and the board is updated if the move is valid.
     *
     * @param row the row where the move should be made
     * @param col the column where the move should be made
     */
    private void performMove(int row, int col) {
        boardCopy = othelloController.othello.copy();
        if (othelloController.othello.move(row, col)) {
            undoOperator.acceptMove(new Move(row, col, this));
            updateBoard();
        }
    }

    /**
     * Checks if it is the computer's turn to make a move.
     *
     * @return true if the current player is a computer, false otherwise
     */
    private boolean isComputerTurn() {
        char turn = othelloController.othello.getWhosTurn();
        return (turn == 'X' && !player1.equals("Human")) || (turn == 'O' && !player2.equals("Human"));
    }

    /**
     * Executes a move for the computer player.
     * The computer's move is determined automatically, and the board is updated.
     * If both players are computers, it will recursively perform moves until the game ends.
     */
    private void computerMove() {
        Move move = getComputerMove();
        if (move != null && othelloController.othello.move(move.getRow(), move.getCol())) {
            undoOperator.acceptMove(move);
            updateBoard();
        }
        if (isBothComputerPlayers() && !othelloController.othello.isGameOver()) {
            computerMove();
        }
    }

    /**
     * Retrieves the next move for the current computer player.
     *
     * @return a {@link Move} object representing the computer's chosen move
     */
    private Move getComputerMove() {
        return othelloController.othello.getWhosTurn() == 'X' ?
                othelloController.player1.getMove() :
                othelloController.player2.getMove();
    }

    /**
     * Checks if both players in the game are computers.
     *
     * @return true if both players are computer-controlled, false otherwise
     */
    private boolean isBothComputerPlayers() {
        return !player1.equals("Human") && !player2.equals("Human");
    }

    /**
     * Updates the game board and notifies all observers.
     * The `flag` variable is toggled to indicate the update process.
     */
    public void updateBoard() {
        flag = true;
        this.notifyObservers();
        flag = false;
    }

    /**
     * Restarts the game with default human players.
     * Resets the game state, updates the board, and sets the game as started.
     */
    public void restart() {
        player1 = "Human";
        player2 = "Human";
        othelloController = initializeGame();
        updateBoard();
        setHasStarted(true);
        this.restartButtonPressed = true;
    }


    /**
     * A method to set the model's grid
     *
     * @param grid the grid to be set
     */
    public void setGrid(GridPane grid) {
        this.grid = grid;
    }

    /**
     * A method to store all the available moves for a player.
     *
     * @return An ArrayList consisting of all the available moves for a give player.
     */
    private ArrayList<Move> AvailableMoves() {
        AvailableMoves moves = new AvailableMoves(this.othelloController.othello, this.othelloController.othello.getWhosTurn());
        return (moves.getAllMove());
    }

    /**
     * Checking if a player has a move at (row, col)
     *
     * @param row
     * @param col
     * @return If the move exists or no
     */
    public boolean AvailableExists(int row, int col) {

        for (Move available : AvailableMoves()) {
            if (available.getRow() == row && available.getCol() == col) {
                return true;
            }
        }
        return false;
    }

    /**
     * Undoes the last move if possible.
     * The method checks if there are moves to undo and if the game is not over.
     * If so, it reverts the board to the previous state, notifies observers, and updates the board.
     *
     * @return true if the last move was successfully undone, false if no moves can be undone
     */
    public boolean undoMove() {
        if (undoOperator.hasMoves() && !othelloController.othello.isGameOver()) {
            this.othelloController = initializeGame();
            this.notifyObservers();
            updateBoard();
            undoOperator.undoMove();
            this.notifyObservers();
            return true;
        }
        return false;
    }



    /**
     * A method to do a move at (row,col) if its possible
     *
     * @param row
     * @param col
     */
    public void directMove(int row, int col) {
        this.boardCopy = this.othelloController.othello.copy();
        if (this.othelloController.othello.move(row, col)) {
            updateBoard();
        }
    }


}
