package lotto.domain;

import lotto.exception.ErrorCode;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);

        this.numbers = numbers.stream()
                .map(LottoNumber::new)
                .sorted()
                .toList();
    }

    public boolean contains(int number) {
        return numbers.contains(new LottoNumber(number));
    }

    public int matchCountWith(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(num -> winningLotto.contains(num.getNumber()))
                .count();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorCode.INVALID_LOTTO_COUNT.getMessage());
        }
        validateDuplicate(numbers);
    }

    private void validateDuplicate(List<Integer> numbers) {
        HashSet<Integer> set = new HashSet<>(numbers);
        if (set.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATE_NUMBER.getMessage());
        }
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

}
