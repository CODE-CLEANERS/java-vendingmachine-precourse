package vendingmachine.domain;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Products {
    private final List<Product> stocks;

    public Products(List<Product> stocks) {
        validate(stocks);
        this.stocks = stocks;
    }

    private void validate(List<Product> stocks){
        validateEmpty(stocks);
        validateDuplicatedName(stocks);
    }

    private void validateEmpty(List<Product> stocks){
        if (Objects.isNull(stocks) || stocks.isEmpty()){
            throw new IllegalArgumentException(DomainErrorMessage.EMPTY_STOCK.getMessage());
        }
    }

    private void validateDuplicatedName(List<Product> stocks){
        Set<String> nameSet = stocks.stream().map(Product::getName).collect(Collectors.toSet());
        if (stocks.size() != nameSet.size()){
            throw new IllegalArgumentException(DomainErrorMessage.DUPLICATED_NAMES.getMessage());
        }
    }

    public int getMinimalPrice() {
        return stocks.stream()
                .mapToInt(Product::getPrice)
                .min()
                .orElseThrow(() -> new IllegalArgumentException(DomainErrorMessage.EMPTY_STOCK.getMessage()));
    }

    public int getQuantitySum() {
        return stocks.stream().mapToInt(Product::getQuantity).sum();
    }

    public int getPriceFrom(String productName){
        return getProductByName(productName).getPrice();
    }

    public void decreaseByName(String productName){
        Product productByName = getProductByName(productName);
        productByName.decreaseQuantity(1);
    }

    private Product getProductByName(String productName) {
        return stocks.stream()
                .filter(each -> each.getName().equals(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(DomainErrorMessage.INVALID_PRODUCT_NAME.getMessage()));
    }
}
