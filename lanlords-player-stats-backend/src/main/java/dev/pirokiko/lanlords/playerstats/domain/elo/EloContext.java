package dev.pirokiko.lanlords.playerstats.domain.elo;

import dev.pirokiko.lanlords.playerstats.model.Ability;
import dev.pirokiko.lanlords.playerstats.model.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@AllArgsConstructor
public class EloContext {
  private Game game;
  private Ability ability;
}
