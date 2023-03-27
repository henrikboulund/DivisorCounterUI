package sample.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.logic.DivisorCounter;
import sample.logic.Result;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Controller {

    private DivisorCounter counter;
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

        counter = new DivisorCounter();
        counter.setMinimum(minimum);
        counter.setMaximum(maximum);
        counter.setOnRunning(e -> {
            startButton.setDisable(true);
            stopButton.setDisable(false);
            txtMinimum.setDisable(true);
            txtMaximum.setDisable(true);
        });

        counter.messageProperty().addListener((obs, o, n) -> {
            progressLabel.setText(n);
        });

        counter.progressProperty().addListener((obs, o, n) -> {
            progressBar.setProgress((double)n);
        });

        counter.valueProperty().addListener((obs, o, n) -> {
            resultLabel.setText("The number " + n.getNumber() + " has " + n.getDivisorCounter() + " divisors!");
        });

        counter.setOnCancelled(e -> {
            resetUI();
        });

        counter.setOnSucceeded(e -> {
            resetUI();
        });

        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(counter);
        es.shutdown();
    }

    @FXML
    public void stop()
    {
        counter.cancel(true);
    }

    private void resetUI(){
        startButton.setDisable(false);
        stopButton.setDisable(true);
        txtMinimum.setDisable(false);
        txtMaximum.setDisable(false);
    }
}

