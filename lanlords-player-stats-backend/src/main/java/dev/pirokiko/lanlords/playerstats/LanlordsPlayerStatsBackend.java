package dev.pirokiko.lanlords.playerstats;

import dev.pirokiko.lanlords.playerstats.config.EloSettings;
import dev.pirokiko.lanlords.playerstats.dev.MatchGenerator;
import dev.pirokiko.lanlords.playerstats.domain.Match;
import dev.pirokiko.lanlords.playerstats.domain.Team;
import dev.pirokiko.lanlords.playerstats.elo.EloProcessor;
import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;
import dev.pirokiko.lanlords.playerstats.fixture.Teams;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class LanlordsPlayerStatsBackend {

  public static void main(String[] args) {
    SpringApplication.run(LanlordsPlayerStatsBackend.class, args);
  }

  @Bean
  CommandLineRunner devEloProcessor() {
    final var eloProcessor = new EloProcessor(new EloSettings());

    final var teams =
        List.of(
            Teams.FieldFuckers(),
            Teams.VeldHeren(),
            Teams.AceGaming(),
            Teams.GoldenBoys(),
            Teams.PaniekZaaiers(),
            Teams.Stuk());
    final var players = teams.stream().flatMap(team -> team.members().stream());

    final var matchGenerator = new MatchGenerator(teams);

    return args -> {
      for (int i = 0; i < 1000; i++) {
        final Match match = matchGenerator.generateMatch(i);
        eloProcessor.process(match);
      }

      for (Team team : teams) {
        System.out.printf("%s | %s%n", team.name(), team.rating());
        for (PlayerEntity player : team.members()) {
          System.out.printf("\t%s | %s%n", player.name(), player.rating());
        }
      }

      final Comparator<PlayerEntity> comp = Comparator.comparing(PlayerEntity::rating);

      System.out.println(" ");
      System.out.println("Players");
      for (PlayerEntity player : players.sorted(comp.reversed()).toList()) {
        System.out.printf("%.0f %s (%s)\n", player.rating(), player.name(), player.handle());
      }
    };
  }
}
