import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringRepeat {
    private static final String EXPECTED_STRING = "aaaaaaa";
    private static final int N = 7;

    @Test
    void usingJDK11StringRepeat() {
        String newString = "a".repeat(N);
        assertEquals(EXPECTED_STRING, newString);
    }

    @Test
    void usingStringBuilder() {
        StringBuilder builder = new StringBuilder(N);
        for (int i = 0; i < N; i++) {
            builder.append("a");
        }

        String newString = builder.toString();
        assertEquals(EXPECTED_STRING, newString);
    }

    @Test
    void usingArray() {
        char[] charArray = new char[N];
        for (int i = 0; i < N; i++) {
            charArray[i] = 'a';
        }
        String newString = new String(charArray);
        assertEquals(EXPECTED_STRING, newString);
    }

    @Test
    void usingArraysFill() {
        char charToAppend = 'a';
        char[] charArray = new char[N];
        Arrays.fill(charArray, charToAppend);
        String newString = new String(charArray);
        assertEquals(EXPECTED_STRING, newString);
    }

    @Test
    void using_nCopies() {
        String charToAppend = "a";
        String newString = String.join("", Collections.nCopies(N, charToAppend));
        assertEquals(EXPECTED_STRING, newString);
    }

    @Test
    void usingStreamGenerateMethod() {
        String charToAppend = "a";
        String newString = Stream.generate(() -> charToAppend)
                .limit(N)
                .collect(Collectors.joining());
        assertEquals(EXPECTED_STRING, newString);
    }
}
