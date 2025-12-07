package com.tasktree.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskNode {

    private String id;
    private String label;
    private double x;
    private double y;

    private List<TaskNode> children = new ArrayList<>();

    public TaskNode(String label) {
        this.id = UUID.randomUUID().toString();
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void addChild(TaskNode child) {
        children.add(child);
    }

    public List<TaskNode> getChildren() {
        return children;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }
}
