package com.codingpuzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import com.codingpuzzle.parallel.LegitAction;
import com.codingpuzzle.parallel.LegitTask;

/**
 * Decide the legitimacy of the input strings parallel, output the result
 * ForkJoinPool is used
 * @author Wei Ding 
 *
 */
public class Parallel{
    public static void main(final String[] args) {
        System.out.println("Running parallel on " + Runtime.getRuntime().availableProcessors() + " cores.");
        //final List<String> inputStrings = Arrays.asList(args);
        final List<LegitTask> legitTasks = toLegitTasks(args);

        executeParallel(legitTasks);

        printResults(legitTasks);
    }

    //for each string form a task
    private static List<LegitTask> toLegitTasks(final String[] args) {
        final List<LegitTask> legitTasks = new ArrayList<LegitTask>();
        for (int i = 0; i < args.length; i++) {
            final LegitTask legitTask = new LegitTask(i, args[i]);
            legitTasks.add(legitTask);
        }
        return legitTasks;
    }

    private static void executeParallel(final List<LegitTask> legitTasks) {
        final LegitAction legitAction = new LegitAction(legitTasks);
        final int cores = Runtime.getRuntime().availableProcessors();
        final ForkJoinPool forkJoinPool = new ForkJoinPool(cores);
        forkJoinPool.invoke(legitAction);
    }

    private static void printResults(final List<LegitTask> legitTasks) {
        for (LegitTask legitTask : legitTasks) {
            System.out.println(legitTask.toString());
        }
    }
}