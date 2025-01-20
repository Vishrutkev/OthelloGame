package model;

/**
 * This controller uses the Model classes to allow the computer Greedy player P1 to play
 * with computer Greedy player P2.
 *
 * @author Vishrut Kevadiya
 *
 */
public class OthelloControllerGreedyVSGreedy extends OthelloControllerVerbose {

    /**
     * Constructs a new OthelloController with a new Othello game.
     * Greedy VS Random strategy
     */
    public OthelloControllerGreedyVSGreedy() {
        super();
        this.player1 = new PlayerGreedy(this.othello, OthelloBoard.P1);
        this.player2 = new PlayerGreedy(this.othello, OthelloBoard.P2);
    }

    /**
     * Run main to play a Computer (P1) against the Computer (P2).
     * The computer (P1) && (P2) uses a greedy strategy, which picks the best possible move.

     * @param args
     */
    public static void main(String[] args) {
        OthelloControllerGreedyVSGreedy oc = new OthelloControllerGreedyVSGreedy();
        oc.play();
    }

}


