package lotto.view;

public class OutputView {
    public void promptAmount() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void promptWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public void promptBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
