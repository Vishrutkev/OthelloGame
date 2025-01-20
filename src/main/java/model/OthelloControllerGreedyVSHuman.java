package model;

/**
 * This controller uses the Model classes to allow the computer Greedy player P1 to play
 * with Human player P2. The computer, P1 uses a Greedy strategy.
 *
 * @author Vishrut Kevadiya
 *
 */
public class OthelloControllerGreedyVSHuman extends OthelloControllerVerbose {

    /**
     * Constructs a new OthelloController with a new Othello game.
     * Greedy VS Human strategy
     */
    public OthelloControllerGreedyVSHuman() {
        super();
        this.player1 = new PlayerGreedy(this.othello, OthelloBoard.P1);
        this.player2 = new PlayerHuman(this.othello, OthelloBoard.P2);
    }

    /**
     * Run main to play a Computer (P1) against the Human P2.
     * The computer uses a Greedy strategy, that is, it picks
     * one with the highest token count.

     * @param args
     */
    public static void main(String[] args) {
        OthelloControllerGreedyVSHuman oc = new OthelloControllerGreedyVSHuman();
        oc.play();
    }

}


