package org.TP1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeTest {

    @Test
    void isPalindrome() {
        assertTrue(Palindrome.isPalindrome("kalak"));
        assertTrue(Palindrome.isPalindrome("ka l a k"));
        assertTrue(Palindrome.isPalindrome("KaLak"));
        assertFalse(Palindrome.isPalindrome("kalakhi"));
        assertTrue(Palindrome.isPalindrome(""));
        assertThrows(NullPointerException.class, () -> Palindrome.isPalindrome(null));
    }
}