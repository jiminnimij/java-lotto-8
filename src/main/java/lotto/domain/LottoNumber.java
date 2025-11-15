package lotto.domain;

import lotto.exception.ErrorCode;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber>{
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private final int number;

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if( number < MIN_NUMBER || number > MAX_NUMBER ) {
            throw new IllegalArgumentException(ErrorCode.INVALID_LOTTO_RANGE.getMessage());
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return Integer.compare(this.number, lottoNumber.number);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof LottoNumber lottoNumber)) return false;
        return this.number == lottoNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
