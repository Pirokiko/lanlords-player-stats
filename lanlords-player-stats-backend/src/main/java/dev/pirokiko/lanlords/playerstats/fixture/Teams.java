package dev.pirokiko.lanlords.playerstats.fixture;

import dev.pirokiko.lanlords.playerstats.domain.Team;
import dev.pirokiko.lanlords.playerstats.domain.elo.EloContext;
import dev.pirokiko.lanlords.playerstats.entity.OrganisationEntity;

import java.util.List;

import static dev.pirokiko.lanlords.playerstats.fixture.Players.*;

public class Teams {
  private static final OrganisationEntity FIELDFUCKERS =
      OrganisationEntity.builder().id(1L).name("FieldFuckers").build();
  private static final OrganisationEntity VELDHEREN =
      OrganisationEntity.builder().id(2L).name("VeldHeren").build();
  private static final OrganisationEntity ACEGAMING =
      OrganisationEntity.builder().id(3L).name("AceGaming").build();
  private static final OrganisationEntity STUK =
      OrganisationEntity.builder().id(4L).name("STUK").build();
  private static final OrganisationEntity PANIEKZAAIERS =
      OrganisationEntity.builder().id(5L).name("PaniekZaaiers").build();
  private static final OrganisationEntity GOLDENBOYS =
      OrganisationEntity.builder().id(6L).name("GoldenBoys").build();

  public static Team FieldFuckers(final EloContext eloContext) {
    return new Team(
        FIELDFUCKERS,
        List.of(PIROKIKO, THUNDERBUFF, SPHONGOLOID, MYQE, SECRETKILLER, DEB, MARK, KRAMERQ, UNREAL),
        eloContext);
  }

  public static Team VeldHeren(final EloContext eloContext) {
    return new Team(
        VELDHEREN, List.of(GEO, KING_KOBIE, PRINCE_KOBIE, EDWARD, DARKSINTH, GIEL), eloContext);
  }

  public static Team AceGaming(final EloContext eloContext) {
    return new Team(ACEGAMING, List.of(DOOM, AlphaRex, DarkWing), eloContext);
  }

  public static Team Stuk(final EloContext eloContext) {
    return new Team(STUK, List.of(DARKPAIN, GIJS), eloContext);
  }

  public static Team PaniekZaaiers(final EloContext eloContext) {
    return new Team(PANIEKZAAIERS, List.of(ARJAN, ACE, NEHOX), eloContext);
  }

  public static Team GoldenBoys(final EloContext eloContext) {
    return new Team(GOLDENBOYS, List.of(FRONK, GWIED, ALPHASPEAR), eloContext);
  }
}
