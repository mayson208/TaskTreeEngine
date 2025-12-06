package com.tasktree.view;

import com.tasktree.core.AnimationUtils;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import java.util.ArrayList;
import java.util.List;

public class CanvasView extends Pane {

    private List<NodeView> nodes = new ArrayList<>();
    private List<EdgeView> edges = new ArrayList<>();
    private NodeView selectedNode;

    private Translate translate = new Translate();
    private Scale scale = new Scale(1, 1, 0, 0);

    private double lastMouseX;
    private double lastMouseY;

    public CanvasView() {
        setPrefSize(1200, 800);
        setStyle("-fx-background-color: #1e1e1e;");

        getTransforms().addAll(translate, scale);

        setOnMouseClicked(this::handleCanvasClick);
        setOnMousePressed(this::handlePress);
        setOnMouseDragged(this::handleDrag);
        setOnScroll(this::handleScroll);
    }

    private void handleCanvasClick(MouseEvent e) {
        if (e.getTarget() instanceof NodeView) {
            NodeView clicked = (NodeView) e.getTarget();
            selectNode(clicked);
            return;
        }

        clearSelection();

        if (!(e.getTarget() instanceof CanvasView)) return;

        NodeView node = createNode(e.getX(), e.getY());
        AnimationUtils.popIn(node);

        if (nodes.size() > 1) {
            NodeView parent = nodes.get(nodes.size() - 2);
            connectNodes(parent, node);
        }
    }

    private NodeView createNode(double x, double y) {
        NodeView node = new NodeView("Task");
        node.setPosition((x - translate.getX()) / scale.getX(),
                         (y - translate.getY()) / scale.getY());

        node.setOnDelete(n -> deleteNode(n));
        node.setOnCreateChild(n -> createChild(n));

        nodes.
