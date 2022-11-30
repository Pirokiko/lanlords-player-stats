package dev.pirokiko.lanlords.playerstats.domain.elo;

import dev.pirokiko.lanlords.playerstats.domain.Contributions;
import dev.pirokiko.lanlords.playerstats.elo.EloEntity;
import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;

import java.util.List;

public record PlayersOnTeam(List<PlayerEntity> players, Contributions contributions) implements EloEntity {

  public double rating() {
    return players.stream().reduce(0d, (acc, member) -> member.rating(), Double::sum)
        / players.size();
  }

  public int numberOfGamesPlayed() {
    return players.stream().reduce(0, (acc, member) -> member.numberOfGamesPlayed(), Integer::sum)
        / players.size();
  }

  public void updateRating(double ratingOffset) {
    if (ratingOffset == 0) return;
    if (ratingOffset > 0) {
      applyRating(ratingOffset, contributions);
    } else {
      // inverse so more contributing players lose less
      applyRating(ratingOffset, contributions.inverse());
    }
  }

  private void applyRating(double ratingOffset, Contributions contributions) {
    contributions
        .normalized() // ensure all are proportionally scaled for a total value of 1.0
        .forEach(
            (contribution) -> {
              final var member = contribution.player();
              final var contributionPart = contribution.contribution();
              member.updateRating(ratingOffset * contributionPart);
            });
  }
}
