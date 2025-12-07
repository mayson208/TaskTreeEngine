package com.tasktree.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;

public class ConnectionPreviewCurve extends CubicCurve {

    public ConnectionPreviewCurve() {
        setStroke(Color.web("#ffffff55"));
        setStrokeWidth(2.5);
        setFill(Color.TRANSPARENT);
    }

    public void update(double startX, double startY, double endX, double endY) {
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
