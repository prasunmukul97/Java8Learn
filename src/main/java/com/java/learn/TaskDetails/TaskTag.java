package com.java.learn.TaskDetails;

public class TaskTag {
    final String tag;
    final Task task;

    public TaskTag(String tag, Task task) {
        this.tag = tag;
        this.task = task;
    }

    public String getTag() {
        return tag;
    }

    public Task getTask() {
        return task;
    }
}
