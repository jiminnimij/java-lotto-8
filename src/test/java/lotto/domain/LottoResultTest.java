package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoResultTest {
    private Lotto lottoOf(Integer... nums) {
        return new Lotto(List.of(nums));
    }

    @Test
    @DisplayName("등수별 개수가 올바르게 집계된다.")
    void 등수별_개수가_올바르게_집계된다() {
        Map<Lotto, Rank> map = new LinkedHashMap<>();
        map.put(lottoOf(1,2,3,4,5,6), Rank.FIRST);
        map.put(lottoOf(7,8,9,10,11,12), Rank.FIFTH);
        map.put(lottoOf(13,14,15,16,17,18), Rank.MISS);

        PurchaseAmount amount = new PurchaseAmount(8000); // 1000원 단위 가정
        LottoResult result = new LottoResult(map, amount);

        assertThat(result.countByRank(Rank.FIRST)).isEqualTo(1);
        assertThat(result.countByRank(Rank.FIFTH)).isEqualTo(1);
        assertThat(result.countByRank(Rank.MISS)).isEqualTo(1);
        assertThat(result.countByRank(Rank.SECOND)).isEqualTo(0); // 없는 등수는 0
    }

    @Test
    @DisplayName("총 상금이 모든 로또의 등수 상금 합으로 계산된다.")
    void 상금은_모든_로또의_상금_합으로_계산된다() {
        Map<Lotto, Rank> map = new LinkedHashMap<>();
        map.put(lottoOf(1,2,3,4,5,6), Rank.FOURTH);
        map.put(lottoOf(7,8,9,10,11,12), Rank.FIFTH);
        map.put(lottoOf(13,14,15,16,17,18), Rank.MISS);

        long expected = Rank.FOURTH.getPrize() + Rank.FIFTH.getPrize() + Rank.MISS.getPrize();

        LottoResult result = new LottoResult(map, new PurchaseAmount(3000));

        assertThat(result.getTotalPrize()).isEqualTo(expected);
    }

    @Test
    @DisplayName("수익률은 (총 상금 / 구매 금액) * 100을 소수점 한 자리로 반올림해 반환한다.")
    void 수익률은_총상금_대비_구매금액으로_계산된다() {
        Map<Lotto, Rank> map = new LinkedHashMap<>();
        map.put(lottoOf(1,2,3,4,5,6), Rank.FIFTH);
        map.put(lottoOf(7,8,9,10,11,12), Rank.FOURTH);

        LottoResult result = new LottoResult(map, new PurchaseAmount(8000));

        assertThat(result.getProfitRate()).isEqualTo(687.5);
    }

    @Test
    @DisplayName("getRankCounts()는 불변 맵을 반환한다.")
    void 등수_집계결과_맵은_불변이다() {
        Map<Lotto, Rank> map = new LinkedHashMap<>();
        map.put(lottoOf(1,2,3,4,5,6), Rank.FIRST);

        LottoResult result = new LottoResult(map, new PurchaseAmount(1000));

        assertThatThrownBy(() -> result.getRankCounts().put(Rank.SECOND, 10))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("모든 Rank에 대해 최소 0 이상의 집계 결과를 가진다.")
    void 모든_등수에_대한_집계결과는_0이상이다() {
        Map<Lotto, Rank> map = new LinkedHashMap<>();
        LottoResult result = new LottoResult(map, new PurchaseAmount(1000));

        for (Rank rank : Rank.values()) {
            assertThat(result.countByRank(rank)).isGreaterThanOrEqualTo(0);
        }
    }
}
