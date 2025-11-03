package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final Map<Lotto, Rank> results;
    private final Map<Rank, Integer> rankCounts;
    private final PurchaseAmount purchaseAmount;

    public LottoResult(Map<Lotto, Rank> results, PurchaseAmount purchaseAmount) {
        this.results = Map.copyOf(results);
        this.rankCounts = aggregate(results);
        this.purchaseAmount = purchaseAmount;
    }

    private Map<Rank, Integer> aggregate(Map<Lotto, Rank> results) {
        Map<Rank, Integer> stats = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            stats.put(rank, 0);
        }
        for (Rank rank : results.values()) {
            stats.put(rank, stats.get(rank) + 1);
        }
        return Collections.unmodifiableMap(stats);
    }

    public long countByRank(Rank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    public long getTotalPrize() {
        return results.values().stream()
                .mapToLong(Rank::prize)
                .sum();
    }

    public double getProfitRate() {
        double profit = (double) getTotalPrize() / purchaseAmount.getAmount() * 100;
        return Math.round(profit * 10) / 10.0;
    }

    public Map<Rank, Integer> getRankCounts() {
        return rankCounts;
    }

}
