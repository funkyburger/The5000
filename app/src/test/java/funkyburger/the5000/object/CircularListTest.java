package funkyburger.the5000.object;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import funkyburger.the5000.combination.CombinationMatcher;

public class CircularListTest {
    @Test
    public void doesLoopBack() {
        CircularList<String> list = new CircularList<>(Arrays.asList("toto", "titi", "tata", "tutu"));

        assertTrue(list.getCurrent().equals("toto"));
        assertTrue(list.next().equals("titi"));
        assertTrue(list.next().equals("tata"));
        assertTrue(list.next().equals("tutu"));
        assertTrue(list.next().equals("toto"));
    }

    @Test
    public void gettingCurrentFromEmptyThrowsException() {
        CircularList<String> list = new CircularList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> list.getCurrent());
    }

    @Test
    public void gettingNextFromEmptyThrowsException() {
        CircularList<String> list = new CircularList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> list.next());
    }
}