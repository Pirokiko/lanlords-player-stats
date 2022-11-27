package dev.pirokiko.lanlords.playerstats.domain;

import dev.pirokiko.lanlords.playerstats.model.Game;
import lombok.NonNull;

import java.util.Objects;

public record Match(Game game, Team team1, Team team2, Team victor, boolean draw) {

    public boolean isWinner(@NonNull Team team){
        return Objects.equals(team, victor);
    }

    public Team winner() {
        if (draw) return null;
        return victor;
    }
    public Team loser() {
        if (draw) return null;
        if (team1 == victor) return team2;
        return team1;
    }

    public Game game(){
        return game;
    }
}
