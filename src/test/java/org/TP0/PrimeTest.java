package org.TP0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeTest {

    @Test
    void isPrime() {
        assertFalse(Prime.isPrime(-10));
        assertFalse(Prime.isPrime(0));
        assertTrue(Prime.isPrime(29));
        assertTrue(Prime.isPrime(97));
        assertFalse(Prime.isPrime(1));
    }
}