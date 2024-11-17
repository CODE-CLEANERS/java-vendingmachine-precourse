package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.Coin;

class CoinsTest {
    @DisplayName("초기화에 성공한다")
    @Test
    void constructTest() {
        Coins coins = Coins.from(500);
        Assertions.assertThat(coins).isNotNull();
    }

    @DisplayName("부적절한 금액이 주어지면 예외가 발생한다.")
    @Test
    void constructTest_emptyList() {
        assertThatThrownBy(() -> Coins.from(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DomainErrorMessage.INVALID_MONEY.getMessage());
    }

    @DisplayName("잔돈이 현재 코인 보유량보다 많으면 보유량 전부를 리턴한다.")
    @Test
    void getChanges_changeIsBiggerThanCoins() {
        Coins coins = Coins.from(660);
        List<Coin> changes = coins.getChanges(1000);
        Assertions.assertThat(changes).containsExactly(
                Coin.COIN_500, Coin.COIN_100, Coin.COIN_50, Coin.COIN_10
        );
    }

    @DisplayName("잔돈이 현재 코인 보유량보다 적으면 필요량을 계산하여 리턴한다.")
    @Test
    void getChanges_changeIsSmallerThanCoins() {
        Coins coins = Coins.from(1000);
        List<Coin> changes = coins.getChanges(660);
        Assertions.assertThat(changes).containsExactly(
                Coin.COIN_500, Coin.COIN_100, Coin.COIN_50, Coin.COIN_10
        );
    }
}
