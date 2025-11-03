package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        PurchaseAmount purchaseAmount = askAmount();

    }

    private PurchaseAmount askAmount() {
        outputView.promptAmount();
        while (true) {
            try {
                int amount = inputView.readPurchaseAmount();

                return new PurchaseAmount(amount);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
