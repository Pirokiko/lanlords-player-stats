package dev.pirokiko.lanlords.playerstats.fixture;

import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;

public class Players {
  // FieldFuckers
  static final PlayerEntity PIROKIKO =
      PlayerEntity.builder().id(1L).name("Milan").handle("Pirokiko").build();
  static final PlayerEntity THUNDERBUFF =
      PlayerEntity.builder().id(2L).name("Jona").handle("Thunderbuff").build();
  static final PlayerEntity SPHONGOLOID =
      PlayerEntity.builder().id(3L).name("Niek").handle("Sphongoloid").build();
  static final PlayerEntity MYQE =
      PlayerEntity.builder().id(4L).name("Mike").handle("Myqe").build();
  static final PlayerEntity SECRETKILLER =
      PlayerEntity.builder().id(5L).name("Pedro").handle("Secretkiller").build();
  static final PlayerEntity DEB = PlayerEntity.builder().id(6L).name("Tom").handle(".Deb").build();
  static final PlayerEntity MARK =
      PlayerEntity.builder().id(7L).name("Mark").handle("Mark").build();
  static final PlayerEntity KRAMERQ =
      PlayerEntity.builder().id(8L).name("Kramer").handle("KRAMERQ").build();
  static final PlayerEntity UNREAL =
      PlayerEntity.builder().id(9L).name("Dion").handle("Unreal").build();

  // VELDHEREN
  static final PlayerEntity GEO =
      PlayerEntity.builder().id(101L).name("George").handle("Geo").build();
  static final PlayerEntity KING_KOBIE =
      PlayerEntity.builder().id(102L).name("Perez").handle("KingKobie").build();
  static final PlayerEntity PRINCE_KOBIE =
      PlayerEntity.builder().id(103L).name("Duco").handle("PrinceKobie").build();
  static final PlayerEntity EDWARD =
      PlayerEntity.builder().id(104L).name("Edward").handle("Edward").build();
  static final PlayerEntity DARKSINTH =
      PlayerEntity.builder().id(105L).name("Martijn").handle("Darksinth").build();
  static final PlayerEntity GIEL =
      PlayerEntity.builder().id(106L).name("Giel").handle("Giel").build();

  // ACE GAMING
  static final PlayerEntity DOOM =
      PlayerEntity.builder().id(201L).name("Doom").handle("DOOM").build();
  static final PlayerEntity AlphaRex =
      PlayerEntity.builder().id(202L).name("AlphaRex").handle("AlphaRex").build();
  static final PlayerEntity DarkWing =
      PlayerEntity.builder().id(203L).name("DarkWing").handle("DarkWing").build();

  // STUK
  static final PlayerEntity DARKPAIN =
      PlayerEntity.builder().id(301L).name("Jan").handle("DarkPain").build();
  static final PlayerEntity GIJS =
      PlayerEntity.builder().id(302L).name("Gijs").handle("Gijs").build();

  // PaniekZaaiers
  static final PlayerEntity ARJAN =
      PlayerEntity.builder().id(401L).name("Arjan").handle("ARJAN").build();
  static final PlayerEntity ACE =
      PlayerEntity.builder().id(402L).name("Koos").handle("Ace").build();
  static final PlayerEntity NEHOX =
      PlayerEntity.builder().id(403L).name("Saaf").handle("Nehox").build();

  // GoldenBoys
  static final PlayerEntity FRONK =
      PlayerEntity.builder().id(501L).name("Fronk").handle("Fronk").build();
  static final PlayerEntity GWIED =
      PlayerEntity.builder().id(502L).name("Gwied").handle("Gwied").build();
  static final PlayerEntity ALPHASPEAR =
      PlayerEntity.builder().id(503L).name("AlphaSpear").handle("AlphaSpear").build();
}
