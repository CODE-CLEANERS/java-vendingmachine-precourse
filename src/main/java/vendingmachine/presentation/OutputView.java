package vendingmachine.presentation;

public class OutputView {
    private static final String INPUT_MONEY_REMAIN = "투입 금액: %d원";
    private static final String MACHINE_HOLDING_COINS = "자판기가 보유한 동전";

    public void printRemainingMoney(int amount){
        System.out.printf((INPUT_MONEY_REMAIN) + "%n", amount);
    }

    public void printCoins(String coins){
        System.out.println(coins);
    }

    public void printInitialCoins(String coins) {
        System.out.println(MACHINE_HOLDING_COINS);
        System.out.println(coins);
    }
}
