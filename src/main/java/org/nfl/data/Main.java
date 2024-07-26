package org.nfl.data;

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
            DefenseStats season = new DefenseStats(2023);
            for ( TeamDefense team : season.getNFL_DEFENSES() ){
                System.out.println(team.getTeamName() + " : " + team.getRushAttPG());
            }
            /*Player moore = new Player("kyler murray");
            for ( PlayerGame game : moore.getGAME_LOG().GAME_LOG ){
                System.out.println( "week: " + game.getOPPONENT() + " : " + game.getTemperature() + " : " + game.getFeelsLike() + " : " + game.getPASS_YD());
            }
             */
            //PlayerSearch moore = new PlayerSearch("Davante Adams", 2023);
            //System.out.println(moore.getStatString("location"));
            //System.out.println(moore.getStatString("age"));
            //Game bearsVsPackers = new Game("/boxscores/202309100chi.htm");
            //Game bearsVsChiefs = new Game("/boxscores/202309240kan.htm");
            //System.out.println(bearsVsPackers.getGameInfo());
            //System.out.println(bearsVsChiefs.getGameStats());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}