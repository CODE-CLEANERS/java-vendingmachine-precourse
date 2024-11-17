package vendingmachine.presentation;

import java.util.List;
import vendingmachine.domain.Coins;
import vendingmachine.domain.Product;
import vendingmachine.domain.Products;
import vendingmachine.domain.VendingMachine;

public class VendingMachineController {
    private final InputView inputView;
    private final OutputView outputView;

    public VendingMachineController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(){
        int changesInfo = RetryHandler.retry(inputView::getChangesInfo);
        Products products = RetryHandler.retry(this::initProducts);
        int inputMoney = RetryHandler.retry(this::getInputMoney);
        Coins coins = RetryHandler.retry(() ->  getCoins(changesInfo));
        VendingMachine vendingMachine = RetryHandler.retry(() -> new VendingMachine(coins, products, inputMoney));
        RetryHandler.retry(() -> purchaseProducts(vendingMachine));
        RetryHandler.retry(() -> printRemainingCoins(vendingMachine));
    }

    private Coins getCoins(int changesInfo) {
        Coins coins = Coins.from(changesInfo);
        CoinsFormatter coinsFormatter = new CoinsFormatter(coins.getCoinsForChanges());
        outputView.printInitialCoins(coinsFormatter.format());
        return coins;
    }

    private void purchaseProducts(VendingMachine vendingMachine) {
        while (vendingMachine.canBuy()){
            outputView.printRemainingMoney(vendingMachine.getInputMoney());
            String productName = inputView.getProductName();
            vendingMachine.buyProduct(productName);
        }
    }

    private void printRemainingCoins(VendingMachine vendingMachine) {
        CoinsFormatter coinsFormatter = new CoinsFormatter(vendingMachine.getChanges());
        String formattedCoins = coinsFormatter.format();
        outputView.printCoins(formattedCoins);
    }

    private Products initProducts(){
        String userInput = inputView.getProduct();
        List<Product> products = ProductParser.parseInput(userInput);
        return new Products(products);
    }

    private int getInputMoney(){
        String userInput = inputView.getInputMoney();
        return Integer.parseInt(userInput);
    }
}
