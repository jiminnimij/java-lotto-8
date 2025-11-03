package lotto.domain;

import lotto.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
    private Lotto lottoOf(Integer... nums) {
        return new Lotto(List.of(nums));
    }

    private LottoNumber num(int n) {
        return new LottoNumber(n);
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void 보너스번호가_당첨번호와_중복되면_예외가_발생한다() {
        Lotto winning = lottoOf(1, 2, 3, 4, 5, 6);
        LottoNumber bonus = num(6); // 당첨 번호와 중복

        assertThatThrownBy(() -> new WinningLotto(winning, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.DUPLICATE_BONUS.getMessage());
    }

    @Test
    @DisplayName("6개 일치 시 1등을 반환한다(보너스와 무관).")
    void 여섯개_일치하면_보너스와_무관하게_1등을_반환한다() {
        WinningLotto winning = new WinningLotto(lottoOf(1, 2, 3, 4, 5, 6), num(7));
        Lotto user = lottoOf(1, 2, 3, 4, 5, 6);

        Rank rank = winning.judge(user);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("5개 + 보너스 일치 시 2등을 반환한다.")
    void 다섯개와_보너스가_일치하면_2등을_반환한다() {
        WinningLotto winning = new WinningLotto(lottoOf(1, 2, 3, 4, 5, 6), num(7));
        Lotto user = lottoOf(1, 2, 3, 4, 5, 7); // 5개 일치 + 보너스

        Rank rank = winning.judge(user);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("5개만 일치(보너스 불일치) 시 3등을 반환한다.")
    void 다섯개만_일치하고_보너스는_불일치하면_3등을_반환한다() {
        WinningLotto winning = new WinningLotto(lottoOf(1, 2, 3, 4, 5, 6), num(7));
        Lotto user = lottoOf(1, 2, 3, 4, 5, 8); // 보너스 불일치

        Rank rank = winning.judge(user);

        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("4개 일치 시 4등을 반환한다.")
    void 네개_일치하면_4등을_반환한다() {
        WinningLotto winning = new WinningLotto(lottoOf(1, 2, 3, 4, 5, 6), num(7));
        Lotto user = lottoOf(1, 2, 3, 4, 8, 9);

        Rank rank = winning.judge(user);

        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("3개 일치 시 5등을 반환한다.")
    void 세개_일치하면_5등을_반환한다() {
        WinningLotto winning = new WinningLotto(lottoOf(1, 2, 3, 4, 5, 6), num(7));
        Lotto user = lottoOf(1, 2, 3, 8, 9, 10);

        Rank rank = winning.judge(user);

        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("2개 이하 일치 시 MISS를 반환한다.")
    void 두개_이하만_일치하면_MISS를_반환한다() {
        WinningLotto winning = new WinningLotto(lottoOf(1, 2, 3, 4, 5, 6), num(7));
        Lotto user = lottoOf(1, 2, 8, 9, 10, 11); // 2개 일치

        Rank rank = winning.judge(user);

        assertThat(rank).isEqualTo(Rank.MISS);
    }
}
