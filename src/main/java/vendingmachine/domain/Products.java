package vendingmachine.domain;

import java.util.List;
import java.util.Objects;

public class Products {
    private final List<Product> stocks;

    public Products(List<Product> stocks) {
        validateEmpty(stocks);
        this.stocks = stocks;
    }

    private void validateEmpty(List<Product> stocks){
        if (Objects.isNull(stocks) || stocks.isEmpty()){
            throw new IllegalArgumentException(DomainErrorMessage.EMPTY_STOCK.getMessage());
        }
    }

    public int getMinimalPrice() {
        return stocks.stream()
                .map(Product::getPrice)
                .min(Integer::compareTo)
                .orElse(0);
    }

    public int getQuantitySum() {
        return stocks.stream().mapToInt(Product::getQuantity).sum();
    }
}
