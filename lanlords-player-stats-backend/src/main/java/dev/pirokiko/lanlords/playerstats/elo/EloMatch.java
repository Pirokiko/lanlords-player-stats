package dev.pirokiko.lanlords.playerstats.elo;

public interface EloMatch<T extends EloEntity> {
    T elo1();
    T elo2();
    boolean draw();
    boolean isWinner(T elo);
}

