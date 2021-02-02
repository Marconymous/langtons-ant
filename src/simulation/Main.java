package simulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.scene.paint.Color.WHITE;


public class Main extends Application {
    private LogicHandler log;
    private final Canvas canvas;
    private final Timeline animation;
    private int amtSteps = 0;
    private final Label stepCounter = new Label("Steps : " + amtSteps);

    public Main() {
        canvas = new Canvas(1000, 1000);
        log = new LogicHandler(canvas);
        animation = new Timeline(new KeyFrame(Duration.millis(1), event -> {
            log.move();
            amtSteps++;
            updateSteps(stepCounter, amtSteps);
        }));
        animation.setCycleCount(Animation.INDEFINITE);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setCenter(canvas);

        // Menu Buttons
        HBox menu = new HBox();
        menu.setStyle("-fx-background-color: #ffffff");

        Button start = new Button("Start");
        start.setStyle("-fx-background-color: #30b564");
        start.setOnAction(e -> {
            animation.play();
        });

        Button stop = new Button("Stop");
        stop.setStyle("-fx-background-color: #9E0149");
        stop.setOnAction(e -> {
            animation.stop();
            start.getStyle();
        });

        TextField steps = new TextField("1");
        steps.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                steps.setText(newValue.replaceAll("[^\\d]", "1"));
            }
        });

        Button step = new Button("Next");
        step.setStyle("-fx-background-color: #1f4168");
        step.setOnAction(e -> {
            for (int i = 0; i < Integer.parseInt(steps.getText()); i++) {
                log.move();
                amtSteps++;
                updateSteps(stepCounter, amtSteps);
            }
        });

        Button gotoStep = new Button("Goto");
        gotoStep.setStyle("-fx-background-color: #EDC211");
        gotoStep.setOnAction(e -> {
            log = new LogicHandler(canvas);
            GraphicsContext qc = canvas.getGraphicsContext2D();
            qc.setFill(WHITE);
            qc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            amtSteps = 0;
            for (int i = 0; i < Integer.parseInt(steps.getText()); i++) {
                log.move();
                amtSteps++;
            }
            updateSteps(stepCounter, amtSteps);
        });

        menu.getChildren().addAll(start, stop, new Label("\t"), steps, step, gotoStep, new Label("\t"), stepCounter);
        // end of menu

        root.setTop(menu);

        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.setTitle("Langtons Ant");
        primaryStage.show();
    }

    private void updateSteps(Label label, int amtSteps) {
        label.setText("Steps : " + amtSteps);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
