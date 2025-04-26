package org.TP1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanNumeralTest {

    @Test
    void toRoman() {
        assertEquals("I", RomanNumeral.toRoman(1));
        assertEquals("IV", RomanNumeral.toRoman(4));
        assertEquals("IX", RomanNumeral.toRoman(9));
        assertEquals("XXV", RomanNumeral.toRoman(25));
        assertEquals("XLII", RomanNumeral.toRoman(42));
        assertEquals("XC", RomanNumeral.toRoman(90));
        assertEquals("CXXV", RomanNumeral.toRoman(125));
        assertEquals("DCCC", RomanNumeral.toRoman(800));
        assertEquals("CM", RomanNumeral.toRoman(900));
        assertEquals("MCMXCIX", RomanNumeral.toRoman(1999));
        assertEquals("MMMCMXCIX", RomanNumeral.toRoman(3999));
        //test input invalid
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.toRoman(0));
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.toRoman(4000));
    }
}