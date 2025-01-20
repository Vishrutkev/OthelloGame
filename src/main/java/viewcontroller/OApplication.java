package viewcontroller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.OthelloModel;

public class OApplication extends Application {

    OthelloModel model;
    OthelloView view;

    @Override
    public void start(Stage stage) throws Exception {
        // Create and hook up the Model, View and the controller

        // MODEL
        this.model = new OthelloModel();
        this.view = new OthelloView(model, stage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
