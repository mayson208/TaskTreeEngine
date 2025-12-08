package com.tasktree.core;

import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class CanvasUtils {

    public static double worldX(double screenX, Translate translate, Scale scale) {
        return (screenX - translate.getX()) / scale.getX();
    }

    public static double worldY(double screenY, Translate translate, Scale scale) {
        return (screenY - translate.getY()) / scale.getY();
    }

    public static double screenX(double worldX, Translate translate, Scale scale) {
        return worldX * scale.getX() + translate.getX();
    }

    public static double screenY(double worldY, Translate translate, Scale scale) {
        return worldY * scale.getY() + translate.getY();
    }

    public static double snap(double value, double gridSize) {
        return Math.round(value / gridSize) * gridSize;
    }

    public static boolean isWithin(double x, double y, double minX, double minY, double maxX, double maxY) {
        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }
}
