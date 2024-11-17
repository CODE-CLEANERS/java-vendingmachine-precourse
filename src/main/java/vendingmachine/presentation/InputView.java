package vendingmachine.presentation;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INPUT_PRODUCT_INFO = "상품명과 가격, 수량을 입력해 주세요.";
    private static final String INPUT_MONEY = "투입 금액을 입력해 주세요.";
    private static final String COINS_INPUT = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String INPUT_PRODUCT_NAME = "구매할 상품명을 입력해 주세요.";

    public int getChangesInfo(){
        System.out.println(COINS_INPUT);
        int coinsValue = Integer.parseInt(Console.readLine());
        InputValidator.validateInteger(coinsValue);
        return coinsValue;
    }

    public String getProduct(){
        System.out.println(INPUT_PRODUCT_INFO);
        return Console.readLine();
    }

    public String getInputMoney(){
        System.out.println(INPUT_MONEY);
        return Console.readLine();
    }

    public String getProductName(){
        System.out.println(INPUT_PRODUCT_NAME);
        return Console.readLine();
    }
}
