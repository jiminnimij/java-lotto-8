package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

import java.util.List;

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

    public void printLottos(List<Lotto> lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach(lotto -> System.out.println(lotto.getNumbers()));
        System.out.println();
    }

    public void printResults(LottoResult lottoResult) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        System.out.println("3개 일치 (" + money(resultPrize(Rank.FIFTH)) + ") - " + lottoResult.countByRank(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (" + money(resultPrize(Rank.FOURTH)) + ") - " + lottoResult.countByRank(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (" + money(resultPrize(Rank.THIRD)) + ") - " + lottoResult.countByRank(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (" + money(resultPrize(Rank.SECOND)) + ") - " + lottoResult.countByRank(Rank.SECOND) + "개");
        System.out.println("6개 일치 (" + money(resultPrize(Rank.FIRST)) + ") - " + lottoResult.countByRank(Rank.FIRST) + "개");

        System.out.println("총 수익률은 " + String.format("%.1f", lottoResult.getProfitRate()) + "%입니다.");
    }

    private long resultPrize(Rank rank) {
        return rank.prize();
    }

    private String money(long won) {
        return String.format("%,d원", won);
    }
}
