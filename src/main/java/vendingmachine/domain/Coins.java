package vendingmachine.domain;

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
            return coinsForChanges;
        }
        return Coin.getCoinsFrom(changes);
    }

    public void removeAll(List<Coin> changes) {
        coinsForChanges.removeAll(changes);
    }

    public List<Coin> getCoinsForChanges() {
        return coinsForChanges;
    }
}
