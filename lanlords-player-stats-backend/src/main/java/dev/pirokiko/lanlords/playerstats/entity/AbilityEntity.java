package dev.pirokiko.lanlords.playerstats.entity;

import dev.pirokiko.lanlords.playerstats.model.AbilityType;
import dev.pirokiko.lanlords.playerstats.model.Game;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.Accessors;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Data
@Accessors(fluent = true)
public class AbilityEntity {
  @Id private Long id;
  private Game game;

  @Enumerated(EnumType.STRING)
  private AbilityType type;

  private double rating;
}
