package sample.logic;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class DivisorCounter extends Task<Result> {

    private Label updateLabel;
    private Label resultLabel;
    private ProgressBar progressBar;

    private int minimum;
    private int maximum;

    @Override
    protected Result call() throws Exception {
        Result result = new Result(0, 0);
        for(int i = getMinimum(); i <= getMaximum(); i++) {

            int finalI = i;
            Platform.runLater(() -> getUpdateLabel().setText("Counting divisors for " + finalI));

            int counter = 0;
            for(int j = 1; j<=i; j++) {
                if(i % j == 0) {
                    counter++;
                }
            }
            if(counter > result.getDivisorCounter()) {
                result = new Result(i, counter);
            }

            double finalI1 = i;
            Platform.runLater(() -> getProgressBar().setProgress(finalI1 / getMaximum()));
        }

        Result finalResult = result;
        Platform.runLater(() -> getResultLabel().setText("The number " + finalResult.getNumber() + " has " + finalResult.getDivisorCounter() + " divisors!"));
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

    public Label getUpdateLabel() {
        return updateLabel;
    }

    public void setUpdateLabel(Label updateLabel) {
        this.updateLabel = updateLabel;
    }

    public Label getResultLabel() {
        return resultLabel;
    }

    public void setResultLabel(Label resultLabel) {
        this.resultLabel = resultLabel;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}
