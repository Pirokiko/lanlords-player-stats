package dev.pirokiko.lanlords.playerstats.entity;

import dev.pirokiko.lanlords.playerstats.model.Ability;
import dev.pirokiko.lanlords.playerstats.model.Game;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

  @OneToMany(cascade = CascadeType.PERSIST)
  private List<AbilityEntity> abilities;

  @ManyToOne private OrganisationEntity organisation;

  public Optional<AbilityEntity> ability(final Game game, final Ability type) {
    if (abilities == null || abilities.isEmpty()) return Optional.empty();

    return abilities.stream()
        .filter(
            ability ->
                Objects.equals(ability.game(), game) && Objects.equals(ability.ability(), type))
        .findFirst();
  }
}
