package dev.pirokiko.lanlords.playerstats.domain.dev;

import dev.pirokiko.lanlords.playerstats.domain.Team;
import dev.pirokiko.lanlords.playerstats.entity.TeamEntity;

import java.util.ArrayList;
import java.util.List;

public class TeamGenerator {

  private final PlayerGenerator playerGenerator = new PlayerGenerator();

  public List<Team> generate(int amount) {
    final var teams = new ArrayList<Team>();
    for (int i = 0; i < amount; i++) {
      teams.add(team(i));
    }
    return teams;
  }

  Team team(int i) {
    final var team = teamEntity(i);
    return new Team(team, playerGenerator.generate(team, 5, 9));
  }

  String getName() {
    return String.format("Team%s", (int) Math.floor(Math.random() * 1000));
  }

  TeamEntity teamEntity(int i) {
    final var team = new TeamEntity();
    team.id((long) i);
    team.name(getName());
    return team;
  }
}
