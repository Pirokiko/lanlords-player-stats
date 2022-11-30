package dev.pirokiko.lanlords.playerstats.elo;

public interface EloEntity {
    double rating();
    void updateRating(double ratingOffset);
    int numberOfGamesPlayed();
}
