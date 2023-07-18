package xyz.ashyboxy;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class UwuifierTest {
    @Test
    void uwuifyReturnsDifferent() {
        String testStr = "Random Stuff";
        assertNotEquals(testStr, Uwuifier.uwuify(testStr), "uwuify should be modifying this string");
        System.out.println(testStr);
    }
}
