package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {
    int a=-1;
    int b=20;
    int c=0;
    @Test
    void fibonacci() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Fibonacci.fibonacci(-1);
        });
        assertEquals("n must be positive", exception.getMessage());
        assertEquals(6765,Fibonacci.fibonacci(b));
        assertEquals(0,Fibonacci.fibonacci(c));
    }
}