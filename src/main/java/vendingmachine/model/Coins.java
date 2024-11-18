package vendingmachine.model;

import java.util.*;

public class Coins {
    private final Map<Coin, Integer> coins;

    public Coins(Map<Integer, Integer> coins) {
        Map<Coin, Integer> map = new HashMap<>();
        for(Integer key: coins.keySet()){
            if(key==500) map.put(Coin.COIN_500, coins.get(key));
            if(key==100) map.put(Coin.COIN_100, coins.get(key));
            if(key==50) map.put(Coin.COIN_50, coins.get(key));
            if(key==10) map.put(Coin.COIN_10, coins.get(key));
        }
        this.coins = map;
    }

    public Map<Coin, Integer> getCoins(){
        return new HashMap<>(coins);
    }

    public List<Integer> getQuantitys(){
        return Arrays.asList(coins.get(Coin.COIN_500), coins.get(Coin.COIN_100), coins.get(Coin.COIN_50), coins.get(Coin.COIN_10));
    }
}
