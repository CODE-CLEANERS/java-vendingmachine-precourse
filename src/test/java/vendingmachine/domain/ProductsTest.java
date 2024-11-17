package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class ProductsTest {
    private Products products;

    @BeforeEach
    void setUp() {
        Product one = new Product("name", 100, 1);
        Product two = new Product("name", 200, 2);
        Product three = new Product("name", 300, 3);
        ArrayList<Product> objects = new ArrayList<>();
        objects.add(one);
        objects.add(two);
        objects.add(three);
        products = new Products(Collections.unmodifiableList(objects));
    }

    @DisplayName("초기화에 성공한다.")
    @Test
    void constructTest() {
        Assertions.assertThat(products).isNotNull();
    }

    @DisplayName("상품 목록이 null 이면 예외이다")
    @NullSource
    @ParameterizedTest
    void nullListProvided(List<Product> products) {
        assertThatThrownBy(() -> new Products(products))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DomainErrorMessage.EMPTY_STOCK.getMessage());
    }

    @DisplayName("상품 목록이 제공되지 않으면 예외이다")
    @Test
    void invalid_quantity() {
        ArrayList<Product> empty = new ArrayList<>();
        assertThatThrownBy(() -> new Products(empty))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DomainErrorMessage.EMPTY_STOCK.getMessage());
    }

    @DisplayName("'상품 최소 가격'을 도출 가능하다")
    @Test
    void getMinimalPrice() {
        int minimalPrice = products.getMinimalPrice();
        Assertions.assertThat(minimalPrice).isEqualTo(100);
    }

    @DisplayName("상품의 총 수량을 도출 가능하다")
    @Test
    void getQuantitySum(){
        int quantitySum = products.getQuantitySum();
        Assertions.assertThat(quantitySum).isEqualTo(6);
    }
}
