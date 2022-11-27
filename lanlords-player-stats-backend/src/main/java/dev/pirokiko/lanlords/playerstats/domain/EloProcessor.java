package dev.pirokiko.lanlords.playerstats.domain;

import dev.pirokiko.lanlords.playerstats.domain.config.EloSettings;

public class EloProcessor {
  private final EloSettings settings;

  public EloProcessor(EloSettings settings) {
    this.settings = settings;
  }

  /** Public API call. */
  public void process(final Match match) {
    final var team1 = match.team1();
    final var team2 = match.team2();

    double firstRatingOffset = this.newRating(match, team1, team2) - team1.rating();
    double secondRatingOffset = this.newRating(match, team2, team1) - team2.rating();
    team1.updateRating(firstRatingOffset);
    team2.updateRating(secondRatingOffset);
    updateWinLossCounts(match);
  }

  double transformedRating(Team team) {
    return Math.pow(10, (team.rating() / 400));
  }

  double expectedScore(Team firstTeam, Team secondTeam) {
    double firstTransformedRating = this.transformedRating(firstTeam);
    double secondTransformedRating = this.transformedRating(secondTeam);
    return firstTransformedRating / (firstTransformedRating + secondTransformedRating);
  }

  double newRating(Match match, Team primaryTeam, Team otherTeam) {
    final var scoreDiff =
        this.getScore(primaryTeam, match) - this.expectedScore(primaryTeam, otherTeam);
    return primaryTeam.rating() + this.kFactor(primaryTeam) * scoreDiff;
  }

  double getScore(Team team, Match match) {
    if (match.draw()) return 0.5;

    if (match.isWinner(team)) return 1;
    return 0;
  }

  int kFactor(Team team) {
    if (team.numberOfGamesPlayed() < settings.getStarterBoundry()) return 25;
    if (team.rating() < settings.getProRatingBoundry()) return 15;
    return settings.getDefaultKFactor();
  }

  void updateWinLossCounts(Match match) {
    final var winner = match.winner();
    final var loser = match.loser();

    if (winner == null || loser == null) {
      match.team1().incrementDrawCount();
      match.team2().incrementDrawCount();
    } else {
      winner.incrementWinCount();
      loser.incrementLossCount();
    }
  }
}
