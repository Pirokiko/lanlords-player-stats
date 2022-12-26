package dev.pirokiko.lanlords.playerstats.domain;

import dev.pirokiko.lanlords.playerstats.domain.elo.EloContext;
import dev.pirokiko.lanlords.playerstats.elo.EloEntity;
import dev.pirokiko.lanlords.playerstats.entity.AbilityEntity;
import dev.pirokiko.lanlords.playerstats.entity.OrganisationEntity;
import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;

import java.util.List;
import java.util.Optional;

/**
 * Represents a team as given shape during a tournament within a match. The EloContext allows for
 * switching the game or ability type to allow processors dynamic access.
 */
public record Team(OrganisationEntity team, List<PlayerEntity> members, EloContext eloContext)
    implements EloEntity {

  public String name() {
    return team.name();
  }

  private List<AbilityEntity> memberAbilities() {
    return members.stream()
        .map(member -> member.ability(eloContext.game(), eloContext.ability()))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .toList();
  }

  private AbilityEntity teamAbility() {
    return team.ability(eloContext.game(), eloContext.ability()).orElseThrow();
  }

  public double rating() {
    final var abilities = memberAbilities();
    if (abilities.isEmpty()) return 0;

    return abilities.stream().reduce(0d, (acc, ability) -> ability.rating(), Double::sum)
        / abilities.size();
  }

  public int numberOfGamesPlayed() {
    final var abilities = memberAbilities();
    if (abilities.isEmpty()) return 0;

    return abilities.stream().reduce(0, (acc, ability) -> ability.gamesPlayed(), Integer::sum)
        / abilities.size();
  }

  public void updateRating(double ratingOffset) {
    final var ability = teamAbility();
    ability.rating(ability.rating() + ratingOffset);
  }

  public void updateRating(double ratingOffset, Contributions contributions) {
    if (ratingOffset == 0) return;
    if (ratingOffset > 0) {
      contributions.applyRating(ratingOffset);
    } else {
      // inverse so more contributing players lose less
      contributions.inverse().applyRating(ratingOffset);
    }
  }

  public void incrementWinCount() {
    final var teamAbility = teamAbility();
    teamAbility.gamesWon(teamAbility.gamesWon() + 1); // This one or
    //    teamAbility.incrementGamesWon(); // is this beter?
    for (AbilityEntity ability : memberAbilities()) {
      ability.gamesWon(ability.gamesWon() + 1);
    }
  }

  public void incrementLossCount() {
    final var teamAbility = teamAbility();
    teamAbility.gamesLost(teamAbility.gamesLost() + 1); // This one or
    //    teamAbility.incrementGamesWon(); // is this beter?
    for (AbilityEntity ability : memberAbilities()) {
      ability.gamesLost(ability.gamesLost() + 1);
    }
  }

  public void incrementDrawCount() {
    final var teamAbility = teamAbility();
    teamAbility.gamesDrawn(teamAbility.gamesDrawn() + 1); // This one or
    //    teamAbility.incrementGamesWon(); // is this beter?
    for (AbilityEntity ability : memberAbilities()) {
      ability.gamesDrawn(ability.gamesDrawn() + 1);
    }
  }
}
