package vendingmachine.domain;

public enum DomainErrorMessage {
    INVALID_MONEY("가격 형식이 부적절합니다."),
    INVALID_BUY_QUANTITY("구매하려는 수량이 재고 수량보다 많습니다."),
    EMPTY_STOCK("상품 목록이 없습니다."),
    INVALID_CHANGES("잔돈 입력 금액이 부적절합니다."),
    INVALID_PRODUCT_NAME("상품 이름이 부적절합니다."),
    NOT_ENOUGH_MONEY("상품 구매 금액이 부족합니다."),

    DUPLICATED_NAMES("상품 이름이 중복되었습니다.");

    private static final String ERROR = "[ERROR]";
    private final String message;

    DomainErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR + message;
    }
}
