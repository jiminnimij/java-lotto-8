package lotto.domain;

import lotto.exception.ErrorCode;

public class PurchaseAmount {
    private final int amount;

    public PurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        validatePositiveInteger(amount);
        validateMultipleOfThousand(amount);
    }

    private void validatePositiveInteger(int amount) {
       if( amount <= 0 ) {
           throw new IllegalArgumentException(ErrorCode.INVALID_POSITIVE_NUMBER.getMessage());
       }
    }

    private void validateMultipleOfThousand(int amount) {
        if( amount % 1000 != 0 ) {
            throw new IllegalArgumentException(ErrorCode.INVALID_AMOUNT.getMessage());
        }
    }
}
