package lotto.domain;

import lotto.exception.ErrorCode;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        validateBonusNumber(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber.getNumber())) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATE_BONUS.getMessage());
        }
    }
}
