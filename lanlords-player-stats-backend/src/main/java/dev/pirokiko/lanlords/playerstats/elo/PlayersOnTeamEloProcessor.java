package dev.pirokiko.lanlords.playerstats.elo;

import dev.pirokiko.lanlords.playerstats.config.EloSettings;
import dev.pirokiko.lanlords.playerstats.domain.elo.Match;
import dev.pirokiko.lanlords.playerstats.domain.elo.PlayersOnTeam;

import java.util.List;
import java.util.Objects;

public class PlayersOnTeamEloProcessor extends BaseEloProcessor<PlayersOnTeam>
    implements EloProcessor<PlayersOnTeam> {

  public PlayersOnTeamEloProcessor(EloSettings settings) {
    super(settings);
  }

  @Override
  public EloMatches<PlayersOnTeam> eloMatchesFrom(
      dev.pirokiko.lanlords.playerstats.domain.Match match) {
    final PlayersOnTeam team1 =
        new PlayersOnTeam(match.team1().members(), match.contribution(match.team1()));
    final PlayersOnTeam team2 =
        new PlayersOnTeam(match.team2().members(), match.contribution(match.team2()));

    final var winner =
        Objects.equals(match.winner(), match.team1())
            ? team1
            : (Objects.equals(match.winner(), match.team2()) ? team2 : null);

    return new EloMatches<>(List.of(new Match<>(team1, team2, winner)));
  }
}
