package dev.pirokiko.lanlords.playerstats.config;

import lombok.Value;

@Value
public class EloSettings {
  int proRatingBoundry = 2400;
  int starterBoundry = 30;
  int defaultRating = 2000;
  int defaultKFactor = 10;
}
