package com.tasktree.core;

import javafx.scene.paint.Color;

public class ColorUtils {

    private static double hue = 0;

    public static Color nextPastelColor() {
        hue += 47;
        hue %= 360;
        return Color.hsb(hue, 0.45, 0.95);
    }

    public static Color shift(Color c, double amount) {
        double h = (c.getHue() + amount) % 360;
        return Color.hsb(h, c.getSaturation(), c.getBrightness());
    }

    public static Color readableTextColor(Color background) {
        double luminance = (0.299 * background.getRed() +
                            0.587 * background.getGreen() +
                            0.114 * background.getBlue());
        return luminance > 0.5 ? Color.BLACK : Color.WHITE;
    }
}
