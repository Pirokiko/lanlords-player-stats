package dev.pirokiko.lanlords.playerstats.fixture;

import dev.pirokiko.lanlords.playerstats.domain.Team;
import dev.pirokiko.lanlords.playerstats.entity.TeamEntity;

import java.util.List;

import static dev.pirokiko.lanlords.playerstats.fixture.Players.*;

public class Teams {
  private static final TeamEntity FIELDFUCKERS =
      TeamEntity.builder().id(1L).name("FieldFuckers").build();
  private static final TeamEntity VELDHEREN = TeamEntity.builder().id(2L).name("VeldHeren").build();
  private static final TeamEntity ACEGAMING = TeamEntity.builder().id(3L).name("AceGaming").build();
  private static final TeamEntity STUK = TeamEntity.builder().id(4L).name("STUK").build();
  private static final TeamEntity PANIEKZAAIERS =
      TeamEntity.builder().id(5L).name("PaniekZaaiers").build();
  private static final TeamEntity GOLDENBOYS =
      TeamEntity.builder().id(6L).name("GoldenBoys").build();

  public static Team FieldFuckers() {
    return new Team(
        FIELDFUCKERS,
        List.of(
            PIROKIKO, THUNDERBUFF, SPHONGOLOID, MYQE, SECRETKILLER, DEB, MARK, KRAMERQ, UNREAL));
  }

  public static Team VeldHeren() {
    return new Team(VELDHEREN, List.of(GEO, KING_KOBIE, PRINCE_KOBIE, EDWARD, DARKSINTH, GIEL));
  }

  public static Team AceGaming() {
    return new Team(ACEGAMING, List.of(DOOM, AlphaRex, DarkWing));
  }

  public static Team Stuk() {
    return new Team(STUK, List.of(DARKPAIN, GIJS));
  }

  public static Team PaniekZaaiers() {
    return new Team(PANIEKZAAIERS, List.of(ARJAN, ACE, NEHOX));
  }

  public static Team GoldenBoys() {
    return new Team(GOLDENBOYS, List.of(FRONK, GWIED, ALPHASPEAR));
  }
}
