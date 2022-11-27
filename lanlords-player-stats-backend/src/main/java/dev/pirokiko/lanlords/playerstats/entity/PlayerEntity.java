package dev.pirokiko.lanlords.playerstats.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(fluent = true)
public class PlayerEntity {
  @Id private Long id;
  private String name;
  private String handle;

  private int winCount;
  private int lossCount;
  private int drawCount;
  private double rating;

  @ManyToOne private TeamEntity team;

  public void updateRating(double offset) {
    rating += offset;
  }

  public void incrementWinCount() {
    winCount += 1;
  }

  public void incrementLossCount() {
    lossCount += 1;
  }

  public void incrementDrawCount() {
    drawCount += 1;
  }

  public int numberOfGamesPlayed() {
    return lossCount + drawCount + winCount;
  }
}
