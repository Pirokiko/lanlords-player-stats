package dev.pirokiko.lanlords.playerstats.elo;

import dev.pirokiko.lanlords.playerstats.config.EloSettings;
import dev.pirokiko.lanlords.playerstats.domain.Match;
import dev.pirokiko.lanlords.playerstats.domain.Team;

import java.util.List;

public class GeneralTeamEloProcessor extends BaseEloProcessor<Team> implements EloProcessor<Team> {

  public GeneralTeamEloProcessor(EloSettings settings) {
    super(settings);
  }

  @Override
  public EloMatches<Team> eloMatchesFrom(Match match) {
    return new EloMatches<>(
        List.of(
            new dev.pirokiko.lanlords.playerstats.domain.elo.Match<>(
                match.team1(), match.team2(), match.winner())));
  }
}
