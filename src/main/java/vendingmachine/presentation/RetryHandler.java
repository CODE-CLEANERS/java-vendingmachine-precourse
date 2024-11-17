package vendingmachine.presentation;

import java.util.function.Supplier;

public class RetryHandler {
    private RetryHandler() {
        throw new UnsupportedOperationException();
    }

    public static <T> T retry(Supplier<T> consumer){
        while (true){
            try {
                return consumer.get();
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void retry(Runnable consumer){
        while (true){
            try {
                consumer.run();
                return;
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
