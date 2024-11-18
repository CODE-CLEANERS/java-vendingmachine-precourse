package vendingmachine.model;

public class Product {
    private final String name;
    private final int price;
    private final int amount;

    public Product(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public int getPrice(){
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public int getAmount(){
        return this.amount;
    }
}
