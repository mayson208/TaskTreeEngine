package com.tasktree.app.Core.controller;

import com.tasktree.core.StyleManager;
import com.tasktree.view.CanvasView;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputController {

    private final CanvasView canvas;
    private final Scene scene;

    public InputController(Scene scene, CanvasView canvas) {
        this.scene = scene;
        this.canvas = canvas;

        attach();
    }

    private void attach() {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPress);
    }

    private void handleKeyPress(KeyEvent e) {

        if (e.getCode() == KeyCode.DELETE || e.getCode() == KeyCode.BACK_SPACE) {
            canvas.deleteSelectedNode();
        }

        if (e.isControlDown() && e.getCode() == KeyCode.T) {
            StyleManager.nextTheme(scene);
        }

        if (e.isControlDown() && e.getCode() == KeyCode.N) {
            canvas.createRandomNode();
        }
    }
}
