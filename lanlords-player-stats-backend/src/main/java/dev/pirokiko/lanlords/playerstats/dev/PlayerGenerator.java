package dev.pirokiko.lanlords.playerstats.dev;

import dev.pirokiko.lanlords.playerstats.config.EloSettings;
import dev.pirokiko.lanlords.playerstats.entity.AbilityEntity;
import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;
import dev.pirokiko.lanlords.playerstats.model.Ability;
import dev.pirokiko.lanlords.playerstats.model.Game;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PlayerGenerator {
  private final EloSettings settings = new EloSettings();

  List<PlayerEntity> generate(int low, int high) {
    final int count = (int) Math.floor(Math.random() * (high - low) + low);
    final var players = new ArrayList<PlayerEntity>();
    for (int i = 0; i < count; i++) {
      players.add(player());
    }
    return players;
  }

  String getName() {
    return String.format("Player%s", (int) Math.floor(Math.random() * 100000));
  }

  String getHandle() {
    return String.format("E-Player%s", (int) Math.floor(Math.random() * 100000));
  }

  AbilityEntity ability(Game game, Ability ability) {
    return AbilityEntity.builder()
        .ability(ability)
        .game(game)
        .rating(settings.getDefaultRating())
        .build();
  }

  PlayerEntity player() {
    final var player = new PlayerEntity();
    player.abilities(List.of(ability(Game.GENERIC, Ability.GENERAL)));
    player.name(getName());
    player.handle(getHandle());
    return player;
  }
}
