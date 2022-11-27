package dev.pirokiko.lanlords.playerstats.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PlayerEntity {
  @Id private Long id;
  private String name;
  private String handle;

  @ManyToOne private TeamEntity team;
}
