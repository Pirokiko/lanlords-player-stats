package dev.pirokiko.lanlords.playerstats.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class TeamEntity {
    @Id private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<PlayerEntity> members;
}
