package dev.pirokiko.lanlords.playerstats.domain;

import dev.pirokiko.lanlords.playerstats.config.EloSettings;
import dev.pirokiko.lanlords.playerstats.domain.elo.EloContext;
import dev.pirokiko.lanlords.playerstats.domain.elo.Match;
import dev.pirokiko.lanlords.playerstats.domain.elo.PlayersOnTeam;
import dev.pirokiko.lanlords.playerstats.elo.BaseEloProcessor;
import dev.pirokiko.lanlords.playerstats.elo.EloMatches;
import dev.pirokiko.lanlords.playerstats.elo.EloProcessor;

import java.util.List;
import java.util.Objects;

public class PlayersOnTeamEloProcessor extends BaseEloProcessor<PlayersOnTeam>
    implements EloProcessor<PlayersOnTeam> {

  private final EloContext eloContext;

  public PlayersOnTeamEloProcessor(EloSettings settings, EloContext eloContext) {
    super(settings);
    this.eloContext = eloContext;
  }

  @Override
  public EloMatches<PlayersOnTeam> eloMatchesFrom(
      dev.pirokiko.lanlords.playerstats.domain.Match match) {
    final PlayersOnTeam team1 =
        new PlayersOnTeam(match.team1().members(), match.contribution(match.team1()), eloContext);
    final PlayersOnTeam team2 =
        new PlayersOnTeam(match.team2().members(), match.contribution(match.team2()), eloContext);

    final var winner =
        Objects.equals(match.winner(), match.team1())
            ? team1
            : (Objects.equals(match.winner(), match.team2()) ? team2 : null);

    return new EloMatches<>(List.of(new Match<>(team1, team2, winner)));
  }
}
