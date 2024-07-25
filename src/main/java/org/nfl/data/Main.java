package org.nfl.data;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            //DataVisualization chart = new DataVisualization("Brandon Aiyuk", 2023, "receiving yards", 76.5, 10);
            //chart.start();


            /*System.out.println(modelOne.performModel());
            Utils utils = new Utils();
            DefenseSearch team = new DefenseSearch("bears", 2017);
            System.out.println(team.getStatAgainst("passing touchdowns", "qb"));*/

            Utils util = new Utils();
            Team bears = new Team("bears", 2023);
            for ( Game g : bears.TEAM_GAMES ) {
                ArrayList<String> game = g.getGameInfo();
                int index = 0;
                if ( game.get(game.size()-1).contains("-") ) { index = game.size()-1; }
                else if ( game.get(game.size()-2).contains("-") ) { index = game.size()-2; }
                System.out.println(game.get(index));
            }
            //PlayerSearch moore = new PlayerSearch("DJ Moore", 2023);
            //System.out.println(moore.getStat("receiving yards", 5));
            //Game bearsVsPackers = new Game("/boxscores/202309100chi.htm");
            //Game bearsVsChiefs = new Game("/boxscores/202309240kan.htm");
            //System.out.println(bearsVsPackers.getGameInfo());
            //System.out.println(bearsVsChiefs.getGameStats());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}