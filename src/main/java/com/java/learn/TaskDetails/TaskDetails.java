package com.java.learn.TaskDetails;

import java.util.Optional;
import java.util.UUID;

public class TaskDetails {
    private final String id;
    private final String title;
    private final TaskType type;
    private Optional<User> assignedTo;

    public TaskDetails(String title,TaskType type){
        this.id= UUID.randomUUID().toString();
        this.title=title;
        this.type=type;
        this.assignedTo=Optional.empty();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TaskType getType() {
        return type;
    }

    public Optional<User> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = Optional.of(assignedTo);
    }
}
