package dev.pirokiko.lanlords.playerstats.elo;

import dev.pirokiko.lanlords.playerstats.domain.Match;

public interface EloProcessor<T extends EloEntity> {
  EloMatches<T> eloMatchesFrom(final Match match);

  //  void process(final EloMatches<T> competition);

  void process(EloMatches<T> competition);
}
