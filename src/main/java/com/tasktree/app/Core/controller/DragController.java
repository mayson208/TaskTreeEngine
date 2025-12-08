package com.tasktree.app.Core.controller;

import com.tasktree.view.CanvasView;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Translate;

public class DragController {

    private final CanvasView canvas;
    private final Translate translate;

    private double lastX;
    private double lastY;

    public DragController(CanvasView canvas, Translate translate) {
        this.canvas = canvas;
        this.translate = translate;

        attach();
    }

    private void attach() {
        canvas.setOnMousePressed(this::handlePress);
        canvas.setOnMouseDragged(this::handleDrag);
    }

    private void handlePress(MouseEvent e) {
        lastX = e.getSceneX();
        lastY = e.getSceneY();
    }

    private void handleDrag(MouseEvent e) {
        if (!e.isMiddleButtonDown()) return;

        double dx = e.getSceneX() - lastX;
        double dy = e.getSceneY() - lastY;

        translate.setX(translate.getX() + dx);
        translate.setY(translate.getY() + dy);

        lastX = e.getSceneX();
        lastY = e.getSceneY();
    }
}
