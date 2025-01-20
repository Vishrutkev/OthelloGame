package viewcontroller;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.OthelloModel;
import util.Observable;
import util.Observer;

/**
 * Class representing the type of player 1
 *
 * @author Vishrut Kevadiya
 *
 */
public class Player1Label extends Label implements Observer {

    public Player1Label(OthelloModel model) {
        // Initialize text based on the current model state
        this.setText("               " + model.getPlayer1() + " player (tokens)");
        this.setStyle(
                "-fx-background-image: url('/x.png'); -fx-background-position: 5, center; -fx-background-repeat: no-repeat; -fx-background-size: 30px; "
                        + " -fx-text-fill: black;");
        this.setMaxWidth(Double.MAX_VALUE);
        this.setMaxHeight(Double.MAX_VALUE);
    }

    @Override
    /**
     * Method that updates the Label's text to display who player 1 is
     *
     * @param A concrete Observable
     */
    public void update(Observable o) {
        OthelloModel m = (OthelloModel) o;
        this.setText("               " + m.getPlayer1() + " player (tokens)");
    }
}
