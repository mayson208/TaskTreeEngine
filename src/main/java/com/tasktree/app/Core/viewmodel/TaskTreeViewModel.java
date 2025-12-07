package com.tasktree.viewmodel;

import com.tasktree.model.TaskGraph;
import com.tasktree.model.TaskNode;

public class TaskTreeViewModel {

    private TaskGraph graph = new TaskGraph();

    public TaskNode createNode(String label, double x, double y) {
        TaskNode node = new TaskNode(label);
        node.setPosition(x, y);
        graph.addNode(node);
        return node;
    }

    public void deleteNode(TaskNode node) {
        graph.removeNode(node);
    }

    public void connect(TaskNode parent, TaskNode child) {
        graph.connect(parent, child);
    }

    public TaskGraph getGraph() {
        return graph;
    }
}
