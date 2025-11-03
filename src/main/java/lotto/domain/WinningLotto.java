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

    public Rank judge(Lotto lotto) {
        long matchCount = winningLotto.getNumbers().stream()
                .map(LottoNumber::getNumber)
                .filter(lotto::contains)
                .count();

        boolean bonusMatch = lotto.contains(bonusNumber.getNumber());

        return Rank.of(matchCount, bonusMatch);
    }

    private void validateBonusNumber(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber.getNumber())) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATE_BONUS.getMessage());
        }
    }
}
