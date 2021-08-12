package funkyburger.the5000.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RandomUtilTests {
    @Test
    public void randomValuesAreInRange() {
        for (int i = 0; i < 10000; i++) {
            int value = RandomUtil.getRandomDiceValue();
            assertTrue("Value was 0 or negative (" + value + ").",value > 0);
            assertTrue("Value was above 6 (\" + value + \").",value <= 6);
        }
    }

    @Test
    public void randomValuesAreEquilibrated() {
        int poolSize = 10000;
        double sum = 0;
        for (int i = 0; i < poolSize; i++) {
            sum += RandomUtil.getRandomDiceValue();
        }

        sum /= poolSize;

        assertTrue("Value was under 3 (" + sum + ").",sum >= 3);
        assertTrue("Value was above 4 (" + sum + ").",sum <= 4);
    }
}
