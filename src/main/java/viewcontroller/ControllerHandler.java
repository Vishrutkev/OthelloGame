package viewcontroller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import model.OthelloModel;

public class ControllerHandler implements EventHandler<ActionEvent> {
    private OthelloModel model;

    public ControllerHandler(OthelloModel model) {
        this.model = model;
    }

    public ControllerHandler(OthelloModel model,  String player1, String player2
                             ) {
        model.setPlayers(player1, player2);
        model.setHasStarted(true);
        model.doMove(3, 3); //trigger a dummy move
    }
    /**
     * A method that routes the user's action to the corresponding method in the model to perform the appropriate task.
     *
     *
     */
    public void handle(ActionEvent event) {

        if (event.getSource() instanceof Button) {

            Button btn = (Button) event.getSource();
            System.out.println(btn.getText());
            System.out.println(model.hasGameStarted());
            if (btn.getText() == "Undo") {
                if (!model.undoMove()) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Cannot Undo Move!");
                    alert.setContentText("Click on OK to get back to the game");
                    alert.showAndWait();
                }
            }
            if (model.hasGameStarted()) {
                model.initPlayers();
            }
        }

        if (model.hasGameStarted() && event.getSource() instanceof ButtonLabel) { //doing the move based on user's selection
            ButtonLabel source = (ButtonLabel) event.getSource();
            Integer colIndex = GridPane.getColumnIndex(source);
            Integer rowIndex = GridPane.getRowIndex(source);
            model.doMove(rowIndex, colIndex);
        }

    }
}