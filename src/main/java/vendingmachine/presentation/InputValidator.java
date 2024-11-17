package vendingmachine.presentation;

public class InputValidator {
    private InputValidator() {
       throw new UnsupportedOperationException();
    }

    public static void validateInteger(int value){
        if (value < 0){
            throw new IllegalArgumentException(PresentationErrorMessage.INVALID_NUMBER.getMessage());
        }
    }
}
