package com.tasktree.core;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationUtils {

    public static void popIn(Node node) {
        ScaleTransition st = new ScaleTransition(Duration.millis(150), node);
        node.setScaleX(0.5);
        node.setScaleY(0.5);
        st.setToX(1.0);
        st.setToY(1.0);
        st.setInterpolator(Interpolator.EASE_OUT);
        st.play();
    }

    public static void fadeIn(Node node, double durationMs) {
        FadeTransition ft = new FadeTransition(Duration.millis(durationMs), node);
        node.setOpacity(0);
        ft.setToValue(1.0);
        ft.setInterpolator(Interpolator.EASE_BOTH);
        ft.play();
    }

    public static void pulse(Node node) {
        ScaleTransition st = new ScaleTransition(Duration.millis(180), node);
        st.setByX(0.12);
        st.setByY(0.12);
        st.setAutoReverse(true);
        st.setCycleCount(2);
        st.play();
    }

    public static void highlight(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(120), node);
        ft.setFromValue(1.0);
        ft.setToValue(0.4);
        ft.setAutoReverse(true);
        ft.setCycleCount(2);
        ft.play();
    }

    public static void smoothMove(Node node, double x, double y, double durationMs) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(durationMs), node);
        tt.setToX(x);
        tt.setToY(y);
        tt.setInterpolator(Interpolator.EASE_BOTH);
        tt.play();
    }
}
