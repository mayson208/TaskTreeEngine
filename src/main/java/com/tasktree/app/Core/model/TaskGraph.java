package com.tasktree.model;

import java.util.ArrayList;
import java.util.List;

public class TaskGraph {

    private final List<TaskNode> nodes = new ArrayList<>();
    private final List<Connection> connections = new ArrayList<>();

    public void addNode(TaskNode node) {
        nodes.add(node);
    }

    public void removeNode(TaskNode node) {
        nodes.remove(node);
        connections.removeIf(c ->
                c.getFromId().equals(node.getId()) ||
                c.getToId().equals(node.getId()));
    }

    public void addConnection(String fromId, String toId) {
        connections.add(new Connection(fromId, toId));
    }

    public List<TaskNode> getNodes() {
        return nodes;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public TaskNode getNodeById(String id) {
        return nodes.stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
