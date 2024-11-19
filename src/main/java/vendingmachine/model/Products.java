package vendingmachine.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Products {
    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public List<Product> getProducts(){
        return Collections.unmodifiableList(this.products);
    }

    public int getMinPrice(){
        int minPrice = (int) 1e8;
        for(Product product:products){
            if(product.getPrice()<minPrice) minPrice = product.getPrice();
        }
        return minPrice;
    }

    public int getPrice(String name){
        Product product = findProduct(name);
        return product.getPrice();
    }

    public Product findProduct(String name) {
        return products.stream().filter(p -> p.getName().equals(name))
            .findFirst().orElseThrow(
                IllegalArgumentException::new
            );
    }
}
