package model;

/**
 * This controller uses the Model classes to allow the computer Random player P1 to play
 * with Human player P2. The computer, P1 uses a Random strategy.
 *
 * @author Vishrut Kevadiya
 *
 */
public class OthelloControllerRandomVSHuman extends OthelloControllerVerbose {

    /**
     * Constructs a new OthelloController with a new Othello game.
     * Random VS Human strategy
     */
    public OthelloControllerRandomVSHuman() {
        super();
        this.player1 = new PlayerRandom(this.othello, OthelloBoard.P1);
        this.player2 = new PlayerHuman(this.othello, OthelloBoard.P2);
    }

    /**
     * Run main to play a Computer (P1) against the Human P2.
     * The computer uses a random strategy, that is, it randomly picks
     * one of its possible moves.

     * @param args
     */
    public static void main(String[] args) {
        OthelloControllerRandomVSHuman oc = new OthelloControllerRandomVSHuman();
        oc.play();
    }

}


