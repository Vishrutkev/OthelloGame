package viewcontroller;

import java.util.Optional;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.OthelloModel;

public class OthelloView {
    private OthelloModel model;
    private Scene introScene, gameScene;

    public OthelloView(OthelloModel othelloModel, Stage stage) {
        this.model = othelloModel;
        initUI(stage);

    }

    private GridPane createIntroScreen(Stage stage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);

        // Welcome Label
        Label welcomeLabel = new Label("Welcome to Othello!");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Opponent Selection
        Label opponentLabel = new Label("Choose the player types:");
        opponentLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #34495e;");
        ComboBox<String> player1ComboBox = new ComboBox<>();
        player1ComboBox.getItems().addAll("Human", "Greedy", "Random");
        player1ComboBox.getSelectionModel().selectFirst();
        player1ComboBox.setStyle("-fx-font-size: 14px; -fx-padding: 5px;");

        Label vsLabel = new Label("v/s");

        ComboBox<String> player2ComboBox = new ComboBox<>();
        player2ComboBox.getItems().addAll("Human", "Greedy", "Random");
        player2ComboBox.getSelectionModel().selectFirst();
        player2ComboBox.setStyle("-fx-font-size: 14px; -fx-padding: 5px;");

        // Start Button
        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");
        startButton.setOnAction(e -> {
            String player1 = player1ComboBox.getValue();
            String player2 = player2ComboBox.getValue();
            new ControllerHandler(model, player1, player2);
            stage.setScene(gameScene);
        });

        for (int i = 0; i < 15; i++) {
            Label label = new Label();
            label.setMinWidth(40);
            label.setMinHeight(40);
            grid.add(label, i, 0, 1, 1);
        }

