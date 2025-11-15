package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

import java.util.Arrays;
import java.util.Comparator;
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

        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.MISS)
                .sorted(Comparator.comparingInt(Rank::getPrize))
                .forEach(rank -> System.out.println(formatRankLine(rank, lottoResult.countByRank(rank))));

        System.out.println("총 수익률은 " + String.format("%.1f", lottoResult.getProfitRate()) + "%입니다.");
    }

    private String formatRankLine(Rank rank, long count) {
        StringBuilder sb = new StringBuilder();

        sb.append(rank.getMatch()).append("개 일치");

        if (rank.isNeedBonus()) {
            sb.append(", 보너스 볼 일치");
        }

        sb.append(" (").append(money(rank.getPrize())).append(") - ")
                .append(count).append("개");

        return sb.toString();
    }

    private String money(long won) {
        return String.format("%,d원", won);
    }
}
