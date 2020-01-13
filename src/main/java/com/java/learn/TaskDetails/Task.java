package com.java.learn.TaskDetails;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Task {
    private String id;
    private String title;
    private TaskType type;
    private LocalDate createdOn;
    private boolean done=false;
    private Set<String> tags=new HashSet<String>();
    private LocalDate dueOn;

    public Task(String title, TaskType type, LocalDate createdOn) {
        this.id=String.valueOf(Math.random());
        this.title = title;
        this.type = type;
        this.createdOn = createdOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Set getTags() {
        return tags;
    }

    public void setTags(Set tags) {
        this.tags = tags;
    }

    public LocalDate getDueOn() {
        return dueOn;
    }

    public void setDueOn(LocalDate dueOn) {
        this.dueOn = dueOn;
    }

    public Task addTag(String tag) {
        this.tags.add(tag);
        return this;
    }
}
