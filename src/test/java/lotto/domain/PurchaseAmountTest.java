package lotto.domain;

import lotto.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PurchaseAmountTest {
    @Test
    @DisplayName("0 이하의 금액이 입력되면 예외가 발생한다.")
    void 음수_또는_0원_입력시_예외() {
        assertThatThrownBy(() -> new PurchaseAmount(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.INVALID_POSITIVE_NUMBER.getMessage());

        assertThatThrownBy(() -> new PurchaseAmount(-5000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.INVALID_POSITIVE_NUMBER.getMessage());
    }

    @Test
    @DisplayName("1000원 단위가 아닌 금액이 입력되면 예외가 발생한다.")
    void 천원단위_아닌_금액이면_예외() {
        assertThatThrownBy(() -> new PurchaseAmount(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.INVALID_AMOUNT.getMessage());

        assertThatThrownBy(() -> new PurchaseAmount(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.INVALID_AMOUNT.getMessage());
    }

    @Test
    @DisplayName("1000원 단위의 양수 금액이면 정상적으로 생성된다.")
    void 유효한_금액이면_정상_생성() {
        PurchaseAmount amount = new PurchaseAmount(8000);

        assertThat(amount.getAmount()).isEqualTo(8000);
        assertThat(amount.getCount()).isEqualTo(8); // 8000 / 1000
    }

}
