package com.tasktree.model;

public class Theme {

    private String name;
    private String backgroundColor;
    private String nodeColor;
    private String connectionColor;

    public Theme(String name, String backgroundColor, String nodeColor, String connectionColor) {
        this.name = name;
        this.backgroundColor = backgroundColor;
        this.nodeColor = nodeColor;
        this.connectionColor = connectionColor;
    }

    public String getName() { return name; }

    public String getBackgroundColor() { return backgroundColor; }

    public String getNodeColor() { return nodeColor; }

    public String getConnectionColor() { return connectionColor; }
}
