package org.nfl.data;

import java.util.ArrayList;

public class PlayerGameLog {
    ArrayList<PlayerGame> GAME_LOG = new ArrayList<>();

    public PlayerGameLog(){}

    public void addGame(PlayerGame playerGame) {
        GAME_LOG.add(playerGame);
    }

    public PlayerGame getWeek(int weekNum) {
        return GAME_LOG.get(weekNum-1);
    }

}
