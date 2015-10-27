package com.codingpuzzle.parallel;

/**
 * Assign an index for each input sting, set and read it's legitimacy,
 * and print out
 *
 * @author Wei Ding
 *
 */

public class LegitTask {

    private final int taskNumber;

    private final String inputString;

    private boolean legitim;

    public LegitTask(final int index, final String inputString) {
        this.taskNumber = index+1; 
        this.inputString = inputString;
    }

    public String getInputString() {
        return this.inputString;
    }

    public void setLegitim(final boolean isLegitim) {
        this.legitim = isLegitim;
    }

    private String isLegitim() {
        return this.legitim ? "true" : 
        	"false";
    }

    public String toString() {
        return String.valueOf(this.taskNumber) + ":" + isLegitim();
    }
}
