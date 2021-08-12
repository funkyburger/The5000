package funkyburger.the5000.combination;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CombinationMatcherTest {

    @Test
    public void matchesCombination() {
        assertTrue(CombinationMatcher.hasMatch(Arrays.asList(1, 2, 3, 2, 4, 2), Arrays.asList(2, 2, 2)));
    }

    @Test
    public void matchesSame() {
        assertTrue(CombinationMatcher.hasMatch(Arrays.asList(2, 2, 2), Arrays.asList(2, 2, 2)));
    }

    @Test
    public void doesntMatchAlmostCombination() {
        assertFalse(CombinationMatcher.hasMatch(Arrays.asList(1, 2, 3, 6, 4, 2), Arrays.asList(2, 2, 2)));
    }

    @Test
    public void doesntMatchTooShortCombination() {
        assertFalse(CombinationMatcher.hasMatch(Arrays.asList(2, 2), Arrays.asList(2, 2, 2)));
    }

    @Test
    public void fetchesScoreFromMatch() {
        assertEquals(200,
            CombinationMatcher.fetchScoreFromMatch(Arrays.asList(1, 2, 3, 2, 4, 2),
                Arrays.asList(2, 2, 2),
                Arrays.asList(new Combination(Arrays.asList(2, 2, 2), 200)))
        );
    }

    @Test
    public void fetchesIfScoreIsMatched() {
        assertEquals(0,
                CombinationMatcher.fetchScoreFromMatch(Arrays.asList(1, 2, 3, 2, 4, 6),
                        Arrays.asList(2, 2, 2),
                        Arrays.asList(new Combination(Arrays.asList(2, 2, 2), 200)))
        );
    }

    @Test
    public void fetchesIfScoreIsInList() {
        assertEquals(0,
                CombinationMatcher.fetchScoreFromMatch(Arrays.asList(1, 2, 3, 2, 4, 2),
                        Arrays.asList(2, 2, 2),
                        Arrays.asList(new Combination(Arrays.asList(1, 1, 1), 1000)))
        );
    }
}