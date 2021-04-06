package sample.logic;

public class Result {
    private int number;
    private int divisorCounter;

    public Result(int number, int divisorCounter) {
        this.number = number;
        this.divisorCounter = divisorCounter;
    }

    public int getDivisorCounter() {
        return this.divisorCounter;
    }

    public int getNumber() {
        return this.number;
    }
}