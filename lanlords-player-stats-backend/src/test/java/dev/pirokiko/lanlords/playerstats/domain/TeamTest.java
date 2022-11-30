package dev.pirokiko.lanlords.playerstats.domain;

import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;
import dev.pirokiko.lanlords.playerstats.entity.TeamEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.springframework.test.util.AssertionErrors.assertEquals;

class TeamTest {

  @Test
  void increaseOfRanking() {
    final var teamEntity = new TeamEntity();
    teamEntity.name("team");

    final var player1 = new PlayerEntity();
    player1.rating(750.0);
    final var player2 = new PlayerEntity();
    player2.rating(500.0);
    final var player3 = new PlayerEntity();
    player3.rating(250.0);

    final var team = new Team(teamEntity, List.of(player1, player2, player3));

    team.updateRating(100, new Match.Contributions(Map.of(player1, 1.0, player2, 1.0,player3, 3.0)));

    assertEquals("player1.rating()", 770.0, player1.rating());
    assertEquals("player2.rating()", 520.0, player2.rating());
    assertEquals("player3.rating()", 310.0, player3.rating());
  }

  @Test
  void decreaseOfRanking() {
    final var teamEntity = new TeamEntity();
    teamEntity.name("team");

    final var player1 = new PlayerEntity();
    player1.rating(750.0);
    final var player2 = new PlayerEntity();
    player2.rating(500.0);
    final var player3 = new PlayerEntity();
    player3.rating(250.0);

    final var team = new Team(teamEntity, List.of(player1, player2, player3));

    team.updateRating(-70, new Match.Contributions(Map.of(player1, 1.0, player2, 1.0,player3, 3.0)));


    assertEquals("player1.rating()", 720.0, player1.rating()); // -30
    assertEquals("player2.rating()", 470.0, player2.rating()); // -30
    assertEquals("player3.rating()", 240.0, player3.rating()); // -10
  }
}
