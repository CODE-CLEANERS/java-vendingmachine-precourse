package vendingmachine.presentation;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vendingmachine.Coin;

public class CoinsFormatter {
    private final Map<Coin, Integer> coinMap;

    public CoinsFormatter(List<Coin> coins) {
        coinMap = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {
            coinMap.put(coin, 0);
        }

        coins.forEach(coin -> coinMap.put(coin, coinMap.get(coin) + 1));
    }

    public String format() {
        return coinMap.entrySet().stream()
                .map(CoinsFormatter::formatEntry)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private static String formatEntry(Map.Entry<Coin, Integer> entry) {
        return entry.getKey().getAmount() + "원 - " + entry.getValue() + "개";
    }
}
