package vendingmachine.presentation;

import java.util.ArrayList;
import java.util.List;
import vendingmachine.domain.Product;

public class ProductParser {
    private ProductParser() {
        throw new UnsupportedOperationException();
    }

    public static List<Product> parseInput(String input){
        ArrayList<Product> products = new ArrayList<>();
        String[] split = input.split(";");
        for (String each : split) {
            Product product = getProduct(each);
            products.add(product);
        }
        return products;
    }

    private static Product getProduct(String each) {
        String[] namePriceQuantity = getNamePriceQuantity(each);
        String name = namePriceQuantity[0];
        String price = namePriceQuantity[1];
        String quantity = namePriceQuantity[2];
        return new Product(name, formatStringToInteger(price), formatStringToInteger(quantity));
    }

    private static int formatStringToInteger(String value){
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException(PresentationErrorMessage.INVALID_NUMBER_FORMAT.getMessage());
        }
    }

    private static String[] getNamePriceQuantity(String each) {
        if (!each.startsWith("[") || !each.endsWith("]")) {
            throw new IllegalArgumentException(PresentationErrorMessage.INVALID_PRODUCT_INPUT_FORMAT.getMessage());
        }
        String productData = each.replace("[", "").replace("]", "");
        String[] namePriceQuantity = productData.split(",");
        validateProductFormat(namePriceQuantity);
        return namePriceQuantity;
    }

    private static void validateProductFormat(String[] namePriceQuantity) {
        if (namePriceQuantity.length != 3){
            throw new IllegalArgumentException(PresentationErrorMessage.INVALID_PRODUCT_INPUT_FORMAT.getMessage());
        }
    }
}
