package com.java.learn.TaskDetails;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
/*
* Optional.ofNullable(T value)-This static factory method works for both null and non null values.
* For null value, it will create an empty Optional.
* For non null value, it will create an Optional using the value.
* */

public class TaskRepository {
    private static final Map<String,TaskDetails> TASK_STORE=new ConcurrentHashMap<String, TaskDetails>();

    public Optional<TaskDetails> find(String taskId){
    return Optional.ofNullable(TASK_STORE.get(taskId));
    }

    public void add(TaskDetails taskDetails){
        TASK_STORE.put(taskDetails.getId(),taskDetails);
    }
}
