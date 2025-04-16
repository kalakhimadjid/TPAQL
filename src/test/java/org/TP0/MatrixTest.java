package org.TP0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    private Matrix A;
    private Matrix B;
    @Test
    void set() {
        A = new Matrix(2);
        A.set(0, 0, 1);
        A.set(0, 1, 2);
        A.set(1, 0, 3);
        A.set(1, 1, 4);

        B = new Matrix(2);
        B.set(0, 0, 5);
        B.set(0, 1, 6);
        B.set(1, 0, 7);
        B.set(1, 1, 8);
    }

    @Test
    void get() {
        A.set(1, 1, 42);
        assertEquals(42, A.get(1, 1));
    }

    @Test
    void add() {
        A.add(B);
        assertEquals(6, A.get(0, 0));
        assertEquals(8, A.get(0, 1));
        assertEquals(10, A.get(1, 0));
        assertEquals(12, A.get(1, 1));
        assertThrows(NullPointerException.class, () -> A.add(null));
        Matrix C = new Matrix(1);
        assertThrows(IllegalArgumentException.class, () -> A.add(C));
    }

    @Test
    void multiply() {
        assertThrows(NullPointerException.class, () -> A.multiply(null));
        Matrix D = new Matrix(3);
        assertThrows(IllegalArgumentException.class, () -> A.multiply(D));
    }

    @Test
    void transpose() {
        A.transpose();
        assertEquals(1, A.get(0, 0));
        assertEquals(3, A.get(0, 1));
        assertEquals(2, A.get(1, 0));
        assertEquals(4, A.get(1, 1));
    }

    @Test
    void testToString() {
        String S = "[1, 2]\n" + "[3, 4]\n";
        assertEquals(S, A.toString());
    }
}