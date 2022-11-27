package dev.pirokiko.lanlords.playerstats.entity;

import dev.pirokiko.lanlords.playerstats.model.Game;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class RankingEntity {
  @Id private Long id;
  @ManyToOne private PlayerEntity player;
  @NotNull private int score;
  @Enumerated private Game game;
}
