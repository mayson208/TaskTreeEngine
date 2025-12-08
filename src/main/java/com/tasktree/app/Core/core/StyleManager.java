package com.tasktree.core;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class StyleManager {

    private static Theme currentTheme = Theme.DARK();

    public static Theme getTheme() {
        return currentTheme;
    }

    public static void setTheme(Theme theme, Scene scene) {
        currentTheme = theme;
        applyThemeToScene(scene);
    }

    public static void nextTheme(Scene scene) {
        if (currentTheme == Theme.DARK()) {
            currentTheme = Theme.LIGHT();
        } else if (currentTheme == Theme.LIGHT()) {
            currentTheme = Theme.BLUEPRINT();
        } else {
            currentTheme = Theme.DARK();
        }
        applyThemeToScene(scene);
    }

    public static void applyThemeToScene(Scene scene) {
        if (scene == null) return;

        // Apply background directly to the root
        if (scene.getRoot() instanceof Pane) {
            Pane root = (Pane) scene.getRoot();
            root.setStyle("-fx-background-color: " + toCss(currentTheme.getBackgroundColor()) + ";");
        }

        scene.getStylesheets().clear();
        scene.getStylesheets().add("/css/global.css");
    }

    public static void applyThemeToCanvas(Pane canvas) {
        if (canvas == null) return;
        canvas.setStyle("-fx-background-color: " + toCss(currentTheme.getBackgroundColor()) + ";");
    }

    private static String toCss(javafx.scene.paint.Color color) {
        return String.format("#%02X%02X%02X",
                (int)(color.getRed() * 255),
                (int)(color.getGreen() * 255),
                (int)(color.getBlue() * 255));
    }
}
