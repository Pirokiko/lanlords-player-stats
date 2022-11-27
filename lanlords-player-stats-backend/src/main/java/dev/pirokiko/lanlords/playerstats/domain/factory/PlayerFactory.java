package dev.pirokiko.lanlords.playerstats.domain.factory;

import dev.pirokiko.lanlords.playerstats.domain.Player;
import dev.pirokiko.lanlords.playerstats.entity.PlayerEntity;

public class PlayerFactory {
    public Player create(final PlayerEntity player){
        return new Player(player);
    }
}
