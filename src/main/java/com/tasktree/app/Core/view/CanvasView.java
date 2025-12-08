package com.tasktree.view;

import com.tasktree.core.AnimationUtils;
import com.tasktree.viewmodel.TaskTreeViewModel;
import com.tasktree.model.TaskNode;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanvasView extends Pane {

    private TaskTreeViewModel viewModel = new TaskTreeViewModel();

    private Map<NodeView, TaskNode> nodeMap = new HashMap<>();

    private List<NodeView> nodes = new ArrayList<>();
    private List<EdgeView> edges = new ArrayList<>();

    private NodeView selectedNode;
    private NodeView connectionStartNode;

    private ConnectionPreviewCurve previewCurve = null;

    private Translate translate = new Translate();
    private Scale scale = new Scale(1, 1, 0, 0);

    private double lastMouseX;
    private double lastMouseY;

    public CanvasView() {
        setPrefSize(1200, 800);
        setStyle("-fx-background-color: #1e1e1e;");

        getTransforms().addAll(translate, scale);

        setOnMouseClicked(this::handleCanvasClick);
        setOnMouseMoved(this::handleMouseMove);
        setOnMousePressed(this::handlePress);
        setOnMouseDragged(this::handleDrag);
        setOnScroll(this::handleScroll);

        setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case DELETE:
                case BACK_SPACE:
                    if (selectedNode != null) {
                        deleteNode(selectedNode);
                        selectedNode = null;
                    }
                    break;
                default:
                    break;
            }
        });
    }

    private void handleCanvasClick(MouseEvent e) {
        if (e.getTarget() instanceof NodeView) {
            NodeView clicked = (NodeView) e.getTarget();
            if (connectionStartNode == null) {
                connectionStartNode = clicked;
                startPreview(clicked);
            } else {
                if (clicked != connectionStartNode) {
                    connectNodes(connectionStartNode, clicked);
                }
                endPreview();
            }
            selectNode(clicked);
            return;
        }

        endPreview();
        clearSelection();

        if (!(e.getTarget() instanceof CanvasView)) return;

        NodeView node = createNode(e.getX(), e.getY());
        AnimationUtils.popIn(node);
    }

    private NodeView createNode(double x, double y) {
        double modelX = (x - translate.getX()) / scale.getX();
        double modelY = (y - translate.getY()) / scale.getY();

        TaskNode data = viewModel.createNode("Task", modelX, modelY);

        NodeView ui = new NodeView("Task");
        ui.setPosition(modelX, modelY);

        ui.setDeleteHandler(() -> deleteNode(ui));
        ui.setTextCommitHandler(() -> {
            TaskNode n = nodeMap.get(ui);
            n.setLabel(ui.getText());
        });

        nodes.add(ui);
        nodeMap.put(ui, data);

        getChildren().add(ui);
        return ui;
    }

    private void deleteNode(NodeView ui) {
        TaskNode data = nodeMap.get(ui);

        viewModel.deleteNode(data);
        nodeMap.remove(ui);

        List<EdgeView> toRemove = new ArrayList<>();
        for (EdgeView e : edges) {
            toRemove.add(e);
        }

        edges.removeAll(toRemove);
        getChildren().removeAll(toRemove);

        nodes.remove(ui);
        getChildren().remove(ui);

        if (selectedNode == ui) selectedNode = null;
    }

    private void connectNodes(NodeView parent, NodeView child) {
        TaskNode p = nodeMap.get(parent);
        TaskNode c = nodeMap.get(child);

        viewModel.connect(p, c);

        EdgeView edge = new EdgeView(parent, child);
        edges.add(edge);
        getChildren().add(0, edge);
    }

    private void startPreview(NodeView startNode) {
        previewCurve = new ConnectionPreviewCurve();
        getChildren().add(previewCurve);
    }

    private void handleMouseMove(MouseEvent e) {
        if (previewCurve == null || connectionStartNode == null) return;

        double startX = connectionStartNode.getLayoutX() + connectionStartNode.getWidth() / 2;
        double startY = connectionStartNode.getLayoutY() + connectionStartNode.getHeight() / 2;

        double endX = (e.getX() - translate.getX()) / scale.getX();
        double endY = (e.getY() - translate.getY()) / scale.getY();

        previewCurve.update(startX, startY, endX, endY);
    }

    private void endPreview() {
        if (previewCurve != null) getChildren().remove(previewCurve);
        previewCurve = null;
        connectionStartNode = null;
    }

    private void selectNode(NodeView node) {
        if (selectedNode != null) selectedNode.setSelected(false);
        selectedNode = node;
        selectedNode.setSelected(true);
    }

    private void clearSelection() {
        if (selectedNode != null) selectedNode.setSelected(false);
        selectedNode = null;
    }

    private void handlePress(MouseEvent e) {
        lastMouseX = e.getSceneX();
        lastMouseY = e.getSceneY();
    }

    private void handleDrag(MouseEvent e) {
        if (!e.isMiddleButtonDown()) return;

        double dx = e.getSceneX() - lastMouseX;
        double dy = e.getSceneY() - lastMouseY;

        translate.setX(translate.getX() + dx);
        translate.setY(translate.getY() + dy);

        lastMouseX = e.getSceneX();
        lastMouseY = e.getSceneY();
    }

    private void handleScroll(ScrollEvent e) {
        double zoomFactor = 1.1;
        if (e.getDeltaY() < 0) zoomFactor = 1 / zoomFactor;

        scale.setX(scale.getX() * zoomFactor);
        scale.setY(scale.getY() * zoomFactor);
    }
}
