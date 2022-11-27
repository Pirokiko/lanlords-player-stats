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

  public void updateRating(double ratingOffset) {
    // Distribute over team based on some defined distribution
    double evenDistribution = ratingOffset / members().size();

    for (PlayerEntity member : members()) {
      member.updateRating(evenDistribution);
    }
  }

  public void incrementWinCount(){
    team.incrementWinCount();
    for (PlayerEntity member : members()) {
      member.incrementWinCount();
    }
  }

  public void incrementLossCount(){
    team.incrementLossCount();
    for (PlayerEntity member : members()) {
      member.incrementLossCount();
    }
  }

  public void incrementDrawCount(){
    team.incrementDrawCount();
    for (PlayerEntity member : members()) {
      member.incrementDrawCount();
    }
  }
}
