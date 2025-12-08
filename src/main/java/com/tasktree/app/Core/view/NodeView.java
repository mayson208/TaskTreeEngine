package com.tasktree.view;

import com.tasktree.core.ColorUtils;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class NodeView extends StackPane {

    private Circle backgroundCircle;
    private Text label;
    private TextField editor;

    private double mouseOffsetX;
    private double mouseOffsetY;

    private Runnable deleteHandler;
    private Runnable createChildHandler;
    private Runnable textCommitHandler;

    public NodeView(String text) {
        backgroundCircle = new Circle(40);
        backgroundCircle.setFill(ColorUtils.nextPastelColor());

        label = new Text(text);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        editor = new TextField(text);
        editor.setVisible(false);
        editor.setPrefWidth(80);
        editor.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        getChildren().addAll(backgroundCircle, label, editor);
        setPickOnBounds(false);

        setOnMousePressed(e -> {
            mouseOffsetX = e.getSceneX() - getLayoutX();
            mouseOffsetY = e.getSceneY() - getLayoutY();
            if (e.isSecondaryButtonDown()) showContextMenu(e.getScreenX(), e.getScreenY());
            e.consume();
        });

        setOnMouseDragged(e -> {
            double newX = e.getSceneX() - mouseOffsetX;
            double newY = e.getSceneY() - mouseOffsetY;
            relocate(newX, newY);
            e.consume();
        });

        setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && !editor.isVisible()) {
                startEditing();
                e.consume();
            }
        });

        editor.setOnAction(e -> commitEditing());
        editor.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) commitEditing();
        });
    }

    public void setDeleteHandler(Runnable handler) {
        this.deleteHandler = handler;
    }

    public void setCreateChildHandler(Runnable handler) {
        this.createChildHandler = handler;
    }

    public void setTextCommitHandler(Runnable handler) {
        this.textCommitHandler = handler;
    }

    private void showContextMenu(double x, double y) {
        ContextMenu menu = new ContextMenu();

        MenuItem rename = new MenuItem("Rename");
        rename.setOnAction(e -> startEditing());

        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(e -> fireDelete());

        MenuItem addChild = new MenuItem("Add Child");
        addChild.setOnAction(e -> {
            if (createChildHandler != null) createChildHandler.run();
        });

        MenuItem recolor = new MenuItem("Change Color");
        recolor.setOnAction(e -> backgroundCircle.setFill(ColorUtils.nextPastelColor()));

        menu.getItems().addAll(rename, addChild, recolor, delete);

        menu.show(this, x, y);
    }

    private void startEditing() {
        editor.setText(label.getText());
        label.setVisible(false);
        editor.setVisible(true);
        editor.requestFocus();
        editor.selectAll();
    }

    private void commitEditing() {
        label.setText(editor.getText());
        editor.setVisible(false);
        label.setVisible(true);
        if (textCommitHandler != null) textCommitHandler.run();
    }

    public void fireDelete() {
        if (deleteHandler != null) deleteHandler.run();
    }

    public String getText() {
        return label.getText();
    }

    public void setPosition(double x, double y) {
        setLayoutX(x - 40);
        setLayoutY(y - 40);
    }

    public void setSelected(boolean selected) {
        if (selected) {
            backgroundCircle.setStroke(Color.WHITE);
            backgroundCircle.setStrokeWidth(3);
        } else {
            backgroundCircle.setStrokeWidth(0);
        }
    }
}
