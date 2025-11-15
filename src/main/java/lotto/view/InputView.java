package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.ErrorCode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class InputView {
    private static final String DELIMITER = ",";

    public int readNumber() {
        return readValidatedInput(this::parseInteger);
    }

    public List<Integer> readNumbers() {
        return readValidatedInput(this::splitNumbers);
    }

    public <T> T readWithRetry(Supplier<T> reader, Consumer<String> onError) {
        while (true) {
            try { return reader.get(); }
            catch (IllegalArgumentException e) { onError.accept(e.getMessage()); }
        }
    }

    private <T> T readValidatedInput(Function<String, T> parser) {
        String input = Console.readLine();
        String trimmedInput = validateInput(input);
        return parser.apply(trimmedInput);
    }

    private List<Integer> splitNumbers(String input) {
        String[] winningNumbers = input.split(DELIMITER);

        return Arrays.stream(winningNumbers).map(this::parseInteger).toList();
    }

    private int parseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.INVALID_NUMBER.getMessage());
        }
    }

    private String validateInput(String input) {
        try {
            return input.trim();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(ErrorCode.NULL_INPUT.getMessage());
        }
    }

}
