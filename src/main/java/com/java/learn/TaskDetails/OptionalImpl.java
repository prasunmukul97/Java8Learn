package com.java.learn.TaskDetails;

public class OptionalImpl {
   static TaskRepository taskRepository;
    public static void main(String[] args) {
        OptionalImpl ref=new OptionalImpl();
        TaskDetails taskDetails1=new TaskDetails("Login Task",TaskType.CODING);
        TaskDetails taskDetails2=new TaskDetails("Assignment Task",TaskType.READING);
        TaskDetails taskDetails3=new TaskDetails("Writing summary on java 8",TaskType.CODING);
        TaskDetails taskDetails4=new TaskDetails("Practice java 8 code",TaskType.CODING);

        User usr1= new User("Ram");
        usr1.setFullname("Sita Ram");
        taskDetails1.setAssignedTo(usr1);

        User usr2= new User("Shyam");
        usr2.setFullname("Radhe Shyam");
        taskDetails2.setAssignedTo(usr2);
        taskRepository= new TaskRepository();
        taskRepository.add(taskDetails1);
        taskRepository.add(taskDetails2);
        taskRepository.add(taskDetails3);
        taskRepository.add(taskDetails4);

        //Getting Title for a task.
        String id=taskDetails3.getId();
        String title = ref.taskTitle(id);
        System.out.println(title);

        //Getting user name to assigned User.
        String userName= ref.taskAssignedTo(id);
        System.out.println(userName);
        //Filtering with Optional
        String taskId3=taskDetails3.getId();
        boolean isWritingTask = ref.taskTypeIsWriting(taskId3);
        System.out.println(isWritingTask);

    }

    private boolean taskTypeIsWriting(String taskId3) {
       return taskRepository.find(taskId3).filter(taskDetails -> taskDetails.getType()==TaskType.WRITING).isPresent();
    }

    /*
    * public <U> Optional<U> flatMap(Function<? super T,Optional<U>> mapper)
        If a value is present, apply the provided Optional-bearing mapping function to it, return that result,
        * otherwise return an empty Optional. This method is similar to map(Function), but the provided mapper
        * is one whose result is already an Optional, and if invoked,
        * flatMap does not wrap it with an additional Optional.
    * */
    private String taskAssignedTo(String id) {
      return  taskRepository.find(id)
              .flatMap(taskDetails -> taskDetails.getAssignedTo().map(user -> user.getUsername()))
              .orElse("Not Assigned");
    }

    private String taskTitle(String taskId) {
      return  taskRepository.find(taskId)
                .map(TaskDetails::getTitle)
                .orElseThrow(()->new TaskNotFoundException(String.format("No Task exist for the id '%s'",taskId)));
    }
}
