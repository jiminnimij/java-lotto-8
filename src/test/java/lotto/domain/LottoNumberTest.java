package lotto.domain;

import lotto.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @Test
    @DisplayName("1 이상 45 이하의 숫자는 정상적으로 LottoNumber가 생성된다.")
    void 유효한_숫자는_정상적으로_생성된다() {
        LottoNumber lottoNumber = new LottoNumber(10);

        assertThat(lottoNumber.getNumber()).isEqualTo(10);
    }

    @Test
    @DisplayName("숫자가 1보다 작으면 예외가 발생한다.")
    void 숫자가_1보다_작으면_예외가_발생한다() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.INVALID_LOTTO_RANGE.getMessage());
    }

    @Test
    @DisplayName("숫자가 45보다 크면 예외가 발생한다.")
    void 숫자가_45보다_크면_예외가_발생한다() {
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.INVALID_LOTTO_RANGE.getMessage());
    }

    @Test
    @DisplayName("같은 숫자는 equals() 비교 시 true를 반환한다.")
    void 같은_숫자는_equals가_true를_반환한다() {
        LottoNumber num1 = new LottoNumber(7);
        LottoNumber num2 = new LottoNumber(7);

        assertThat(num1).isEqualTo(num2);
        assertThat(num1.hashCode()).isEqualTo(num2.hashCode());
    }

    @Test
    @DisplayName("다른 숫자는 equals() 비교 시 false를 반환한다.")
    void 다른_숫자는_equals가_false를_반환한다() {
        LottoNumber num1 = new LottoNumber(1);
        LottoNumber num2 = new LottoNumber(2);

        assertThat(num1).isNotEqualTo(num2);
    }

    @Test
    @DisplayName("compareTo()는 숫자 크기에 따라 올바른 순서를 반환한다.")
    void compareTo는_숫자_크기를_기준으로_정렬된다() {
        LottoNumber smaller = new LottoNumber(3);
        LottoNumber larger = new LottoNumber(10);

        assertThat(smaller.compareTo(larger)).isLessThan(0);
        assertThat(larger.compareTo(smaller)).isGreaterThan(0);
        assertThat(smaller.compareTo(new LottoNumber(3))).isZero();
    }

    @Test
    @DisplayName("toString()은 숫자를 문자열로 반환한다.")
    void toString은_숫자를_문자열로_반환한다() {
        LottoNumber number = new LottoNumber(15);

        assertThat(number.toString()).isEqualTo("15");
    }

}
