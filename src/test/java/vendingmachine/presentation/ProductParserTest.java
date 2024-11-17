package vendingmachine.presentation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vendingmachine.domain.Product;

class ProductParserTest {
    @DisplayName("사용자의 입력을 파싱해서 상품 목록으로 변환한다.")
    @Test
    void parseInput() {
        String given = "[콜라,1500,20];[사이다,1000,10]";
        List<Product> products = ProductParser.parseInput(given);
        Product cola = new Product("콜라", 1500, 20);
        Product cider = new Product("사이다", 1000, 10);
        Assertions.assertThat(products).containsExactly(cola, cider);
    }

    @DisplayName("정해진 포맷에 맞지 않으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"[콜라,1500,20],[사이다,1000,10]", "[콜라,숫자아님,20],[사이다,1000,10]", "[콜라,1000]", "[콜라;1000;10]"})
    void parseInput_invalidDelimiter() {
        String given = "[콜라,1500,20],[사이다,1000,10]";
        assertThatThrownBy(() -> ProductParser.parseInput(given))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PresentationErrorMessage.INVALID_PRODUCT_INPUT_FORMAT.getMessage());
    }
}
