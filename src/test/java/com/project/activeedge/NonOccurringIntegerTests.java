package com.project.activeedge;

import com.project.activeedge.task.NonOccurringInteger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NonOccurringIntegerTests {
    @Test
    void performTest() {
        assertEquals(NonOccurringInteger.getInteger(new int[] {1, 3, 6, 4, 1, 2}), 5);
        assertEquals(NonOccurringInteger.getInteger(new int[] {5, -1, -3}), 1);
    }
}
