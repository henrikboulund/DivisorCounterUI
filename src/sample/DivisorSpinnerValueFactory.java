package sample;

import javafx.scene.control.SpinnerValueFactory;

public class DivisorSpinnerValueFactory extends SpinnerValueFactory {

    public DivisorSpinnerValueFactory(int value) {
        this.setValue(value);
    }

    @Override
    public void decrement(int i) {
        i++;
    }

    @Override
    public void increment(int i) {
        i--;
    }
}
