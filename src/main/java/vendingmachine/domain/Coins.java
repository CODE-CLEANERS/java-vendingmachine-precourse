package vendingmachine.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import vendingmachine.Coin;

public class Coins {
    private final List<Coin> coinsForChanges;

    private Coins(List<Coin> coinsForChanges) {
        validate(coinsForChanges);
        this.coinsForChanges = coinsForChanges;
    }

    private void validate(List<Coin> coins){
        if (Objects.isNull(coins) || coins.isEmpty()) {
            throw new IllegalArgumentException(DomainErrorMessage.INVALID_CHANGES.getMessage());
        }
    }

    public static Coins from(int money){
        List<Coin> coinsFromMoney = Coin.getCoinsFrom(money);
        return new Coins(coinsFromMoney);
    }

    private int getCoinsSum(){
        return coinsForChanges.stream().mapToInt(Coin::getAmount).sum();
    }

    public List<Coin> getChanges(int changes){
        if (getCoinsSum() <= changes){
            return Collections.unmodifiableList(coinsForChanges);
        }
        return Coin.getCoinsFrom(changes);
        // 만약 어플리케이션이 계속 되어야 한다면, 아래 리스트가 포함하는 요소를 Coins 클래스의 리스트에서 제거한다.
    }
}
