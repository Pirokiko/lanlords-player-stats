package dev.pirokiko.lanlords.playerstats.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

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
