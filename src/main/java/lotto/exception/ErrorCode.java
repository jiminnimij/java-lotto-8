package lotto.exception;

public enum ErrorCode {
    INVALID_NUMBER("숫자를 입력해야 합니다."),
    NULL_INPUT("입력이 null일 수 없습니다."),
    INVALID_AMOUNT("구입 금액은 1,000원 단위여야 합니다."),
    INVALID_POSITIVE_NUMBER("입력값은 양수여야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
