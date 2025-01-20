package viewcontroller;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import model.OthelloModel;
import util.Observable;
import util.Observer;


/**
 * A class representing the buttons present on the OthelloBoard
 *
 * @author Vishrut Kevadiya
 *
 */
public class ButtonLabel extends Button implements Observer {
    private Image x = new Image(getClass().getResource("/x.png").toExternalForm());
    private Image o = new Image(getClass().getResource("/o.png").toExternalForm());
    private Image available = new Image(getClass().getResource("/available.png").toExternalForm());
    private Image empty = new Image(getClass().getResource("/empty.png").toExternalForm());

    @Override
    /**
     * A Method that updates the images of the tokens when the user (human or AI)
     * plays a move.
     *
     * @param A concrete Observable
     */
    public void update(Observable observe) {

        OthelloModel model = (OthelloModel) observe;

        if (model.flag) {

            int col = GridPane.getColumnIndex(this);
            int row = GridPane.getRowIndex(this);

            // Updating buttons based on the token present on it
            if (model.othelloController.othello.getToken(row, col) == 'X') {

                ImageView imageView = new ImageView(x);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                this.setGraphic(imageView);
                this.setId("unavailable");


            } else if (model.othelloController.othello.getToken(row, col) == 'O') {
                ImageView imageView = new ImageView(o);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                this.setGraphic(imageView);
                this.setId("unavailable");

            }
            else if (model.othelloController.othello.getToken(row, col) == ' ') {

                if (model.AvailableExists(row, col)) { //update a button if its an available move
                    ImageView imageView = new ImageView(available);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    this.setGraphic(imageView);
                    this.setId("available");

                } else {
                    ImageView imageView = new ImageView(empty);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    this.setGraphic(imageView);
                    this.setId("unavailable");
                }
            }

        }

    }

}
