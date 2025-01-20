package viewcontroller;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import model.OthelloModel;
import util.Observable;
import util.Observer;

/**
 * A class representing the label that display's which player is currently
 * playing
 *
 * @author Vishrut Kevadiya
 *
 */
public class NextPlayerLabel extends Label implements Observer {

    @Override
    /**
     * A Method that updates who's turn it is
     *
     * @param A concrete Observable
     */
    public void update(Observable observe) {
        OthelloModel m = (OthelloModel) observe;
        Image x = new Image("/x.png");
        Image o = new Image("/o.png");
        ImageView OimageView = new ImageView(o);
        ImageView XimageView = new ImageView(x);
        OimageView.setFitHeight(30);
        OimageView.setFitWidth(30);
        XimageView.setFitHeight(30);
        XimageView.setFitWidth(30);
        if (!m.othelloController.othello.isGameOver()) {
            if (m.othelloController.othello.getWhosTurn() == 'X') {
                this.setGraphic(XimageView);
            } else if (m.othelloController.othello.getWhosTurn() == 'O') {
                this.setGraphic(OimageView);
            }
        }
        if(m.othelloController.othello.isGameOver()) {
            this.setGraphic(null);
            this.setText("GAME OVER!!");
            this.setTextAlignment(TextAlignment.CENTER);
            this.setStyle("-fx-text-fill: black;");
        }
    }
}
