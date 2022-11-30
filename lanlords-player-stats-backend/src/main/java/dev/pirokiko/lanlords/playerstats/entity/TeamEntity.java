package dev.pirokiko.lanlords.playerstats.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Accessors(fluent = true)
public class TeamEntity {
  @Id private Long id;
  private String name;

  private int winCount;
  private int lossCount;
  private int drawCount;

  public void incrementWinCount() {
    winCount += 1;
  }

  public void incrementLossCount() {
    lossCount += 1;
  }

  public void incrementDrawCount() {
    drawCount += 1;
  }
}
