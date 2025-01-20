package viewcontroller;

import javafx.scene.control.Label;
import model.OthelloModel;
import util.Observable;
import util.Observer;

/**
 * A class that updates the label displaying the number of tokens of player 1
 *
 * @author Vishrut Kevadiya
 *
 */
public class TokenX extends Label implements Observer {

    @Override
    /**
     * Updating the label's text to display the current number of tokens on the board for player 1
     *
     * @param A concrete Observable
     */
    public void update(Observable o) {
        OthelloModel m = (OthelloModel) o;
        this.setText(" " + m.othelloController.othello.getCount('X'));
    }
}
