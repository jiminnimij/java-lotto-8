package lotto.generator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

import java.util.List;
import java.util.stream.IntStream;

public class LottoGenerator {
    public List<Lotto> issueLottos(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> issueLottoNumber())
                .toList();
    }

    private Lotto issueLottoNumber() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);

        return new Lotto(numbers);
    }
}
