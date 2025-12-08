package com.tasktree.view;

import com.tasktree.core.ColorUtils;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class NodeView extends StackPane {

    private Circle backgroundCircle;
    private Text label;

    private double mouseOffsetX;
    private double mouseOffsetY;

    private Runnable deleteHandler;
    private Runnable createChildHandler;

    public NodeView(String text) {
        backgroundCircle = new Circle(40);
        backgroundCircle.setFill(ColorUtils.nextPastelColor());

        label = new Text(text);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        getChildren().addAll(backgroundCircle, label);
        setPickOnBounds(false);

        setOnMousePressed(e -> {
            mouseOffsetX = e.getSceneX() - getLayoutX();
            mouseOffsetY = e.getSceneY() - getLayoutY();
            e.consume();
        });

        setOnMouseDragged(e -> {
            double newX = e.getSceneX() - mouseOffsetX;
            double newY = e.getSceneY() - mouseOffsetY;
            relocate(newX, newY);
            e.consume();
        });
    }

    public void setDeleteHandler(Runnable handler) {
        this.deleteHandler = handler;
    }

    public void setCreateChildHandler(Runnable handler) {
        this.createChildHandler = handler;
    }

    public void fireDelete() {
        if (deleteHandler != null) deleteHandler.run();
    }

    public void setPosition(double x, double y) {
        setLayoutX(x - 40);
        setLayoutY(y - 40);
    }

    public void setSelected(boolean selected) {
        if (selected) {
            backgroundCircle.setStroke(Color.WHITE);
            backgroundCircle.setStrokeWidth(3);
        } else {
            backgroundCircle.setStrokeWidth(0);
        }
    }
}
