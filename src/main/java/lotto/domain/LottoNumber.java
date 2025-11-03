package lotto.domain;

import lotto.exception.ErrorCode;

public class LottoNumber {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private int number;

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if( number < MIN_NUMBER || number > MAX_NUMBER ) {
            throw new IllegalArgumentException(ErrorCode.INVALID_LOTTO_RANGE.getMessage());
        }
    }
}
