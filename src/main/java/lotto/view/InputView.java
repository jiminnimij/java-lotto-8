package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.ErrorCode;

import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String DELIMITER = ",";

    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();

        return parseInteger(input);
    }

    public List<Integer> readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();

        return splitNumbers(input);
    }

    public int readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine();

        return parseInteger(input);
    }

    public List<Integer> splitNumbers(String input) {
        String[] winningNumbers = input.split(DELIMITER);

        return Arrays.stream(winningNumbers).map(this::parseInteger).toList();
    }

    public int parseInteger(String input) {
        try {
            input = input.trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.INVALID_NUMBER.getMessage());
        }
    }

}
