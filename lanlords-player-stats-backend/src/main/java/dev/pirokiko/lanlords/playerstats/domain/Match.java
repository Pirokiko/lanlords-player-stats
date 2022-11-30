package dev.pirokiko.lanlords.playerstats.domain;

import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;
import dev.pirokiko.lanlords.playerstats.model.Game;
import dev.pirokiko.lanlords.playerstats.util.MappingIterator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public record Match(Game game, Team team1, Team team2, Team victor, boolean draw) {

  public boolean isWinner(@NonNull Team team) {
    return Objects.equals(team, victor);
  }

  public Team winner() {
    if (draw) return null;
    return victor;
  }

  public Team loser() {
    if (draw) return null;
    if (team1 == victor) return team2;
    return team1;
  }

  private Contributions evenContribution(final Team team) {
    return new Contributions(
        team.members().stream().collect(Collectors.toMap(Function.identity(), (k) -> 1.0)));
  }

  private Contributions randomContribution(final Team team) {
    return new Contributions(
        team.members().stream().collect(Collectors.toMap((k) -> k, (k) -> Math.random())));
  }

  public Contributions contribution(final Team team) {
    if (team != team1 && team != team2) {
      throw new IllegalArgumentException("Team " + team.name() + " is not part of this match");
    }

    // @TODO: implement determining actual contribution
    return Math.random() < 0.75 ? randomContribution(team) : evenContribution(team);
  }

  record Contribution(PlayerEntity player, Double contribution) {}

  @RequiredArgsConstructor
  static class Contributions implements Iterable<Contribution> {
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
  }
}
