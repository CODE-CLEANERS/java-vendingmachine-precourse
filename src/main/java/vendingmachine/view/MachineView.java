package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.model.Coin;
import vendingmachine.model.Coins;
import vendingmachine.model.Product;
import vendingmachine.model.Products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MachineView {
    private static final String INPUT_CHANGE = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String NOW_CHANGE = System.lineSeparator() + "자판기가 보유한 동전" + System.lineSeparator();
    private static final String NOW_500 = "500원 - %d개" + System.lineSeparator();
    private static final String NOW_100 = "100원 - %d개" + System.lineSeparator();
    private static final String NOW_50 = "50원 - %d개" + System.lineSeparator();
    private static final String NOW_10 = "10원 - %d개" + System.lineSeparator();
    private static final String OUTPUT_CHANGE = NOW_CHANGE + NOW_500 + NOW_100 + NOW_50 + NOW_10 + System.lineSeparator();
    private static final String PRODUCT_NAME_AND_AMOUNT = "상품명과 가격, 수량을 입력해 주세요.";
    private static final String INSERT_MONEY = System.lineSeparator() + "투입 금액을 입력해 주세요.";
    private static final String NOW_MONEY = System.lineSeparator() + "투입 금액: %d원" + System.lineSeparator();
    private static final String INSERT_PRODUCT_NAME = "구매할 상품명을 입력해 주세요.";
    private static final String FINAL_CHANGE = "잔돈";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String IS_NOT_NUMBER = "금액은 숫자여야 합니다.";
    private static final String WRONG_PATTERN = "입력형식이 맞지 않습니다.";
    private static final String WRONG_PRODUCT = "존재하지 않는 상품입니다.";
    private static final String MUST_BE_OVER_TEN = "10원 단위로만 입력이 가능합니다.";
    private static final String PATTERN = "\\[[ㄱ-힣]+,\\d+,\\d+\\](;\\[[ㄱ-힣]+,\\d+,\\d+\\])*";

    public void inputChange() {
        System.out.println(INPUT_CHANGE);
    }

    public int change() {
        try {
            return validateInt(Integer.parseInt(Console.readLine()));
        } catch (NumberFormatException e) {
            System.out.println(ERROR_PREFIX + IS_NOT_NUMBER);
            return change();
        }
    }

    private int validateInt(int parseInt) {
        if(parseInt<0){
            throw new NumberFormatException();
        }
        return parseInt;
    }

    public void nowChange() {
        System.out.println(NOW_CHANGE);
    }

    public Products productNameAndAmount() {
        System.out.println(PRODUCT_NAME_AND_AMOUNT);
        return validateProductNameAndAmount();
    }

    public Products validateProductNameAndAmount() {
        String input = Console.readLine();
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(input);
        try {
            if (!matcher.find()) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_PREFIX + WRONG_PATTERN);
            return productNameAndAmount();
        }
        return createProducts(input, matcher);
    }

    public Products createProducts(String input, Matcher matcher) {
        List<String> orders = Arrays.asList(input.split(";"));
        List<Product> products = new ArrayList<>();
        for (String s : orders) {
            List<String> order = Arrays.asList(s.substring(1, s.length() - 1).split(","));
            Product product = new Product(order.get(0), Integer.parseInt(order.get(1)), Integer.parseInt(order.get(2)));
            products.add(product);
        }
        return new Products(products);
    }

    public int insertMoney() {
        System.out.println(INSERT_MONEY);
        int money = 0;
        try {
            money = validateMoney(Integer.parseInt(Console.readLine()));
        } catch (NumberFormatException e) {
            System.out.println(ERROR_PREFIX + IS_NOT_NUMBER);
        }
        return money;
    }

    private int validateMoney(int input) {
        int result = 0;
        try {
            if (input % 10 == 0) {
                result = input;
            }
            if (input % 10 != 0) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_PREFIX + MUST_BE_OVER_TEN);
            return insertMoney();
        }
        return result;
    }

    public int nowMoneyAndInsertProductName(int money, Products products, Coins coins) {
        int result = 0;
        try {
            if (money >= products.getMinPrice()) {
                System.out.printf(NOW_MONEY, money);
                System.out.println(INSERT_PRODUCT_NAME);
                result = validateProduct(Console.readLine(), products, money, coins);
            }
            if (money < products.getMinPrice()) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            returnChange(money, coins);
        }
        return result;
    }

    private int validateProduct(String input, Products products, int money, Coins coins) {
        try {
            validateAmount(money, products, input, coins);
            validatePrice(money, products, input, coins);
            return nowMoneyAndInsertProductName(money - products.getPrice(input), products, coins);
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_PREFIX + WRONG_PRODUCT);
            return nowMoneyAndInsertProductName(money, products, coins);
        }
    }

    private void validatePrice(int money, Products products, String input, Coins coins) {
        try {
            if (products.findProduct(input).getPrice() > money) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            returnChange(money, coins);
        }
    }

    private void validateAmount(int money, Products products, String input, Coins coins) {
        try {
            if (products.findProduct(input).getAmount() == 0) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            returnChange(money, coins);
        }
    }

    private void returnChange(int money, Coins coins) {
        System.out.printf(NOW_MONEY, money);
        System.out.println(FINAL_CHANGE);
        List<Integer> coin = coins.getQuantitys();
        money = check500(money, coin.get(0));
        money = check100(money, coin.get(1));
        money = check50(money, coin.get(2));
        money = check10(money, coin.get(3));
        System.exit(0);
    }

    private int check10(int money, Integer i) {
        int cnt = 0;
        while(money/Coin.COIN_10.getAmount()>0 && i>0){
            money-=Coin.COIN_10.getAmount();
            cnt++;
            i--;
        }
        System.out.printf(NOW_10, cnt);
        return money;
    }

    private int check50(int money, Integer i) {
        int cnt = 0;
        while(money/Coin.COIN_50.getAmount()>0 && i>0){
            money-=Coin.COIN_50.getAmount();
            cnt++;
            i--;
        }
        System.out.printf(NOW_50, cnt);
        return money;
    }

    private int check100(int money, Integer i) {
        int cnt = 0;
        while(money/Coin.COIN_100.getAmount()>0 && i>0){
            money-=Coin.COIN_100.getAmount();
            cnt++;
            i--;
        }
        System.out.printf(NOW_100, cnt);
        return money;
    }

    private int check500(int money, int i) {
        int cnt = 0;
        while(money/Coin.COIN_500.getAmount()>0 && i>0){
            money-=Coin.COIN_500.getAmount();
            cnt++;
            i--;
        }
        System.out.printf(NOW_500, cnt);
        return money;
    }

    public void insertProductName() {
        System.out.println(INSERT_PRODUCT_NAME);
    }

    public void finalChange() {
        System.out.println(FINAL_CHANGE);
    }

    public void outputCoins(List<Integer> coins) {
        System.out.printf(OUTPUT_CHANGE, coins.toArray());
    }

    public int insertProducts(Products products, Coins coins) {
        int money = insertMoney();
        do {
            money = nowMoneyAndInsertProductName(money, products, coins);
        } while (money < products.getMinPrice());
        return money;
    }
}
