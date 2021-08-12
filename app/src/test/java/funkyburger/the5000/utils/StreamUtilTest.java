package funkyburger.the5000.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.stream.Stream;

public class StreamUtilTest {

    @Test
    public void areEqual() {
        Stream<Integer> s1 = Stream.of(1, 2, 3);
        Stream<Integer> s2 = Stream.of(1, 2, 3);

        assertTrue(StreamUtil.areEqual(s1, s2));
    }

    @Test
    public void areNotEqual() {
        Stream<Integer> s1 = Stream.of(1, 2, 3);
        Stream<Integer> s2 = Stream.of(1, 5, 3);

        assertFalse(StreamUtil.areEqual(s1, s2));
    }

    @Test
    public void areNotEqualIfTooShort() {
        Stream<Integer> s1 = Stream.of(1, 2, 3, 4);
        Stream<Integer> s2 = Stream.of(1, 2, 3);

        assertFalse(StreamUtil.areEqual(s1, s2));
    }

    @Test
    public void areNotEqualIfTooLong() {
        Stream<Integer> s1 = Stream.of(1, 2, 3);
        Stream<Integer> s2 = Stream.of(1, 2, 3, 4);

        assertFalse(StreamUtil.areEqual(s1, s2));
    }

    @Test
    public void areNotEqualIfNotSameType() {
        Stream<Integer> s1 = Stream.of(1, 2, 3);
        Stream<Double> s2 = Stream.of(1.0, 2.0, 3.0);

        assertFalse(StreamUtil.areEqual(s1, s2));
    }
}