package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.ErrorCode;

import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String DELIMITER = ",";

    public int readPurchaseAmount() {
        String input = Console.readLine();
        validateInput(input);

        return parseInteger(input);
    }

    public List<Integer> readWinningNumbers() {
        String input = Console.readLine();
        validateInput(input);

        return splitNumbers(input);
    }

    public int readBonusNumber() {
        String input = Console.readLine();
        validateInput(input);

        return parseInteger(input);
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

    private void validateInput(String input) {
        try {
            input = input.trim();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(ErrorCode.NULL_INPUT.getMessage());
        }
    }

}
