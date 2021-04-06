package sample.logic;

import javafx.concurrent.Task;

public class DivisorCounter extends Task<Result> {

    private int minimum;
    private int maximum;

    @Override
    protected Result call() throws Exception {
        Result result = new Result(0, 0);
        for(int i = getMinimum(); i <= getMaximum(); i++) {

            this.updateMessage("Counting divisors for " + i);

            int counter = 0;
            for(int j = 1; j<=i; j++) {
                if(i % j == 0) {
                    counter++;
                }
            }
            if(counter > result.getDivisorCounter()) {
                result = new Result(i, counter);
            }

            this.updateProgress(i, getMaximum());
        }

        return result;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }
}
