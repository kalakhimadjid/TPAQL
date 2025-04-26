package org.TP1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {
    int[] A = {1, 3, 5, 7, 9};
    int[] B = {};
    @Test
    void binarySearch() {
        assertEquals(2, BinarySearch.binarySearch(A, 5));// au milieu
        assertEquals(0, BinarySearch.binarySearch(A, 1)); // au debut
        assertEquals(4, BinarySearch.binarySearch(A, 9)); //a la fin
        assertEquals(-1, BinarySearch.binarySearch(A, 8)); // non found
        assertEquals(-1, BinarySearch.binarySearch(B, 5)); //test table vide
        assertThrows(NullPointerException.class, () -> BinarySearch.binarySearch(null, 5)); // null table

    }
}