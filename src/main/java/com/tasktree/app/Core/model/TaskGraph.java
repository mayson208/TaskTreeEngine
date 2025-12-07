package com.tasktree.model;

import java.util.ArrayList;
import java.util.List;

public class TaskGraph {

    private List<TaskNode> nodes = new ArrayList<>();

    public void addNode(TaskNode node) {
        nodes.add(node);
    }

    public void removeNode(TaskNode node) {
        nodes.remove(node);
    }

    public List<TaskNode> getNodes() {
        return nodes;
    }

    public void connect(TaskNode parent, TaskNode child) {
        parent.addChild(child);
    }
}
