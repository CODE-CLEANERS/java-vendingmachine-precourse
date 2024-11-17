package vendingmachine.domain;

public class Product {
    private static final int MINIMUM_MONEY_VALUE = 100;
    private static final int MINIMUM_MONEY_THRESHOLD = 10;
    private final String name;
    private final int price;
    private int quantity;

    public Product(String name, int price, int quantity) {
        this.name = name;
        validateMoney(price);
        this.price = price;
        this.quantity = quantity;
    }

    private void validateMoney(int money){
        if (money % MINIMUM_MONEY_THRESHOLD != 0 || money < MINIMUM_MONEY_VALUE){
            throw new IllegalArgumentException(DomainErrorMessage.INVALID_MONEY.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity(int buyQuantity) {
        if (buyQuantity > this.quantity){
            throw new IllegalArgumentException(DomainErrorMessage.INVALID_BUY_QUANTITY.getMessage());
        }
        this.quantity -= buyQuantity;
    }
}
