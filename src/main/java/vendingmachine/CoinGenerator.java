package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.model.Coin;
import vendingmachine.model.Coins;

import java.util.*;

public class CoinGenerator {
    private Map<Integer, Integer> emptyCoins(){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(Coin.COIN_500.getAmount(), 0);
        map.put(Coin.COIN_100.getAmount(), 0);
        map.put(Coin.COIN_50.getAmount(), 0);
        map.put(Coin.COIN_10.getAmount(), 0);
        return map;
    }

    private List<Integer> coinAmountNames(){
        List<Integer> coinAmountNames = new ArrayList<>();
        coinAmountNames.add(Coin.COIN_500.getAmount());
        coinAmountNames.add(Coin.COIN_100.getAmount());
        coinAmountNames.add(Coin.COIN_50.getAmount());
        coinAmountNames.add(Coin.COIN_10.getAmount());
        return coinAmountNames;
    }

    public Coins generateCoins(int money){
        Map<Integer, Integer> map = emptyCoins();
        while(money>0){
            int pickNum = Randoms.pickNumberInList(coinAmountNames());
            if(pickNum<=money){
                map.put(pickNum, map.get(pickNum)+1);
                money -= pickNum;
            }
        }
        return new Coins(map);
    }
}
