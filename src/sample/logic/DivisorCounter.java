package sample.logic;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

public class DivisorCounter extends Task<Result> {

    private int minimum;
    private int maximum;

    //private Label resultLabel;

    @Override
    protected Result call() throws Exception {
        Result result = new Result(0, 0);
        for(int i = minimum; i <= maximum; i++) {
            int counter = 0;
            this.updateMessage("Counting divisors for " + i);       //UPDATES THE MESSAGE ABOUT HOW FAR WE ARE WITH THE PROGRESS.
            for(int j = 1; j<=i; j++) {
                if(this.isCancelled())
                    return null;
                if(i % j == 0) {
                    counter++;
                }
            }



            if(counter > result.getDivisorCounter()) {
                result = new Result(i, counter);
                this.updateValue(result);           //UPDATES THE VALUE WHEN THERE ARE SOME NEW TO THE VALUE.
            }

            this.updateProgress(i, getMaximum());   //UPDATES THE PROGRESS BAR ACCORDINGLY WITH THE EXACT PROGRESS.
        }

        /*
        * resultLabel.setText("The number " + finalResult.getNumber() + " has " + finalResult.getDivisorCounter() + " divisors!")
         * */

        /*Result finalResult = result;
        Platform.runLater(() ->
            resultLabel.setText("The number " + finalResult.getNumber() + " has " + finalResult.getDivisorCounter() + " divisors!")
                );*/


        return result;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
    public int getMaximum() {
        return maximum;
    }

    /*public void setLabel(Label resultLabel) {
        this.resultLabel = resultLabel;
    }
    public Label getLabel() {
        return resultLabel;
    }*/
}
