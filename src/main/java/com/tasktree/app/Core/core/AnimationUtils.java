package com.tasktree.core;

import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import javafx.scene.Node;

public class AnimationUtils {

    public static void popIn(Node node) {
        ScaleTransition st = new ScaleTransition(Duration.millis(250), node);
        node.setScaleX(0.1);
        node.setScaleY(0.1);
        st.setToX(1);
        st.setToY(1);
        st.setInterpolator(javafx.animation.Interpolator.EASE_OUT);
        st.play();
    }
}
