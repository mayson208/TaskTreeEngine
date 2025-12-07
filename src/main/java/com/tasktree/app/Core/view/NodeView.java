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
        backgroundCircle.setFill(ColorUtils.nextPastelColor());

        label = new Text(text);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        getChildren().addAll(highlightRing, backgroundCircle, label);
        setPickOnBounds(false);

        setOnMousePressed(e -> {
            mouseOffsetX = e.getSceneX() - getLayoutX();
            mouseOffsetY = e.getSceneY() - getLayoutY();
        });

        setOnMouseDragged(e -> {
            double newX = e.getSceneX() - mouseOffsetX;
            double newY = e.getSceneY() - mouseOffsetY;
            relocate(newX, newY);
        });

        ContextMenu menu = new ContextMenu();

        MenuItem renameItem = new MenuItem("Rename");
        renameItem.setOnAction(a -> rename());

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(a -> {
            if (onDelete != null) onDelete.accept(this);
        });

        MenuItem childItem = new MenuItem("Create Child");
        childItem.setOnAction(a -> {
            if (onCreateChild != null) onCreateChild.accept(this);
        });

        menu.getItems().addAll(renameItem, childItem, deleteItem);

        setOnContextMenuRequested(e -> {
            menu.show(this, e.getScreenX(), e.getScreenY());
        });
    }

    private void rename() {
        TextInputDialog dialog = new TextInputDialog(label.getText());
        dialog.setTitle("Rename Node");
        dialog.setHeaderText(null);
        dialog.setContentText(null);
        dialog.showAndWait().ifPresent(label::setText);
    }

    public void setSelected(boolean selected) {
        if (selected) highlightRing.setStroke(Color.WHITE);
        else highlightRing.setStroke(Color.TRANSPARENT);
    }

    public void setOnDelete(Consumer<NodeView> action) {
        this.onDelete = action;
    }

    public void setOnCreateChild(Consumer<NodeView> action) {
        this.onCreateChild = action;
    }

    public String getText() {
        return label.getText();
    }

    public void setPosition(double x, double y) {
        setLayoutX(x - 40);
        setLayoutY(y - 40);
    }
}
