package dev.pirokiko.lanlords.playerstats.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@Data
@Accessors(fluent = true)
public class PlayerEntity {
  @Id private Long id;
  private String name;
  private String handle;

  private int winCount;
  private int lossCount;
  private int drawCount;

  @OneToMany(cascade = CascadeType.PERSIST)
  private List<AbilityEntity> abilities;

  private double rating; // Replace with an AbilityEntity with GENERAL type in abilities

  // Maybe not define it as a hard requirement: a player competes with a team, but can compete for a
  // different team next time; so it might not be a hard defined thing and should be defined when
  // building the team for processing the matches
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
