package vendingmachine.controller;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.CoinGenerator;
import vendingmachine.model.Coins;
import vendingmachine.model.Products;
import vendingmachine.view.MachineView;

import java.util.List;

public class MachineController {
    private final MachineView machineView;
    private final CoinGenerator coinGenerator;

    public MachineController(MachineView machineView, CoinGenerator coinGenerator) {
        this.machineView = machineView;
        this.coinGenerator = coinGenerator;
    }

    public void run() {
        Coins coins = makeCoins();
        Products products = machineView.productNameAndAmount();
        int money = machineView.insertProducts(products, coins);
    }

    public Coins makeCoins(){
        machineView.inputChange();
        int change = machineView.change();
        Coins coins = coinGenerator.generateCoins(change);
        List<Integer> quantitys = coins.getQuantitys();
        machineView.outputCoins(quantitys);
        return coins;
    }
}
