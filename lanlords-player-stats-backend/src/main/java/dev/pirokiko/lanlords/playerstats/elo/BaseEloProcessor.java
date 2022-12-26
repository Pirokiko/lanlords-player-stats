package dev.pirokiko.lanlords.playerstats.elo;

import dev.pirokiko.lanlords.playerstats.config.EloSettings;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseEloProcessor<T extends EloEntity> implements EloProcessor<T> {
  private final EloSettings settings;

  @Override
  public void process(EloMatches<T> competition) {
    for (EloMatch<T> eloMatch : competition) {
      process(eloMatch);
    }
  }

  private void process(EloMatch<T> eloMatch) {
    final var team1 = eloMatch.elo1();
    final var team2 = eloMatch.elo2();

    double firstRatingOffset = this.newRating(eloMatch, team1, team2) - team1.rating();
    double secondRatingOffset = this.newRating(eloMatch, team2, team1) - team2.rating();
    team1.updateRating(firstRatingOffset);
    team2.updateRating(secondRatingOffset);
  }

  private double transformedRating(T team) {
    return Math.pow(10, (team.rating() / 400));
  }

  private double expectedScore(T firstTeam, T secondTeam) {
    double firstTransformedRating = this.transformedRating(firstTeam);
    double secondTransformedRating = this.transformedRating(secondTeam);
    return firstTransformedRating / (firstTransformedRating + secondTransformedRating);
  }

  private double newRating(EloMatch<T> match, T primaryTeam, T otherTeam) {
    final var scoreDiff =
        this.getScore(primaryTeam, match) - this.expectedScore(primaryTeam, otherTeam);
    return primaryTeam.rating() + this.kFactor(primaryTeam) * scoreDiff;
  }

  private double getScore(T team, EloMatch<T> match) {
    if (match.draw()) return 0.5;

    if (match.isWinner(team)) return 1;
    return 0;
  }

  private int kFactor(T team) {
    if (team.numberOfGamesPlayed() < settings.getStarterBoundry()) return 25;
    if (team.rating() < settings.getProRatingBoundry()) return 15;
    return settings.getDefaultKFactor();
  }
}
