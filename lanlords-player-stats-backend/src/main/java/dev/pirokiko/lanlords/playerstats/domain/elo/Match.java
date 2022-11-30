package dev.pirokiko.lanlords.playerstats.domain.elo;

import dev.pirokiko.lanlords.playerstats.elo.EloEntity;
import dev.pirokiko.lanlords.playerstats.elo.EloMatch;

import java.util.Objects;

public record Match<T extends EloEntity>(T elo1, T elo2, T victor) implements EloMatch<T> {
  @Override
  public boolean draw() {
    return victor == null;
  }

  @Override
  public boolean isWinner(T elo) {
    return Objects.equals(elo, victor);
  }
}
