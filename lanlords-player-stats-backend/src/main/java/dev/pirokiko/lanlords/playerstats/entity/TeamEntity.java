package dev.pirokiko.lanlords.playerstats.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Accessors(fluent = true)
public class TeamEntity {
  @Id private Long id;
  @ManyToOne private OrganisationEntity organisation;
  @OneToMany private List<PlayerEntity> members;
  private String tournament; // In which tournament did this team composition play?
  @OneToMany private List<AbilityEntity> abilities;
}
