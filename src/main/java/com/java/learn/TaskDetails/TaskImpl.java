package com.java.learn.TaskDetails;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class TaskImpl {
    public static void main(String[] args) {
        List<Task> tasks = TaskConstruct.getTasks();
//        List<Task> readingTasks=tasks.stream().filter(task->task.getType()==TaskType.READING).collect(Collectors.toList());
//        readingTasks.forEach(task-> System.out.println(task.getTitle()+task.getTags()));

        //Example 1 — Find all the reading task titles sorted by their creation date
        System.out.println("---------All the reading task titles sorted by their creation date-----------");
        List readingTaskTitles=findAllReadingTaskTitleSortedByCreatedOn(tasks);
        readingTaskTitles.forEach(System.out::println);

        System.out.println("---------All the reading task titles sorted by their creation date in reverse order-----------");
        List allReadingTaskTitleSortedByCreatedOnInReversed = findAllReadingTaskTitleSortedByCreatedOnInReversed(tasks);
        allReadingTaskTitleSortedByCreatedOnInReversed.forEach(System.out::println);

        //Example 2 — Finding distinct tasks
        System.out.println("------------Finding distinct Reading tasks titles----------------------");
        List<String> distinctTaskTitles=findDistinctReadingTaskTitles(tasks);
        distinctTaskTitles.forEach(System.out::println);

        //Example 3 — Find top 2 reading tasks sorted by creation date
        System.out.println("------------Finding top 2 reading tasks sorted by creation date----------------------");
        List<String> taskTitlesofSize2=findTaskTitlesOfSize2(tasks);
        taskTitlesofSize2.forEach(System.out::println);

        //Use of limit along with skip for pagination
        System.out.println("------------Use of limit along with skip for pagination----------------------");
        List<String> secondndPageTitles=findtitlesofpage2(tasks,1);
        secondndPageTitles.forEach(System.out::println);

        //Example 4: Count all reading tasks
        System.out.println("-------------Count all reading tasks--------------");
        long countTaks=countAllReadingTasks(tasks);
        System.out.println(countTaks);

        //Example 5: Find all the unique tags from all tasks
        System.out.println("----------All the unique tags from all tasks---------------");
        List uniqueTags=findAllTheUniqueTags(tasks);
        System.out.println(uniqueTags);

        //Example 6 — Check if all reading tasks have tag books
        System.out.println("------Check if all reading tasks have tag books-----------");
        boolean ifAllReadingTaskhasBooks=isAllReadingTasksWithTagBooks(tasks);
        System.out.println(ifAllReadingTaskhasBooks);
        boolean isAny=isAnyReadingTaskWithTagJava8(tasks);
        System.out.println(isAny);

        //Example 7 — Creating a summary of all titles
        System.out.println("-----Creating a summary of all titles----");
        String titles= joinAllTaskTitles(tasks);
        System.out.println(titles);
        //Another example to demonstrate reduce
        System.out.println("----------find the sum of squares of all numbers from given a list of numbers. -----------");
        List<Integer> numbers=Arrays.asList(1,2,3,4);
        Integer result=numbers.stream()
                .map(number->number*number)
                .reduce(0,(acc,el)->acc+el);
        System.out.println(result);

        //Example 8 – Working with primitive Streams
        IntStream.range(1,10).forEach(System.out::print);
        System.out.println();
        IntStream.rangeClosed(1,10).forEach(System.out::print);
        System.out.println();
        //infinite stream
        LongStream infiniteStream=LongStream.iterate(1,el->el+1);
        //infiniteStream.limit(50).forEach(System.out::print);
        infiniteStream.limit(10).map(num->num*2).filter(num->num%3==0).forEach(System.out::print);
        System.out.println();
        //Creating Streams from Arrays
        String[] languages={"Java8","python","ruby","Pearl"};
        Arrays.stream(languages).map(String::toUpperCase).forEach(System.out::print);
        System.out.println();
        Arrays.stream(languages,1,3).forEach(System.out::print);
        //partition on the basis of title contains mobile.
        Map<Boolean, List<String>> mobileContainingTitles = tasks.stream().map(Task::getTitle).collect(Collectors.partitioningBy(title -> title.contains("mobile")));
        System.out.println(mobileContainingTitles);
    }



    private static String joinAllTaskTitles(List<Task> tasks) {
       return tasks.stream()
                .map(Task::getTitle)
                .reduce((first,second)->first+"*****"+second)
                .get();
    }

    private static boolean isAnyReadingTaskWithTagJava8(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> task.getType()==TaskType.READING)
                .anyMatch(task -> task.getTags().contains("java8"));
    }

    private static boolean isAllReadingTasksWithTagBooks(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> task.getType()==TaskType.READING)
                .allMatch(task -> task.getTags().contains("git"));
    }

    private static List findAllTheUniqueTags(List<Task> tasks) {
        return (List) tasks
                .stream()
                .flatMap(task -> task.getTags().stream())
                /*In short, we can say that if there is a Stream of List of <<Data Type>> before flattening,
                then on applying flatMap(), Stream of <<Data Type>> is returned after flattening.
                Output: [git, books, reading, java8, coding, mobile, blogging, writing, streams, ddd] */

                //.map(task->task.getTags())
                /*map operation Output: [[git, books, reading], [books, java8, reading], [coding, mobile], [blogging, writing, streams], [books, ddd, reading]]*/
                .distinct()
                .collect(Collectors.toList());
    }

    private static long countAllReadingTasks(List<Task> tasks) {
        return tasks.stream().filter(task -> task.getType()==TaskType.READING).count();
    }

    private static List<String> findtitlesofpage2(List<Task> tasks, int pageNum) {
        return tasks.stream().filter(task -> task.getType()==TaskType.READING)
                    .sorted(Comparator.comparing(Task::getCreatedOn))
                    .map(Task::getTitle)
                    .skip(2*pageNum)
                    .limit(2)
                    .collect(Collectors.toList());
    }

    private static List<String> findTaskTitlesOfSize2(List<Task> tasks) {
       return tasks.stream()
                .filter(task -> task.getType()==TaskType.READING)
                .sorted(Comparator.comparing(Task::getCreatedOn))
                .map(Task::getTitle)
                .distinct()
                .limit(2)//The limit function can be used to limit the result set to a given size.limit is a short circuiting operation which means it does not evaluate all the elements to find the result.
                .collect(Collectors.toList());

    }

    private static List<String> findDistinctReadingTaskTitles(List<Task> tasks) {
        return tasks.stream()
                .filter(task->task.getType()==TaskType.READING)
                .sorted(Comparator.comparing(Task::getCreatedOn))
                .map(Task::getTitle)
                .distinct()//remove the duplicates and get only distinct elements by using the distinct method.It uses the Object’s equals method for determining the object equality.
                .collect(Collectors.toList());
    }

    private static List findAllReadingTaskTitleSortedByCreatedOn(List<Task> tasks) {
//        List readingTaskTitles= tasks.stream().filter(task->task.getType()== TaskType.READING)
//                .sorted((t1, t2)->t1.getCreatedOn().compareTo(t2.getCreatedOn()))
//                .map(task->task.getTitle())
//                .collect(Collectors.toList());
        List readingTaskTitles= tasks.stream().filter(task->task.getType()==TaskType.READING)
                        .sorted(Comparator.comparing(Task::getCreatedOn))
                        .map(Task::getTitle)
                        .collect(Collectors.toList());
        return readingTaskTitles;
    }

    private static List findAllReadingTaskTitleSortedByCreatedOnInReversed(List<Task> tasks) {
        List readingTaskTitles= tasks.stream().filter(task->task.getType()==TaskType.READING)
                .sorted(Comparator.comparing(Task::getCreatedOn).reversed())
                .map(Task::getTitle)
                .collect(Collectors.toList());
        return readingTaskTitles;
    }

}
