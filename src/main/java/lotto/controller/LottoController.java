package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.generator.LottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;

    public LottoController(InputView inputView, OutputView outputView, LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        PurchaseAmount purchaseAmount = askAmount();
        List<Lotto> lottos = lottoGenerator.issueLottos(purchaseAmount.getCount());
        outputView.printLottos(lottos);



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
