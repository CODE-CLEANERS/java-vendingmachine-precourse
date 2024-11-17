package vendingmachine.domain;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.Coin;
import vendingmachine.presentation.ProductParser;

class VendingMachineTest {
    private VendingMachine vendingMachine;
    private Coins coins;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        coins = Coins.from(1000);
        products = ProductParser.parseInput("[콜라,1500,20];[사이다,1000,10]");
        vendingMachine = new VendingMachine(coins, new Products(products), 5000);
    }

    @DisplayName("물품을 구매하면 넣은 금액이 차감된다.")
    @Test
    void buyProduct() {
        vendingMachine.buyProduct("콜라");
        Assertions.assertThat(vendingMachine.getInputMoney()).isEqualTo(3500);
    }

    @DisplayName("남은 금액이 물품의 최소금액보다 적으면 구매가 불가능하다.")
    @Test
    void canBuy_notEnoughBudget() {
        vendingMachine.buyProduct("콜라");
        vendingMachine.buyProduct("콜라");
        vendingMachine.buyProduct("콜라"); // 남은금액 500원
        Assertions.assertThat(vendingMachine.canBuy()).isFalse();
    }

    @DisplayName("재고가 없으면 구매가 불가능하다.")
    @Test
    void buyProduct_NotEnoughQuantity() {
        ArrayList<Product> emptyList = new ArrayList<>();
        Product product = new Product("name", 1000, 0);
        emptyList.add(product);
        VendingMachine vendingMachine1 = new VendingMachine(coins, new Products(emptyList), 5000);
        Assertions.assertThat(vendingMachine1.canBuy()).isFalse();
    }

    @DisplayName("자판기는 잔돈을 계산하여 리턴 가능하다")
    @Test
    void getChanges() {
        vendingMachine = new VendingMachine(coins, new Products(products), 1660);
        vendingMachine.buyProduct("사이다");
        List<Coin> changes = vendingMachine.getChanges();
        Assertions.assertThat(changes).containsExactly(
                Coin.COIN_500, Coin.COIN_100, Coin.COIN_50, Coin.COIN_10
        );
    }
}
