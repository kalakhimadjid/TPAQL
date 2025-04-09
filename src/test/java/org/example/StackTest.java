package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
Stack S=new Stack();
    @Test
    void push() {
        S.push(5);
        assertFalse(S.isEmpty());
        assertEquals(5,S.peek());
    }

    @Test
    void pop() {
        S.push(5);
        assertEquals(5,S.peek());
        S.pop();
        assertTrue(S.isEmpty());
    }

    @Test
    void peek() {
        S.push(5);
        int p= S.peek();
        System.out.print(p);
        assertEquals(p,S.peek());
    }

    @Test
    void isEmpty() {
        assertTrue(S.isEmpty());
        assertEquals(0,S.size());
    }

    @Test
    void size() {
        S.push(1);
        S.push(3);
        S.push(2);
        assertEquals(3,S.size());
    }
}
