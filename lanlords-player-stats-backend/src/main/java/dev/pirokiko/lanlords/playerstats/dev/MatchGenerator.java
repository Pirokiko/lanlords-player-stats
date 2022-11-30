package dev.pirokiko.lanlords.playerstats.dev;

import dev.pirokiko.lanlords.playerstats.domain.Match;
import dev.pirokiko.lanlords.playerstats.domain.Team;
import dev.pirokiko.lanlords.playerstats.model.Game;

import java.util.List;

public class MatchGenerator {
  private final List<Team> teams;

  public MatchGenerator(List<Team> teams) {
    this.teams = teams;
  }

  Game game(int i) {
    final var g = i % 7;
    return switch (g) {
      case 0 -> Game.BF1942;
      case 1 -> Game.BF2142;
      case 2 -> Game.COD2;
      case 3 -> Game.COD4;
      case 4 -> Game.COD5;
      case 5 -> Game.TF2;
      case 6 -> Game.CSGO;
      default -> null;
    };
  }

  Team getTeam(int i) {
    final int index = i % teams.size();
    return teams.get(index);
  }

  Team getTeam(Team otherTeam) {
    Team team = otherTeam;
    while (otherTeam.equals(team)) {
      final var index = (int) Math.floor(Math.random() * teams.size());
      team = teams.get(index);
    }
    return team;
  }

  public Match generateMatch(int i) {
    final var game = game(i);
    final var team1 = getTeam(i);
    final var team2 = getTeam(team1);

    final var isDraw = Math.random() < 0.5;
    if (isDraw) {
      return new Match(game, team1, team2, null, isDraw);
    }

    if (Math.random() < 0.5) {
      return new Match(game, team1, team2, team1, isDraw);
    } else {
      return new Match(game, team1, team2, team2, isDraw);
    }
  }
}
