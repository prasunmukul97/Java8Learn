package com.java.learn.TaskDetails;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.summarizingInt;

public class CollectImpl {
    public static void main(String[] args) {
        List<Task> tasks = TaskConstruct.getTasks();
        // group tasks by their type.
        Map<TaskType,List<Task>> taskByType= groupTaskByType(tasks);
        taskByType.forEach((k,v)-> {
            System.out.print(k+":");
            v.stream().forEach(task -> System.out.print(task.getTitle()+","));
            System.out.println();
        });

        //Collecting data into containers
        System.out.println("*******Collecting data into containers***********");
        List<String> taskTitles=collectAllTaskTitles(tasks);
        taskTitles.forEach(System.out::println);
        //Collecting data into a Set
        System.out.println("*******Collecting data into a Set***********");
        Set<String> uniqueTitles=collectAllTaskTitlesInSet(tasks);
        uniqueTitles.forEach(System.out::println);
        //Collecting data into a Map
        System.out.println("**********Collecting data into a Map***********");
        Map<String,Task> taskMap=getTaskMap(tasks);
        taskMap.forEach((k,v)->{
            System.out.println(k+":");
            System.out.println(v.getTags()+"-----------"+v.getType());
        });

        //using other collections
        System.out.println("**********using other collections***********");
        LinkedHashSet<Task> collection=collectToLinkedHaskSet(tasks);
        collection.forEach(task -> System.out.println(task.getTitle()));
        
        //Grouping task by tags
        System.out.println("**************Grouping by tags****************");
        Map<String, List<Task>> taskByTags=groupTasksByTags(tasks);
        taskByTags.forEach((k,v)-> {
            System.out.println("Tag Name:" + k + "-");
            v.stream().map(task -> task.getTitle()).distinct().forEach(System.out::println);
        });

        //Group task by tag and count
       Map<String,Long> taskbyTagCount= tagsAndCount(tasks);
        taskbyTagCount.forEach((k,v)-> {
            System.out.println(k+":"+v);
        });

        //Grouping by TaskType and createdOn
        System.out.println("***********Grouping by TaskType and createdOn***********");
        /*output: {WRITING={2015-07-04=[com.java.learn.TaskDetails.Task@70177ecd]},
                     READING={2015-07-05=[com.java.learn.TaskDetails.Task@1e80bfe8], 2015-07-02=[com.java.learn.TaskDetails.Task@1b2c6ec2], 2015-07-01=[com.java.learn.TaskDetails.Task@30dae81, com.java.learn.TaskDetails.Task@66a29884]},
                        CODING={2015-07-03=[com.java.learn.TaskDetails.Task@4edde6e5]}} */
        Map<TaskType, Map<LocalDate, List<Task>>> tasksbyTaskTypeCreatedOn= groupTasksByTypeAndCreationDate(tasks);
        System.out.println(tasksbyTaskTypeCreatedOn);

        //partition tasks into two groups by defining a partitioning function that partition tasks into two groups
        System.out.println("*****partitioning Task by creation date************");
        /*
        * output:
        * { false=[com.java.learn.TaskDetails.Task@30dae81, com.java.learn.TaskDetails.Task@1b2c6ec2, com.java.learn.TaskDetails.Task@4edde6e5, com.java.learn.TaskDetails.Task@70177ecd, com.java.learn.TaskDetails.Task@1e80bfe8, com.java.learn.TaskDetails.Task@66a29884]
        * , true=[]}
        * */
        Map<Boolean,List<Task>> oldAndNewTask= partitionOldAndFutureTasks(tasks);
        System.out.println(oldAndNewTask);

        //Joining all titles
        System.out.println("*************Joining all titles************");
        String joinedTitles = tasks.stream().map(Task::getTitle).collect(Collectors.joining(","));
        System.out.println(joinedTitles);

        System.out.println("*****************Summary Statistics******************");

        IntSummaryStatistics summaryStatistics = tasks.stream().map(Task::getTitle).collect(summarizingInt(String::length));
        System.out.println(summaryStatistics.getAverage()); //32.4
        System.out.println(summaryStatistics.getCount()); //5
        System.out.println(summaryStatistics.getMax()); //44
        System.out.println(summaryStatistics.getMin()); //24
        System.out.println(summaryStatistics.getSum()); //162

        List<TaskTag> collect = tasks.stream().flatMap(task -> ((Set<String>) task.getTags()).stream().map(tag -> new TaskTag(tag, task)))
                .collect(Collectors.toList());
        System.out.println(collect);

    }

    private static Map<Boolean,List<Task>> partitionOldAndFutureTasks(List<Task> tasks) {
        return tasks.stream().collect(Collectors.partitioningBy(task -> task.getCreatedOn().isAfter(LocalDate.now())));
    }

