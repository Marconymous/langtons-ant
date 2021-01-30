package simulation;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

public class UpdateThread extends Thread {
    private final String threadName;
    private final Main main;
    private final LogicHandler log;

    public UpdateThread(String threadName, Canvas canvas, Main main) {
        this.threadName = threadName;
        this.main = main;

        log = new LogicHandler(canvas);
    }


    public void start() {
    }

    @Override
    public void run() {
        System.out.println("Thread " + threadName + " is now running");
    }


    int i = 0;
    public void update() {
        i++;
        log.move();
        if (i < 11000) update();
    }
}