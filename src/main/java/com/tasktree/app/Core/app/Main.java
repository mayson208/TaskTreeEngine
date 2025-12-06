package com.tasktree.app;

import com.tasktree.view.CanvasView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        CanvasView canvas = new CanvasView();
        Scene scene = new Scene(canvas, 1200, 800);
        primaryStage.setTitle("TaskTree Engine");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
