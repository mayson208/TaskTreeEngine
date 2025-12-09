package com.tasktree.model;

public class Connection {

    private final String fromId;
    private final String toId;

    public Connection(String fromId, String toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public String getToId() {
        return toId;
    }
}
