package dev.pirokiko.lanlords.playerstats.dev;

import dev.pirokiko.lanlords.playerstats.domain.Team;
import dev.pirokiko.lanlords.playerstats.domain.elo.EloContext;
import dev.pirokiko.lanlords.playerstats.entity.OrganisationEntity;

import java.util.ArrayList;
import java.util.List;

public class TeamGenerator {

  private final PlayerGenerator playerGenerator = new PlayerGenerator();

  public List<Team> generate(int amount, EloContext eloContext) {
    final var teams = new ArrayList<Team>();
    for (int i = 0; i < amount; i++) {
      teams.add(team(i, eloContext));
    }
    return teams;
  }

  Team team(int i, EloContext eloContext) {
    final var team = teamEntity(i);
    return new Team(team, playerGenerator.generate(5, 9), eloContext);
  }

  String getName() {
    return String.format("Team%s", (int) Math.floor(Math.random() * 1000));
  }

  OrganisationEntity teamEntity(int i) {
    final var team = new OrganisationEntity();
    team.id((long) i);
    team.name(getName());
    return team;
  }
}
