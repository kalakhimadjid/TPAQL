package org.TP0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person A=new Person("kalakhi","madjid",24);
    Person B=new Person("Aissani","Ramzi",17);
    @Test
    void getFullName() {
        assertEquals("kalakhi madjid", A.getFullName());
        assertEquals("Aissani Ramzi", B.getFullName());
    }

    @Test
    void isAdult() {
        assertEquals(true, A.isAdult());
        assertEquals(false, B.isAdult());
    }
}