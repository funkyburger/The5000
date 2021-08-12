package funkyburger.the5000.utils;

import java.util.Random;

public final class RandomUtil {
    private static final Random random = new Random();

    private RandomUtil() {};

    public static int getRandomDiceValue()
    {
        return Math.abs(random.nextInt() % 6) + 1;
    }
}
