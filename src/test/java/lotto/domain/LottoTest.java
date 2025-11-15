package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 개수가 6개 미만이면 예외가 발생한다.")
    @Test
    void 로또_번호의_개수가_6개보다_작으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효한 6개의 번호로 로또를 생성하면 예외가 발생하지 않는다.")
    @Test
    void 유효한_번호로_로또를_생성하면_정상적으로_생성된다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.getNumbers()).hasSize(6);
        assertThat(lotto.getNumbers())
                .extracting(LottoNumber::getNumber)
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("로또 번호는 생성 시 자동으로 오름차순 정렬된다.")
    @Test
    void 로또_번호는_자동으로_정렬된다() {
        Lotto lotto = new Lotto(List.of(6, 1, 3, 5, 2, 4));

        List<Integer> sorted = lotto.getNumbers().stream()
                .map(LottoNumber::getNumber)
                .toList();

        assertThat(sorted).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("로또가 특정 숫자를 포함하고 있으면 true를 반환한다.")
    @Test
    void 로또가_특정_숫자를_포함하고_있으면_true() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.contains(3)).isTrue();
    }

    @DisplayName("로또가 특정 숫자를 포함하고 있지 않으면 false를 반환한다.")
    @Test
    void 로또가_특정_숫자를_포함하고_있지_않으면_false() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.contains(10)).isFalse();
    }
}
