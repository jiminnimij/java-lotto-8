package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
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
        WinningLotto winningLotto = askWinningLotto();
        
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

    private WinningLotto askWinningLotto() {
        Lotto winningNumbers = askWinningNumbers();
        LottoNumber bonusNumber = askBonusNumber();

        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private Lotto askWinningNumbers() {
        outputView.promptWinningNumbers();
        while (true) {
            try {
                List<Integer> winningNumbers = inputView.readWinningNumbers();
                return new Lotto(winningNumbers);

            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private LottoNumber askBonusNumber() {
        outputView.promptBonusNumber();
        while (true) {
            try {
                int bonusNumber = inputView.readBonusNumber();
                return new LottoNumber(bonusNumber);

            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
