package model;

/**
 * This controller uses the Model classes to allow the computer Greedy player P1 to play
 * with Random player P2.
 *
 * @author Vishrut Kevadiya
 *
 */
public class OthelloControllerGreedyVSRandom extends OthelloControllerVerbose {

    /**
     * Constructs a new OthelloController with a new Othello game.
     * Greedy VS Random strategy
     */
    public OthelloControllerGreedyVSRandom() {
        super();
        this.player1 = new PlayerGreedy(this.othello, OthelloBoard.P1);
        this.player2 = new PlayerRandom(this.othello, OthelloBoard.P2);
    }

    /**
     * Run main to play a Computer (P1) against the Computer (P2).
     * The computer (P1) uses a greedy strategy, which picks the best possible move.
     * The computer (P2) uses a random strategy, that is, it randomly picks one of its possible moves.

     * @param args
     */
    public static void main(String[] args) {
        OthelloControllerGreedyVSRandom oc = new OthelloControllerGreedyVSRandom();
        oc.play();
    }

}


