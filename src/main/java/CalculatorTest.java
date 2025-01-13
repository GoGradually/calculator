import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

class CalculatorTest {

    static Calculator c;
    @BeforeEach
    void setUp() {
        c = new Calculator();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
        assertThat(c.add(2,3)).isEqualTo(5);
    }
}
