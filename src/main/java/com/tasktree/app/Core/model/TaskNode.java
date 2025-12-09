package com.tasktree.model;

import java.util.UUID;

public class TaskNode {

    private String id;
    private String title;

    private double x;
    private double y;

    public TaskNode(String title, double x, double y) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
