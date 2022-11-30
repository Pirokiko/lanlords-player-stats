package dev.pirokiko.lanlords.playerstats.domain;

import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;
import dev.pirokiko.lanlords.playerstats.entity.TeamEntity;

import java.util.List;

public record Team(TeamEntity team, List<PlayerEntity> members) {

  public String name() {
    return team.name();
  }

  public double rating() {
    return members.stream().reduce(0d, (acc, member) -> member.rating(), Double::sum)
        / members.size();
  }

  public int numberOfGamesPlayed() {
    return members.stream().reduce(0, (acc, member) -> member.numberOfGamesPlayed(), Integer::sum)
        / members.size();
  }

  public void updateRating(double ratingOffset, Match.Contributions contributions) {
    if (ratingOffset == 0) return;
    if (ratingOffset > 0) {
      applyRating(ratingOffset, contributions);
    } else {
      // inverse so more contributing members lose less
      applyRating(ratingOffset, contributions.inverse());
    }
  }

  // @TODO: Determine if this code (and callers) should still be here as it doesn't use the instance
  private void applyRating(double ratingOffset, Match.Contributions contributions) {
    contributions
        .normalized() // ensure all are proportionally scaled for a total value of 1.0
        .forEach(
            (contribution) -> {
              final var member = contribution.player();
              final var contributionPart = contribution.contribution();
              member.updateRating(ratingOffset * contributionPart);
            });
  }

  public void incrementWinCount() {
    team.incrementWinCount();
    for (PlayerEntity member : members()) {
      member.incrementWinCount();
    }
  }

  public void incrementLossCount() {
    team.incrementLossCount();
    for (PlayerEntity member : members()) {
      member.incrementLossCount();
    }
  }

  public void incrementDrawCount() {
    team.incrementDrawCount();
    for (PlayerEntity member : members()) {
      member.incrementDrawCount();
    }
  }
}
