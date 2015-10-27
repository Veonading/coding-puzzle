package com.codingpuzzle.parallel;

import java.util.List;
import java.util.concurrent.RecursiveAction;

import com.codingpuzzle.core.LegitimException;
import com.codingpuzzle.core.Legitimus;
/**
 * Fragment the multi tasks recursively into halves, until 
 * each fragment has only one task, then compute the legitimacy
 * on the single task.
 * 
 * @author Wei Ding
 *
 */
@SuppressWarnings("serial")
public class LegitAction extends RecursiveAction {

    private final List<LegitTask> legitTasks;

    public LegitAction(final List<LegitTask> legitTasks) {
        this.legitTasks = legitTasks;
    }

    @Override
    protected void compute() {
        if (this.legitTasks.size() > 1) {
            parallelize();
        } else {
        	sequential();        	
        }
    }

    private void parallelize() {
        final int size = this.legitTasks.size();
        final int midIndex = size / 2;

        final List<LegitTask> parserTasksOne = this.legitTasks.subList(0, midIndex);
        final List<LegitTask> parserTaskTwo = this.legitTasks.subList(midIndex, size);

        final LegitAction parserActionOne = new LegitAction(parserTasksOne);
        final LegitAction parserActionTwo = new LegitAction(parserTaskTwo);

        invokeAll(parserActionOne, parserActionTwo);
    }
    
    private void sequential(){
    	LegitTask legitTask = this.legitTasks.get(0);
        final Legitimus legitimus = new Legitimus(legitTask.getInputString());
        try {
            legitimus.legitim();
            legitTask.setLegitim(true);
        } catch (LegitimException e) {
            legitTask.setLegitim(false);
        }
    }
}