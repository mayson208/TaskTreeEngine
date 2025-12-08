package com.tasktree.core;

import javafx.scene.paint.Color;

public class Theme {

    private String name;

    private Color backgroundColor;
    private Color gridColor;
    private Color nodeStrokeColor;
    private Color edgeColor;

    private double nodeStrokeWidth;
    private double edgeWidth;

    private boolean showGrid;

    public Theme(String name,
                 Color backgroundColor,
                 Color gridColor,
                 Color nodeStrokeColor,
                 Color edgeColor,
                 double nodeStrokeWidth,
                 double edgeWidth,
                 boolean showGrid) {

        this.name = name;
        this.backgroundColor = backgroundColor;
        this.gridColor = gridColor;
        this.nodeStrokeColor = nodeStrokeColor;
        this.edgeColor = edgeColor;
        this.nodeStrokeWidth = nodeStrokeWidth;
        this.edgeWidth = edgeWidth;
        this.showGrid = showGrid;
    }

    public String getName() {
        return name;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getGridColor() {
        return gridColor;
    }

    public Color getNodeStrokeColor() {
        return nodeStrokeColor;
    }

    public Color getEdgeColor() {
        return edgeColor;
    }

    public double getNodeStrokeWidth() {
        return nodeStrokeWidth;
    }

    public double getEdgeWidth() {
        return edgeWidth;
    }

    public boolean isShowGrid() {
        return showGrid;
    }

    public static Theme DARK() {
        return new Theme(
                "Dark",
                Color.web("#1e1e1e"),
                Color.web("#2c2c2c"),
                Color.web("#ffffff"),
                Color.web("#888888"),
                2.0,
                2.0,
                true
        );
    }

    public static Theme LIGHT() {
        return new Theme(
                "Light",
                Color.web("#f6f6f6"),
                Color.web("#dddddd"),
                Color.web("#222222"),
                Color.web("#555555"),
                1.5,
                2.0,
                true
        );
    }

    public static Theme BLUEPRINT() {
        return new Theme(
                "Blueprint",
                Color.web("#0a1024"),
                Color.web("#102040"),
                Color.web("#67a8ff"),
                Color.web("#89c4ff"),
                2.0,
                2.5,
                true
        );
    }
}
