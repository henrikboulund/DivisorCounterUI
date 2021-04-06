package sample.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.logic.DivisorCounter;
import sample.logic.Result;

public class Controller {

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

        DivisorCounter counter = new DivisorCounter();
        Result result = counter.calculate(minimum, maximum);
        resultLabel.setText("The number " + result.getNumber() + " has " + result.getDivisorCounter() + " divisors!");

        progressBar.setProgress(1);
        startButton.setDisable(false);
        stopButton.setDisable(true);
    }
}

