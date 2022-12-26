package dev.pirokiko.lanlords.playerstats.domain;

import dev.pirokiko.lanlords.playerstats.domain.elo.EloContext;
import dev.pirokiko.lanlords.playerstats.entity.AbilityEntity;
import dev.pirokiko.lanlords.playerstats.entity.OrganisationEntity;
import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;
import dev.pirokiko.lanlords.playerstats.model.Ability;
import dev.pirokiko.lanlords.playerstats.model.Game;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.springframework.test.util.AssertionErrors.assertEquals;

class TeamTest {

  PlayerEntity player(double rating) {
    final var player = new PlayerEntity();
    final var ability =
        AbilityEntity.builder().rating(rating).game(Game.GENERIC).ability(Ability.GENERAL).build();
    player.abilities(List.of(ability));
    return player;
  }

  @Test
  void increaseOfRanking() {
    final var teamEntity = new OrganisationEntity();
    teamEntity.name("team");

    final var player1 = player(750);
    final var player2 = player(500);
    final var player3 = player(250);

    final var team =
        new Team(
            teamEntity,
            List.of(player1, player2, player3),
            new EloContext(Game.GENERIC, Ability.GENERAL));

    team.updateRating(100, new Contributions(Map.of(player1, 1.0, player2, 1.0, player3, 3.0)));

    assertEquals(
        "ability1.rating()",
        770.0,
        player1.ability(Game.GENERIC, Ability.GENERAL).map(AbilityEntity::rating).orElse(0.0));
    assertEquals(
        "ability2.rating()",
        520.0,
        player2.ability(Game.GENERIC, Ability.GENERAL).map(AbilityEntity::rating).orElse(0.0));
    assertEquals(
        "ability3.rating()",
        310.0,
        player3.ability(Game.GENERIC, Ability.GENERAL).map(AbilityEntity::rating).orElse(0.0));
  }

  @Test
  void decreaseOfRanking() {
    final var teamEntity = new OrganisationEntity();
    teamEntity.name("team");

    final var player1 = player(750);
    final var player2 = player(500);
    final var player3 = player(250);

    final var team =
        new Team(
            teamEntity,
            List.of(player1, player2, player3),
            new EloContext(Game.GENERIC, Ability.GENERAL));

    team.updateRating(-70, new Contributions(Map.of(player1, 1.0, player2, 1.0, player3, 3.0)));

    assertEquals(
        "player1.rating()",
        720.0,
        player1
            .ability(Game.GENERIC, Ability.GENERAL)
            .map(AbilityEntity::rating)
            .orElse(0.0)); // -30
    assertEquals(
        "player2.rating()",
        470.0,
        player2
            .ability(Game.GENERIC, Ability.GENERAL)
            .map(AbilityEntity::rating)
            .orElse(0.0)); // -30
    assertEquals(
        "player3.rating()",
        240.0,
        player3
            .ability(Game.GENERIC, Ability.GENERAL)
            .map(AbilityEntity::rating)
            .orElse(0.0)); // -10
  }
}
