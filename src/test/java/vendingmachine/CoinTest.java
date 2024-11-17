package vendingmachine;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vendingmachine.domain.DomainErrorMessage;

class CoinTest {
    @DisplayName("금액으로부터 코인 더미를 생성 할 수 있다")
    @Test
    void getCoinsFrom() {
        List<Coin> coinsFrom = Coin.getCoinsFrom(660);
        Assertions.assertThat(coinsFrom).containsExactly(
                Coin.COIN_500, Coin.COIN_100, Coin.COIN_50, Coin.COIN_10
        );
    }

    @DisplayName("부적절한 금액이 주어지면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 99, 101})
    void getCoinsFrom_invalidMoney(int invalidMoney) {
        assertThatThrownBy(() -> Coin.getCoinsFrom(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DomainErrorMessage.INVALID_MONEY.getMessage());

    }
}
