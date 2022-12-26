package dev.pirokiko.lanlords.playerstats.domain;

import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;
import dev.pirokiko.lanlords.playerstats.util.MappingIterator;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Contributions implements Iterable<Contributions.Contribution> {
    public record Contribution(PlayerEntity player, Double contribution) {}

    // Change to List<Contribution> ??? fewer problems for changing values
    private final Map<PlayerEntity, Double> contributions;

    public Contributions normalized() {
        final var total = contributions.values().stream().mapToDouble(v -> v).sum();
    return new Contributions(
        contributions.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() / total)));
    }

    public Contributions inverse() {
    return new Contributions(
        contributions.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, e -> 1.0 / e.getValue())));
    }

    @Override
    public Iterator<Contribution> iterator() {
        return new MappingIterator<>(
                contributions.entrySet().iterator(), e -> new Contribution(e.getKey(), e.getValue()));
    }

  public void applyRating(double ratingOffset) {
    this.normalized() // ensure all are proportionally scaled for a total value of 1.0
        .forEach(
            (contribution) -> {
              final var member = contribution.player();
              final var ability =
                  member.ability(eloContext.game(), eloContext.ability()).orElseThrow();
              final var contributionPart = contribution.contribution();
              ability.rating(ability.rating() + ratingOffset * contributionPart);
            });
  }
}
