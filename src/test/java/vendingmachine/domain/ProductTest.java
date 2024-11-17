package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProductTest {

    @DisplayName("초기화에 성공한다.")
    @Test
    void constructTest() {
        String name = "name";
        int price = 1000;
        int quantity = 1;
        Product product = new Product(name, price, quantity);
        SoftAssertions.assertSoftly(
                softly -> {
                    softly.assertThat(product.getName()).isEqualTo(name);
                    softly.assertThat(product.getPrice()).isEqualTo(price);
                    softly.assertThat(product.getQuantity()).isEqualTo(quantity);
                }
        );
    }

    @DisplayName("부적절한 가격이 입력되면 예외이다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 99, 101})
    void invalid_money(int invalidMoney) {
        assertThatThrownBy(() -> new Product("name", invalidMoney, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DomainErrorMessage.INVALID_MONEY.getMessage());
    }

    @DisplayName("수량을 정상적으로 감소 시킬 수 있다.")
    @Test
    void decreaseQuantity() {
        Product product = new Product("name", 100, 10);
        product.decreaseQuantity(10);
        Assertions.assertThat(product.getQuantity()).isZero();
    }

    @DisplayName("재고 수량보다 더 많은 량을 감소시키면 예외이다.")
    @Test
    void decreaseQuantity_invalidBuyQuantity() {
        Product product = new Product("name", 100, 10);
        assertThatThrownBy(() -> product.decreaseQuantity(11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DomainErrorMessage.INVALID_BUY_QUANTITY.getMessage());
    }
}
