package dev.pirokiko.lanlords.playerstats.elo;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public record EloMatches<T extends EloEntity>(List<EloMatch<T>> matches)
    implements Iterable<EloMatch<T>> {
  @Override
  public @NotNull Iterator<EloMatch<T>> iterator() {
    return matches.iterator();
  }
}
