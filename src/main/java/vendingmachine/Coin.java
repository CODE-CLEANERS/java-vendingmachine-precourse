package vendingmachine;

import java.util.ArrayList;
import java.util.List;
import vendingmachine.domain.DomainErrorMessage;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private static final int MINIMUM_MONEY_VALUE = 100;
    private static final int MINIMUM_MONEY_THRESHOLD = 10; // TODO 이 부분 프로덕트와 중복이다. 제거한다.
    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static List<Coin> getCoinsFrom(int money){
        validateMoney(money);
        return generateCoins(money);
    }

    private static List<Coin> generateCoins(int money) {
        List<Coin> coins = new ArrayList<>();
        for (Coin coin : Coin.values()) {
            int count = money / coin.amount;
            money %= coin.amount;
            addCoins(coin, count, coins);
        }
        return coins;
    }

    private static void addCoins(Coin coin, int count, List<Coin> coins) {
        for (int i = 0; i < count; i++) {
            coins.add(coin);
        }
    }

    private static void validateMoney(int money){
        if (money % MINIMUM_MONEY_THRESHOLD != 0 || money < MINIMUM_MONEY_VALUE){
            throw new IllegalArgumentException(DomainErrorMessage.INVALID_MONEY.getMessage());
        }
    }

    public int getAmount() {
        return amount;
    }

    public String customToString(int quantity) {
        return amount + "원 - " + quantity + "개";
    }
}