        // Layout Arrangement
        grid.add(welcomeLabel, 5, 0, 7, 1);
        grid.add(opponentLabel, 2, 1, 2, 1);
        grid.add(player1ComboBox, 5, 1, 3, 1);
        grid.add(vsLabel, 8, 1, 1, 1);
        grid.add(player2ComboBox, 9, 1, 3, 1);
        grid.add(startButton, 5, 2, 4, 1);
        GridPane.setHalignment(startButton, HPos.CENTER);
        return grid;
    }

    private VBox createGameScreen(Stage stage) {
        // CONTROLLER->MODEL hookup
        ControllerHandler controller = new ControllerHandler(model);
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        NextPlayerLabel nextPlayer = new NextPlayerLabel();
        TokenX tokenX = new TokenX();
        TokenO tokenO = new TokenO();
        WinnerLabel winner = new WinnerLabel();
        Player1Label p1 = new Player1Label(model);
        Player2Label p2 = new Player2Label(model);
        Button undoBtn = new Button();

        // Top Information Section
        HBox topInfo = new HBox(20);
        topInfo.setAlignment(Pos.CENTER);

        Label currentPlayerLabel = new Label("Current Player: ");
        currentPlayerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #2980b9;");

        nextPlayer.setTextFill(Color.web("#ffffff"));
        nextPlayer.setMaxWidth(Double.MAX_VALUE);
        nextPlayer.setMaxHeight(Double.MAX_VALUE);
        Image x = new Image("x.png");
        ImageView XimageView = new ImageView(x);
        XimageView.setFitHeight(30);
        XimageView.setFitWidth(30);
        nextPlayer.setGraphic(XimageView);
        nextPlayer.setTextFill(Color.web("#FFFFFF"));
        tokenX.setText("2");

        winner.setText("Game in Progress");
        winner.setTextFill(Color.web("#000000"));

        winner.setStyle(
                "-fx-background-color: #A5D6A7; -fx-border-color: #81C784; -fx-font-weight: bold; -fx-font-size: 16px; -fx-padding: 10px; -fx-border-radius: 10px; -fx-background-radius: 10px;");

        winner.setMaxWidth(Double.MAX_VALUE);
        winner.setMaxHeight(Double.MAX_VALUE);


        Button restartButton = new Button("Restart Game");
        restartButton.setStyle("-fx-background-color: #FF6347; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");

        restartButton.setMaxHeight(80);
        restartButton.setOnAction(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Restart Game");
            alert.setHeaderText("Are you sure you want to restart?");
            alert.getButtonTypes().setAll(
                    new ButtonType("Yes", ButtonData.OK_DONE),
                    new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get().getButtonData() == ButtonData.OK_DONE) {
                model.restart();
                nextPlayer.setText("");
                stage.setScene(introScene);
            }
        });

        topInfo.getChildren().addAll(currentPlayerLabel, restartButton);

        // Game Board Grid
        GridPane gameGrid = new GridPane();
        gameGrid.setAlignment(Pos.CENTER);
        gameGrid.setVgap(5);
        gameGrid.setHgap(5);
        gameGrid.setPadding(new Insets(10));

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Button cell = new Button();
                cell.setPrefSize(60, 60);
                cell.setStyle("-fx-background-color: #ecf0f1; -fx-border-color: #bdc3c7;");
                gameGrid.add(cell, col, row);
            }
        }

        ButtonLabel btn;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                btn = new ButtonLabel();
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                gameGrid.add(btn, col, row);
                if (model.othelloController.othello.getToken(row, col) != ' ') {
                    ImageView imageView = new ImageView(
                            getClass().getResource("/" + model.othelloController.othello.getToken(row, col) + ".png").toExternalForm());
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    btn.setGraphic(imageView);
                } else {
                    ImageView imageView = new ImageView(new Image("/empty.png"));
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    btn.setGraphic(imageView);
                }
                if (model.AvailableExists(row, col)) {
                    ImageView availableView = new ImageView(new Image("/available.png"));
                    availableView.setFitHeight(30);
                    availableView.setFitWidth(30);
                    btn.setGraphic(availableView);
                    btn.setStyle(
                            "-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-width: 1px;");
                } else {
                    btn.setId("unavailable");
                }

                btn.setPadding(new Insets(5));
                model.setGrid(gameGrid);
                btn.setOnAction(controller);
                model.attach(btn);
            }
        }

        tokenX.setMaxWidth(Double.MAX_VALUE);
        tokenX.setMaxHeight(Double.MAX_VALUE);

        tokenO.setText("2");
        tokenO.setMaxWidth(Double.MAX_VALUE);
        tokenO.setMaxHeight(Double.MAX_VALUE);

        Image undo = new Image("/undo.png");
        ImageView UndoimageView = new ImageView(undo);
        UndoimageView.setFitHeight(18);
        UndoimageView.setFitWidth(20);

        undoBtn.setText("Undo");
        undoBtn.setGraphic(UndoimageView);
        undoBtn.setGraphicTextGap(10);
        undoBtn.setMaxWidth(Double.MAX_VALUE);
        undoBtn.setMaxHeight(40);
        undoBtn.setMaxWidth(280);
        undoBtn.setStyle("-fx-background-color: #8E44AD; -fx-text-fill: #FFFFFF; -fx-border-color: #b30000; -fx-border-width: 3px; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        undoBtn.setOnAction(controller);

        // Adding labels and tokens to the game grid
        gameGrid.add(currentPlayerLabel, 0, 10, 2, 2);
        gameGrid.add(nextPlayer, 2, 10, 4, 1);

        gameGrid.add(p1, 0, 13, 3, 2);
        gameGrid.add(tokenX, 3, 13, 1, 2);

        gameGrid.add(p2, 0, 16, 3, 2);
        gameGrid.add(tokenO, 3, 16, 1, 2);

        gameGrid.add(undoBtn, 7, 10, 3, 3);
        gameGrid.add(restartButton, 7, 14, 3, 3);

        gameGrid.add(winner, 0, 20, 5, 2);

        model.attach(nextPlayer);
        model.attach(tokenX);
        model.attach(tokenO);
        model.attach(winner);
        model.attach(p1);
        model.attach(p2);

        layout.getChildren().addAll(topInfo, gameGrid);

        model.setGrid(gameGrid);

        return layout;
    }

    private void initUI(Stage stage) {

        GridPane introGrid = createIntroScreen(stage);

        VBox gameLayout = createGameScreen(stage);

        // SCENE
        introScene = new Scene(introGrid, 600, 400);
        introScene.getStylesheets().add("/style.css");

        gameScene = new Scene(gameLayout, 800, 600);
        gameScene.getStylesheets().add("/style.css");

        // STAGE
        stage.setTitle("Othello");
        stage.setScene(introScene);
        stage.setResizable(false);
        stage.setWidth(1000);
        stage.setHeight(800);

        // LAUNCH THE GUI
        stage.show();
    }
}

