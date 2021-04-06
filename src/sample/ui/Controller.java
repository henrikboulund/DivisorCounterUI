package sample.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.logic.DivisorCounter;
import sample.logic.Result;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    private DivisorCounter counterTask = new DivisorCounter();

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Label resultLabel;

    @FXML
    private TextField txtMinimum;

    @FXML
    private TextField txtMaximum;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressLabel;

    @FXML
    public void initialize() {
        progressBar.setProgress(0.5);
        progressLabel.setText("");
        resultLabel.setText("");

        startButton.setDisable(false);
        stopButton.setDisable(true);

        txtMinimum.setText("1");
        txtMaximum.setText("100000");

        System.out.println(Thread.currentThread().getId());
        counterTask.messageProperty().addListener((obs, oold, nnew) -> resultLabel.setText(nnew));
        counterTask.progressProperty().addListener((obs, oold, nnew) -> progressBar.setProgress((double)nnew));
        counterTask.setOnSucceeded(e -> {
            try {
                System.out.println(Thread.currentThread().getId());
                Result result = counterTask.get();
                resultLabel.setText("The number " + result.getNumber() + " has " + result.getDivisorCounter() + " divisors!");
                startButton.setDisable(false);
                stopButton.setDisable(true);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
        });
    }

    @FXML
    public void start() {
        Integer minimum = Integer.parseInt(txtMinimum.getText());
        Integer maximum = Integer.parseInt(txtMaximum.getText());

        progressBar.setProgress(0);
        startButton.setDisable(true);
        stopButton.setDisable(false);
        resultLabel.setText("");

        counterTask.setMinimum(minimum);
        counterTask.setMaximum(maximum);

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(counterTask);
    }

    @FXML
    public void stop() {
        startButton.setDisable(false);
        stopButton.setDisable(true);

        counterTask.cancel();
    }
}

