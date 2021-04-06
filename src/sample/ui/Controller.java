package sample.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import sample.logic.DivisorCounter;
import sample.logic.Result;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Label resultLabel;

    @FXML
    private Spinner<Integer> minimumSpinner;

    @FXML
    private Spinner<Integer> maximumSpinner;

    @FXML
    private ProgressBar progressBar;

    @FXML
    public void initialize() {
        progressBar.setProgress(0.5);
        resultLabel.setText("");

        startButton.setDisable(false);
        stopButton.setDisable(true);

        minimumSpinner.setValueFactory(new DivisorSpinnerValueFactory(1));
        maximumSpinner.setValueFactory(new DivisorSpinnerValueFactory(100000));
    }

    @FXML
    public void start() {
        Integer minimum = minimumSpinner.getValue();
        Integer maximum = maximumSpinner.getValue();

        progressBar.setProgress(0);
        startButton.setDisable(true);
        stopButton.setDisable(false);
        resultLabel.setText("");

        DivisorCounter counter = new DivisorCounter();
        counter.setMinimum(minimum);
        counter.setMaximum(maximum);

        counter.messageProperty().addListener((obs, oold, nnew) -> resultLabel.setText(nnew));
        counter.progressProperty().addListener((obs, oold, nnew) -> progressBar.setProgress((double)nnew));
        counter.setOnSucceeded(e -> {
            try {
                Result result = counter.get();
                resultLabel.setText("The number " + result.getNumber() + " has " + result.getDivisorCounter() + " divisors!");
                startButton.setDisable(false);
                stopButton.setDisable(true);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }

        });

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(counter);
    }
}

