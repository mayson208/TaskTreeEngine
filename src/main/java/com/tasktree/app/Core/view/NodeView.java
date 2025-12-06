package com.tasktree.view;

import com.tasktree.core.ColorUtils;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import java.util.function.Consumer;

public class NodeView extends StackPane {

    private Circle backgroundCircle;
    private Circle highlightRing;
    private Text label;

    private double mouseOffsetX;
    private double mouseOffsetY;

    private Consumer<NodeView> onDelete;
    private Consumer<NodeView> onCreateChild;

    public NodeView(String text) {
        highlightRing = new Circle(48);
        highlightRing.setFill(Color.TRANSPARENT);
        highlightRing.setStroke(Color.TRANSPARENT);
        highlightRing.setStrokeWidth(3);

        backgroundCircle = new Circle(40);
        backgroundCircle.setFill(ColorUtils.nextP
