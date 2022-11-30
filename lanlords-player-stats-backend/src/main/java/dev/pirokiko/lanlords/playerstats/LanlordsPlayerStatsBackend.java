package dev.pirokiko.lanlords.playerstats;

import dev.pirokiko.lanlords.playerstats.config.EloSettings;
import dev.pirokiko.lanlords.playerstats.dev.MatchGenerator;
import dev.pirokiko.lanlords.playerstats.domain.Match;
import dev.pirokiko.lanlords.playerstats.domain.Team;
import dev.pirokiko.lanlords.playerstats.elo.EloProcessor;
import dev.pirokiko.lanlords.playerstats.elo.GeneralTeamEloProcessor;
import dev.pirokiko.lanlords.playerstats.elo.PlayersOnTeamEloProcessor;
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

  List<Team> teams() {
    return List.of(
        Teams.FieldFuckers(),
        Teams.VeldHeren(),
        Teams.AceGaming(),
        Teams.GoldenBoys(),
        Teams.PaniekZaaiers(),
        Teams.Stuk());
  }

  List<EloProcessor<?>> eloProcessors() {
    final var eloSettings = new EloSettings();
    return List.of(
        new GeneralTeamEloProcessor(eloSettings), new PlayersOnTeamEloProcessor(eloSettings));
  }

  void printResults(List<Team> teams, List<PlayerEntity> players) {
    for (Team team : teams) {
      System.out.printf("%s | %s%n", team.name(), team.rating());
      for (PlayerEntity player : team.members()) {
        System.out.printf("\t%s | %s%n", player.name(), player.rating());
      }
    }

    System.out.println(" ");
    System.out.println("Players");
    final Comparator<PlayerEntity> comp = Comparator.comparing(PlayerEntity::rating);
    final var sortedPlayers = players.stream().sorted(comp.reversed()).toList();
    for (PlayerEntity player : sortedPlayers) {
      System.out.printf("%.0f %s (%s)\n", player.rating(), player.name(), player.handle());
    }
  }

  @Bean
  CommandLineRunner devEloProcessor() {
    final var teams = teams();
    final var players = teams.stream().flatMap(team -> team.members().stream()).toList();
    final var matchGenerator = new MatchGenerator(teams);
    var eloProcessors = eloProcessors();

    return args -> {
      for (int i = 0; i < 1000; i++) {
        final Match match = matchGenerator.generateMatch(i);
        for (EloProcessor eloProcessor : eloProcessors) {
          var eloMatches = eloProcessor.eloMatchesFrom(match);
          eloProcessor.process(eloMatches);
        }
      }

      printResults(teams, players);
    };
  }
}
