package com.tasktree.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;

public class EdgeView extends CubicCurve {

    private NodeView parent;
    private NodeView child;

    public EdgeView(NodeView parent, NodeView child) {
        this.parent = parent;
        this.child = child;

        setStroke(Color.web("#909090"));
        setStrokeWidth(3);
        setFill(Color.TRANSPARENT);

        updateCurve();

        parent.layoutXProperty().addListener((o, a, b) -> updateCurve());
        parent.layoutYProperty().addListener((o, a, b) -> updateCurve());
        child.layoutXProperty().addListener((o, a, b) -> updateCurve());
        child.layoutYProperty().addListener((o, a, b) -> updateCurve());
    }

    private void updateCurve() {
        double startX = parent.getLayoutX() + parent.getWidth() / 2;
        double startY = parent.getLayoutY() + parent.getHeight() / 2;
        double endX = child.getLayoutX() + child.getWidth() / 2;
        double endY = child.getLayoutY() + child.getHeight() / 2;

        double controlOffset = Math.abs(endX - startX) * 0.5;

        setStartX(startX);
        setStartY(startY);
        setEndX(endX);
        setEndY(endY);

        setControlX1(startX + controlOffset);
        setControlY1(startY);
        setControlX2(endX - controlOffset);
        setControlY2(endY);
    }
}
