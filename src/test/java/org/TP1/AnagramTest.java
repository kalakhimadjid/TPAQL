package org.TP1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnagramTest {

    @Test
    void isAnagram() {
        assertTrue(Anagram.isAnagram("listen", "silent"));
        assertTrue(Anagram.isAnagram("Astronomer", "Moon starer"));
        assertTrue(Anagram.isAnagram("conversation", "voices rant on"));
        assertFalse(Anagram.isAnagram("hello", "helloo"));
        assertFalse(Anagram.isAnagram("test", "best"));
        assertTrue(Anagram.isAnagram("", ""));
        assertTrue(Anagram.isAnagram("a", "a"));
        assertFalse(Anagram.isAnagram("a", "b"));
        assertThrows(NullPointerException.class, () -> Anagram.isAnagram(null, "abc"));
        assertThrows(NullPointerException.class, () -> Anagram.isAnagram(null, null));
    }
}