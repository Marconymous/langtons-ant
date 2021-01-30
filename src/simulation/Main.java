package simulation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private LogicHandler logic;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Canvas canvas = new Canvas(1000, 1000);
        BorderPane root = new BorderPane(canvas);
        logic = new LogicHandler(canvas);

        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.setTitle("Langtons Ant");
        primaryStage.show();

        update();
    }

    private void update() {
        for (int i = 0; i < 11111; i++) {
            logic.update();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