    private static Map<TaskType, Map<LocalDate, List<Task>>> groupTasksByTypeAndCreationDate(List<Task> tasks) {
        return tasks.stream().collect(Collectors.groupingBy(Task::getType, Collectors.groupingBy(Task::getCreatedOn)));
   }

    private static  Map<String,Long> tagsAndCount(List<Task> tasks) {
       return tasks.stream().flatMap(task -> ((Set<String>)task.getTags()).stream()
                .map(tag->new TaskTag(tag,task)))
                .collect(Collectors.groupingBy(TaskTag::getTag,Collectors.counting()));
    }

    private static Map<String, List<Task>> groupTasksByTags(List<Task> tasks) {
        //To accumulate list of task for each tag:
       return  tasks.stream().flatMap(task -> ((Set<String>)task.getTags()).stream().map(tag->new TaskTag(tag,task)))
                        .collect(Collectors.groupingBy(TaskTag::getTag,Collectors.mapping(TaskTag::getTask,Collectors.toList())));
       /*
       @param classifier a classifier function mapping input elements to keys
     * @param downstream a {@code Collector} implementing the downstream reduction
       *  public static <T, K, A, D>
            Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier,
                                          Collector<? super T, A, D> downstream) {
        return groupingBy(classifier, HashMap::new, downstream);
    }
       * */

    }

    private static LinkedHashSet collectToLinkedHaskSet(List<Task> tasks) {
        return tasks.stream().collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static Map<String, Task> getTaskMap(List<Task> tasks) {
        //toMap collector method converts streams to map.
        //toMap collector takes two mapper function to extract key and values for Map.
       // return tasks.stream().collect(Collectors.toMap(Task::getTitle,task -> task));
       // return tasks.stream().collect(Collectors.toMap(Task::getTitle,identity()));
        /*
        * The code to create a Map from the stream will throw an exception when duplicate keys are present.
        * The above implemented codes gives below exception:
            Exception in thread "main" java.lang.IllegalStateException: Duplicate key com.java.learn.TaskDetails.Task@4fca772d
        * */
        return tasks.stream().collect(Collectors.toMap(Task::getTitle,identity(),(e1,e2)->e2));
        /*
         * You can handle duplicates by using another variant of the toMap function which allows us to specify a merge function.
         * The merge function allows a client to specify how they want to resolve collisions between values associated with the same key.
         * here merge function is (e1,e2)->e2
         * */


    }

    private static Set<String> collectAllTaskTitlesInSet(List<Task> tasks) {
        return tasks.stream().map(Task::getTitle).collect(Collectors.toSet());
    }

    private static List<String> collectAllTaskTitles(List<Task> tasks) {
        return tasks.stream().map(Task::getTitle).collect(Collectors.toList());
    }

    private static Map<TaskType,List<Task>> groupTaskByType(List<Task> tasks) {
        //It creates a Map with key as the TaskType and value as the list containing all the tasks which have same TaskType.
        return  tasks.stream().collect(Collectors.groupingBy(Task::getType));
       /* Java 7 implementation of above logic.
        Map<TaskType,List<Task>> mapTask=new HashMap<>();
        for(Task task:tasks){
            if(!mapTask.containsKey(task.getType())){
                List<Task> taskList=new ArrayList<>();
                taskList.add(task);
                mapTask.put(task.getType(),taskList);
            }else{
                mapTask.get(task.getType()).add(task);
            }

        }

        return mapTask; */
    }

    /*
*  @apiNote
 * The {@code mapping()} collectors are most useful when used in a
 * multi-level reduction, such as downstream of a {@code groupingBy} or
 * {@code partitioningBy}.  For example, given a stream of
 * {@code Person}, to accumulate the set of last names in each city:
 * <pre>{@code
 *     Map<City, Set<String>> lastNamesByCity
 *         = people.stream().collect(groupingBy(Person::getCity,
 *                                              mapping(Person::getLastName, toSet())));
 * }</pre>
 *
 * @param <T> the type of the input elements
 * @param <U> type of elements accepted by downstream collector
 * @param <A> intermediate accumulation type of the downstream collector
 * @param <R> result type of collector
 * @param mapper a function to be applied to the input elements
 * @param downstream a collector which will accept mapped values
 * @return a collector which applies the mapping function to the input
 * elements and provides the mapped results to the downstream collector

public static <T, U, A, R>
Collector<T, ?, R> mapping(Function<? super T, ? extends U> mapper,
                           Collector<? super U, A, R> downstream) {
    BiConsumer<A, ? super U> downstreamAccumulator = downstream.accumulator();
    return new CollectorImpl<>(downstream.supplier(),
            (r, t) -> downstreamAccumulator.accept(r, mapper.apply(t)),
            downstream.combiner(), downstream.finisher(),
            downstream.characteristics());
}

*/
}


