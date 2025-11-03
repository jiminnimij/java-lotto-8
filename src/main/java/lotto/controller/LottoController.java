package lotto.controller;

import lotto.domain.*;
import lotto.generator.LottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        LottoResult lottoResult = LottoResult.of(lottos, winningLotto, purchaseAmount);
        outputView.printResults(lottoResult);
    }

    private PurchaseAmount askAmount() {
        outputView.promptAmount();
        int amount = inputView.readWithRetry(inputView::readNumber, outputView::printError);
        return new PurchaseAmount(amount);
    }

    private WinningLotto askWinningLotto() {
        Lotto winningNumbers = askWinningNumbers();
        LottoNumber bonusNumber = askBonusNumber();

        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private Lotto askWinningNumbers() {
        outputView.promptWinningNumbers();
        List<Integer> winningNumbers = inputView.readWithRetry(inputView::readNumbers, outputView::printError);
        return new Lotto(winningNumbers);
    }

    private LottoNumber askBonusNumber() {
        outputView.promptBonusNumber();
        int bonusNumber = inputView.readWithRetry(inputView::readNumber, outputView::printError);
        return new LottoNumber(bonusNumber);
    }

}
