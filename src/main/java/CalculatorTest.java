import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.StringReader;
import java.util.Scanner;

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


    @ParameterizedTest
    @CsvSource(value = {
            "'1,1', 2",
            "'1,3', 4",
            "'2:5', 7"
    })
    @DisplayName("커스텀 구분자 성공 동작 테스트")
    void customSeparatorSuccess(String input, int expected) {
        StringReader reader = new StringReader(input);
        Scanner scanner = new Scanner(reader);
        String value = scanner.nextLine();
        Calculator cal = new Calculator();

        assertThat(cal.calculate(value)).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1|3",
            "1%4"
    })
    @DisplayName("커스텀 구분자 실패 동작 테스트")
    void customSeparatorFail(String input) {
        Calculator cal = new Calculator();
        assertThatThrownBy(() -> cal.calculate(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("공백 혹은 null값 정상 처리 테스트")
    void customSeparatorNullAndEmpty(String input) {
        Calculator cal = new Calculator();
        assertThat(cal.calculate(input)).isEqualTo(0);
    }

    @Test
    @DisplayName("예제 테스트")
    void exampleTest(){
        Calculator cal = new Calculator();
        assertThat(cal.calculate("2 + 3 * 4 / 2")).isEqualTo(10);
    }

    @Test
    @DisplayName("통합 테스트")
    void exampleTest2(){
        Calculator cal = new Calculator();
        assertThat(cal.calculate("2:5 + 3 * 4 / 2")).isEqualTo(20);
    }
}
