package vendingmachine;

import vendingmachine.controller.MachineController;
import vendingmachine.model.Coins;
import vendingmachine.view.MachineView;

import java.util.HashMap;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        MachineView machineView = new MachineView();
        CoinGenerator coinGenerator = new CoinGenerator();
        Coins coins = new Coins(new HashMap<>());
        MachineController machineController = new MachineController(machineView, coinGenerator);
        machineController.run();
    }
}
