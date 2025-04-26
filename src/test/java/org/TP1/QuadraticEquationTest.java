package org.TP1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuadraticEquationTest {

    @Test
    void solve() {
        //test solution doublee
        double[] A = QuadraticEquation.solve(1, -3, 2); // x² - 3x + 2 = 0
        assertNotNull(A);
        assertEquals(2, A.length);
        assertTrue(contains(A, 1.0));
        assertTrue(contains(A, 2.0));
        //test solution unique
        double[] B = QuadraticEquation.solve(1, 2, 1); // x² + 2x + 1 = 0
        assertNotNull(B);
        assertEquals(1, B.length);
        assertEquals(-1.0, B[0], 1e-9);
        //test pas solution
        double[] C = QuadraticEquation.solve(1, 0, 1); // x² + 1 = 0
        assertNull(C);
        // test fonction non quadratic
        assertThrows(IllegalArgumentException.class, () -> QuadraticEquation.solve(0, 2, 1));
    }

    private boolean contains(double[] array, double value) {
        for (double v : array) {
            if (Math.abs(v - value) < 1e-9) {
                return true;
            }
        }
        return false;
    }
}