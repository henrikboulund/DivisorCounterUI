package sample.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.logic.DivisorCounter;
import sample.logic.Result;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Controller {

    private DivisorCounter counterTask;
    private ExecutorService executorService = Executors.newFixedThreadPool(1);

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
    }

    @FXML
    public void start() {
        Integer minimum = Integer.parseInt(txtMinimum.getText());
        Integer maximum = Integer.parseInt(txtMaximum.getText());

        progressBar.setProgress(0);
        startButton.setDisable(true);
        stopButton.setDisable(false);
        resultLabel.setText("");
        txtMaximum.setDisable(true);
        txtMinimum.setDisable(true);

        counterTask = new DivisorCounter();
        counterTask.setUpdateLabel(progressLabel);
        counterTask.setResultLabel(resultLabel);
        counterTask.setProgressBar(progressBar);
        counterTask.setOnSucceeded(e -> {
            startButton.setDisable(false);
            stopButton.setDisable(true);
        });
        counterTask.setMinimum(minimum);
        counterTask.setMaximum(maximum);

        executorService.submit(counterTask);
    }

    @FXML
    public void stop() throws InterruptedException {
        counterTask.cancel();

        startButton.setDisable(false);
        stopButton.setDisable(true);
        resultLabel.setText("");
        progressBar.setProgress(0);
        txtMaximum.setDisable(false);
        txtMinimum.setDisable(false);
    }
}

