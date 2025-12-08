package com.tasktree.app.Core.controller;

import com.tasktree.view.CanvasView;
import com.tasktree.view.NodeView;
import javafx.scene.input.MouseEvent;

public class NodeController {

    private final CanvasView canvas;

    public NodeController(CanvasView canvas) {
        this.canvas = canvas;
    }

    public void attachNodeHandlers(NodeView node) {
        node.setOnMouseClicked(this::handleClick);
        node.setOnMousePressed(this::handlePress);
        node.setOnMouseDragged(this::handleDrag);
    }

    private double offsetX;
    private double offsetY;

    private void handleClick(MouseEvent e) {
        NodeView node = (NodeView) e.getSource();
        canvas.selectNode(node);

        if (e.getClickCount() == 2) {
            node.startEditing();
        }

        e.consume();
    }

    private void handlePress(MouseEvent e) {
        NodeView node = (NodeView) e.getSource();
        offsetX = e.getSceneX() - node.getLayoutX();
        offsetY = e.getSceneY() - node.getLayoutY();
    }

    private void handleDrag(MouseEvent e) {
        NodeView node = (NodeView) e.getSource();
        double newX = e.getSceneX() - offsetX;
        double newY = e.getSceneY() - offsetY;

        node.relocate(newX, newY);
        canvas.updateEdges();

        e.consume();
    }
}
