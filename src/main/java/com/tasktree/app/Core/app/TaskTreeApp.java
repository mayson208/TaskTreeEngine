package com.tasktree.app;

import com.tasktree.core.StyleManager;
import com.tasktree.view.CanvasView;
import com.tasktree.controller.InputController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TaskTreeApp extends Application {

    @Override
    public void start(Stage stage) {

        CanvasView canvasView = new CanvasView();

        StackPane root = new StackPane(canvasView);
        Scene scene = new Scene(root, 1400, 900);

        StyleManager.applyThemeToScene(scene);

        new InputController(scene, canvasView);

        stage.setTitle("TaskTree Engine");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
