package dev.pirokiko.lanlords.playerstats;

import dev.pirokiko.lanlords.playerstats.config.EloSettings;
import dev.pirokiko.lanlords.playerstats.dev.MatchGenerator;
import dev.pirokiko.lanlords.playerstats.domain.GeneralTeamEloProcessor;
import dev.pirokiko.lanlords.playerstats.domain.Match;
import dev.pirokiko.lanlords.playerstats.domain.PlayersOnTeamEloProcessor;
import dev.pirokiko.lanlords.playerstats.domain.Team;
import dev.pirokiko.lanlords.playerstats.domain.elo.EloContext;
import dev.pirokiko.lanlords.playerstats.elo.EloProcessor;
import dev.pirokiko.lanlords.playerstats.entity.AbilityEntity;
import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;
import dev.pirokiko.lanlords.playerstats.fixture.Teams;
import dev.pirokiko.lanlords.playerstats.model.Ability;
import dev.pirokiko.lanlords.playerstats.model.Game;
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

  List<Team> teams(final EloContext eloContext) {
    return List.of(
        Teams.FieldFuckers(eloContext),
        Teams.VeldHeren(eloContext),
        Teams.AceGaming(eloContext),
        Teams.GoldenBoys(eloContext),
        Teams.PaniekZaaiers(eloContext),
        Teams.Stuk(eloContext));
  }

  List<EloProcessor<?>> eloProcessors(EloContext eloContext) {
    final var eloSettings = new EloSettings();
    return List.of(
        new GeneralTeamEloProcessor(eloSettings),
        new PlayersOnTeamEloProcessor(eloSettings, eloContext));
  }

  double rating(PlayerEntity player) {
    return player.ability(Game.GENERIC, Ability.GENERAL).map(AbilityEntity::rating).orElse(0d);
  }

  void printResults(List<Team> teams, List<PlayerEntity> players) {
    for (Team team : teams) {
      System.out.printf("%s | %s%n", team.name(), team.rating());
      for (PlayerEntity player : team.members()) {
        System.out.printf("\t%s | %s%n", player.name(), rating(player));
      }
    }

    System.out.println(" ");
    System.out.println("Players");
    final Comparator<PlayerEntity> comp = Comparator.comparing(this::rating);
    final var sortedPlayers = players.stream().sorted(comp.reversed()).toList();
    for (PlayerEntity player : sortedPlayers) {
      System.out.printf("%.0f %s (%s)\n", rating(player), player.name(), player.handle());
    }
  }

  @Bean
  CommandLineRunner devEloProcessor() {
    final var eloContext = new EloContext(Game.GENERIC, Ability.GENERAL);
    final var teams = teams(eloContext);
    final var players = teams.stream().flatMap(team -> team.members().stream()).toList();
    final var matchGenerator = new MatchGenerator(teams);
    var eloProcessors = eloProcessors(eloContext);

    return args -> {
      for (int i = 0; i < 1000; i++) {
        final Match match = matchGenerator.generateMatch(i);

        for (final Game game : Game.values()) {
          for (final Ability ability : Ability.values()) {
            // Set context for processing
            eloContext.ability(ability);
            eloContext.game(game);

            for (EloProcessor eloProcessor : eloProcessors) {
              var eloMatches = eloProcessor.eloMatchesFrom(match);
              eloProcessor.process(eloMatches);
            }
          }
        }
      }

      printResults(teams, players);
    };
  }
}
