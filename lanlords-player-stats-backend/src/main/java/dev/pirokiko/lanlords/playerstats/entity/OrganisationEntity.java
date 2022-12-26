package dev.pirokiko.lanlords.playerstats.entity;

import dev.pirokiko.lanlords.playerstats.model.Ability;
import dev.pirokiko.lanlords.playerstats.model.Game;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Accessors(fluent = true)
public class OrganisationEntity {
  @Id private Long id;
  private String name;

  @OneToMany private List<AbilityEntity> abilities;

  public Optional<AbilityEntity> ability(final Game game, final Ability type) {
    if (abilities == null || abilities.isEmpty()) return Optional.empty();

    return abilities.stream()
        .filter(
            ability ->
                Objects.equals(ability.game(), game) && Objects.equals(ability.ability(), type))
        .findFirst();
  }
}
