package dev.pirokiko.lanlords.playerstats;

import dev.pirokiko.lanlords.playerstats.domain.EloProcessor;
import dev.pirokiko.lanlords.playerstats.domain.Match;
import dev.pirokiko.lanlords.playerstats.domain.Team;
import dev.pirokiko.lanlords.playerstats.domain.config.EloSettings;
import dev.pirokiko.lanlords.playerstats.domain.dev.MatchGenerator;
import dev.pirokiko.lanlords.playerstats.domain.dev.TeamGenerator;
import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;
import dev.pirokiko.lanlords.playerstats.model.Game;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LanlordsPlayerStatsBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(LanlordsPlayerStatsBackendApplication.class, args);
  }

  @Bean
  CommandLineRunner devEloProcessor() {
    final var eloProcessor = new EloProcessor(new EloSettings());

    final var teamGenerator = new TeamGenerator();
    final var teams = teamGenerator.generate(6);

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
    };
  }
}
