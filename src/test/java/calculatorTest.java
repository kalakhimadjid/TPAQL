import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class calculatorTest {

    @Test
    void add() {
        assertEquals(4, calculator.add(2, 2));

    }

    @Test
    void multiply() {
        assertAll(() -> assertEquals(4, calculator.multiply(2, 2)),
                () -> assertEquals(-4, calculator.multiply(2, -2)),
                () -> assertEquals(4, calculator.multiply(-2, -2)),
                () -> assertEquals(0, calculator.multiply(1, 0)));

    }
}