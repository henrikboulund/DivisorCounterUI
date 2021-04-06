package sample;

import java.util.Vector;

public class DivisorCounter {
    private final static Vector<Result> results = new Vector<>();

    public Result calculate(int minimum, int maximum) {
        Result result = new Result(0, 0);
        for(int i = minimum; i <= maximum; i++) {
            int counter = 0;
            for(int j = 1; j<=i; j++) {
                if(i % j == 0) {
                    counter++;
                }
            }
            if(counter > result.getDivisorCounter()) {
                result = new Result(i, counter);
            }
        }

        results.add(result);
        return result;
    }
}
