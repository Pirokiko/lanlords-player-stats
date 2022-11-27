package dev.pirokiko.lanlords.playerstats.domain.dev;

import dev.pirokiko.lanlords.playerstats.domain.config.EloSettings;
import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;
import dev.pirokiko.lanlords.playerstats.entity.TeamEntity;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PlayerGenerator {
  private final EloSettings settings = new EloSettings();

  List<PlayerEntity> generate(TeamEntity team, int low, int high) {
    final int count = (int) Math.floor(Math.random() * (high - low) + low);
    final var players = new ArrayList<PlayerEntity>();
    for (int i = 0; i < count; i++) {
      players.add(player(team));
    }
    return players;
  }

  String getName() {
    return String.format("Player%s", (int) Math.floor(Math.random() * 100000));
  }

  String getHandle() {
    return String.format("E-Player%s", (int) Math.floor(Math.random() * 100000));
  }

  PlayerEntity player(TeamEntity team) {
    final var player = new PlayerEntity();
    player.rating(settings.getDefaultRating());
    player.team(team);
    player.name(getName());
    player.handle(getHandle());
    return player;
  }
}
