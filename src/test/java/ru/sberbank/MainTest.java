package ru.sberbank;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void mainTest() throws IOException {
        Main.main(new String[]{"src/main/resources/clients.txt","src/main/resources/orders.txt", "src/main/resources/result.txt"});

        Stream<String> expectedResultStream = Files.lines(Paths.get("src/main/resources/expectedResult.txt"));
        Stream<String> resultStream  = Files.lines(Paths.get("src/main/resources/result.txt"));

        String[] expectedResultArray = expectedResultStream.toArray(String[]::new);
        String[] resultArray = resultStream.toArray(String[]::new);

        long expextedResultLength = expectedResultArray.length;
        long resultLength = resultArray.length;
        assertEquals(expextedResultLength, resultLength);

        assertTrue(Arrays.deepEquals(expectedResultArray, resultArray));
    }
}
