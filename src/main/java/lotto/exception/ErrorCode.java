package lotto.exception;

public enum ErrorCode {
    INVALID_NUMBER("숫자를 입력해야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
