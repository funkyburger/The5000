package funkyburger.the5000.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationGenerator {
    public static List<Combination> generate() {
        return Arrays.asList(new Combination(Arrays.asList(1), 100),
                new Combination(Arrays.asList(5), 50),
                new Combination(Arrays.asList(1, 1, 1), 1000),
                new Combination(Arrays.asList(2, 2, 2), 200),
                new Combination(Arrays.asList(3, 3, 3), 300),
                new Combination(Arrays.asList(4, 4, 4), 400),
                new Combination(Arrays.asList(5, 5, 5), 500),
                new Combination(Arrays.asList(6, 6, 6), 600),
                new Combination(Arrays.asList(1, 2, 3, 4, 5, 6), 6000));
    }
}
