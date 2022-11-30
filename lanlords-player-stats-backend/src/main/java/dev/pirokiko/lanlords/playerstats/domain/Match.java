package dev.pirokiko.lanlords.playerstats.domain;

import dev.pirokiko.lanlords.playerstats.model.Game;
import lombok.NonNull;

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
}
