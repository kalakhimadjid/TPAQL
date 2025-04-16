package org.TP0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private BankAccount A =new BankAccount(1000.0, 0.05);;
    private BankAccount B = new BankAccount(500.0, 0.03);
    @Test
    void deposit() {
        assertThrows(IllegalArgumentException.class, () -> A.deposit(-100.0));
        A.deposit(500.0);
        assertEquals(1500.0, A.getBalance());
    }

    @Test
    void withdraw() {
        assertThrows(IllegalStateException.class, () -> A.withdraw(1500.0));
        A.withdraw(300.0);
        assertEquals(700.0, A.getBalance());
        A.withdraw(700.0);
        assertEquals(0.0, A.getBalance());
        assertThrows(IllegalArgumentException.class, () -> A.withdraw(-50.0));
    }

    @Test
    void transfer() {
        A.transfer(200.0, B);
        assertEquals(800.0, A.getBalance());
        assertEquals(700.0, B.getBalance());
        assertThrows(NullPointerException.class, () -> A.transfer(100.0, null));
        assertThrows(IllegalStateException.class, () -> A.transfer(2000.0, B));
    }

    @Test
    void addInterest() {
        A.addInterest(); // 5% de 1000 = 1050
        assertEquals(1050.0, A.getBalance());
    }

    @Test
    void getBalance() {
        assertEquals(1000.0, A.getBalance());
    }
}