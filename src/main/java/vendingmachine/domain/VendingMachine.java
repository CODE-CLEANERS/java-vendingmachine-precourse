package vendingmachine.domain;

import java.util.List;
import vendingmachine.Coin;

public class VendingMachine {
    private final Coins coins;
    private final Products products;
    private int inputMoney;

    public VendingMachine(Coins coins, Products products, int inputMoney) {
        this.coins = coins;
        this.products = products;
        this.inputMoney = inputMoney;
    }

    public void buyProduct(String productName){
        int productPrice = products.getPriceFrom(productName);
        if (productPrice > inputMoney){
            throw new IllegalArgumentException(DomainErrorMessage.NOT_ENOUGH_MONEY.getMessage());
        }
        inputMoney -= productPrice;
        products.decreaseByName(productName);
    }

    public boolean canBuy(){
        if (products.getQuantitySum() == 0){
            return false;
        }
        return products.getMinimalPrice() <= inputMoney;
    }

    public int getInputMoney() {
        return inputMoney;
    }

    public List<Coin> getChanges(){
        List<Coin> changes = coins.getChanges(inputMoney);
        coins.removeAll(changes);
        return changes;
    }

    public String getCoinsString(){
        return coins.toString();
    }
}
