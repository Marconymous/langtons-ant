package simulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private Canvas canvas;
    private final Timeline animation;

    public Main() {
        canvas = new Canvas(1000, 1000);
        log = new LogicHandler(canvas);
        animation = new Timeline(new KeyFrame(Duration.millis(2), event -> log.move()));
        animation.setCycleCount(Animation.INDEFINITE);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setCenter(canvas);

        // Menu Buttons
        HBox menu = new HBox();

        Button start = new Button("Start");
        start.setOnAction(e -> animation.play());

        Button stop = new Button("Stop");
        stop.setOnAction(e -> animation.stop());

        TextField steps = new TextField("1");
        steps.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                steps.setText(newValue.replaceAll("[^\\d]", "1"));
            }
        });

        Button step = new Button("Next");
        step.setOnAction(e -> {
            for (int i = 0; i < Integer.parseInt(steps.getText()); i++) {
                log.move();
            }
        });

        TextField gotoSteps = new TextField("0");
        gotoSteps.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                steps.setText(newValue.replaceAll("[^\\d]", "0"));
            }
        });

        Button gotoStep = new Button("Goto");
        gotoStep.setOnAction(e -> {
            log = new LogicHandler(canvas);
            GraphicsContext qc = canvas.getGraphicsContext2D();
            qc.setFill(WHITE);
            qc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
            for (int i = 0; i < Integer.parseInt(gotoSteps.getText()); i++) {
                log.move();
            }
        });

        Label sep1 = new Label("\t");
        Label sep2 = new Label("\t");


        menu.getChildren().addAll(start, stop, sep1, steps, step, sep2, gotoSteps, gotoStep);
        // end of menu


        root.setTop(menu);

        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.setTitle("Langtons Ant");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
