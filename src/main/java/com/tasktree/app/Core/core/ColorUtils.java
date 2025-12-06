package com.tasktree.core;

import javafx.scene.paint.Color;

public class ColorUtils {

    private static final Color[] PASTEL_COLORS = {
            Color.web("#A3D8F4"),
            Color.web("#F7B2BD"),
            Color.web("#C5E1A5"),
            Color.web("#FFD59E"),
            Color.web("#D7B4F3"),
            Color.web("#FFB5E8"),
            Color.web("#BFF0D4"),
            Color.web("#FAE3B4")
    };

    private static int index = 0;

    public static Color nextPastelColor() {
        Color c = PASTEL_COLORS[index];
        index = (index + 1) % PASTEL_COLORS.length;
        return c;
    }
}
