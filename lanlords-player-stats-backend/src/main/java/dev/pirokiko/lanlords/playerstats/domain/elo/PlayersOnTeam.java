package dev.pirokiko.lanlords.playerstats.domain.elo;

import dev.pirokiko.lanlords.playerstats.domain.Contributions;
import dev.pirokiko.lanlords.playerstats.elo.EloEntity;
import dev.pirokiko.lanlords.playerstats.entity.AbilityEntity;
import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;

import java.util.List;
import java.util.Optional;

public record PlayersOnTeam(
    List<PlayerEntity> players, Contributions contributions, EloContext eloContext)
    implements EloEntity {

  public List<AbilityEntity> abilities() {
    return players.stream()
        .map(player -> player.ability(eloContext.game(), eloContext.ability()))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .toList();
  }

  public double rating() {
    final var abilities = abilities();
    if (abilities.isEmpty()) return 0d;

    return abilities.stream().reduce(0d, (acc, ability) -> ability.rating(), Double::sum)
        / abilities.size();
  }

  public int numberOfGamesPlayed() {
    final var abilities = abilities();
    if (abilities.isEmpty()) return 0;

    return abilities.stream().reduce(0, (acc, ability) -> ability.gamesPlayed(), Integer::sum)
        / abilities.size();
  }

  public void updateRating(double ratingOffset) {
    if (ratingOffset == 0) return;
    if (ratingOffset > 0) {
      contributions.applyRating(ratingOffset);
    } else {
      // inverse so more contributing players lose less
      contributions.inverse().applyRating(ratingOffset);
    }
  }
}
