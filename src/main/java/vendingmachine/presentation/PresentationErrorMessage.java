package vendingmachine.presentation;

public enum PresentationErrorMessage {
    INVALID_PRODUCT_INPUT_FORMAT("상품 입력 포맷이 부적절합니다."),
    INVALID_NUMBER_FORMAT("숫자만 입력 해 주세요"),
    INVALID_NUMBER("부적절한 숫자 범위입니다."),
    ;

    private static final String ERROR = "[ERROR] ";
    private final String message;

    PresentationErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR + message;
    }
}
