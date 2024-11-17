package vendingmachine;

import vendingmachine.presentation.InputView;
import vendingmachine.presentation.OutputView;
import vendingmachine.presentation.VendingMachineController;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        VendingMachineController vendingMachineController = new VendingMachineController(inputView, outputView);
        vendingMachineController.run();
    }
}
