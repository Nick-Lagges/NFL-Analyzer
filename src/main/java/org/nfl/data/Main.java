package org.nfl.data;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try {
            /*
            (Intercept)              249.7770    87.8396   2.844  0.00593 **
            `team.getReceptionsPG()`   2.4298     0.8311   2.923  0.00474 **
            `team.getTargetsPG()`     -1.6241     0.6543  -2.482  0.01560 *
            `team.getYardsPG()`        0.6744     0.2425   2.781  0.00706 **
            `team.getPlaysPG()`       -3.4380     1.3782  -2.495  0.01512 *
            `team.getYardsPerPlay()` -41.4877    15.0406  -2.758  0.00751 **
            `team.getFirstDownsPG()`  -0.9659     0.5440  -1.776  0.08040 .
            `team.getPassAttPG()`     -0.4656     0.2765  -1.684  0.09694 .
            avgRec                     0.6398     0.2616   2.445  0.01715 *
                ---
            Signif. codes:  0 ‘***’ 0.001 ‘**’ 0.01 ‘*’ 0.05 ‘.’ 0.1 ‘ ’ 1

             */
            Utils utils = new Utils();
            Random rand = new Random();
            String[] first7 = { "DJ Moore", "AJ Brown", "Keenan Allen", "Brandon Aiyuk", "Tyreek Hill", "CeeDee Lamb", "Michael Pittman" };
            String[] second7 = { "Stefon Diggs", "Ja'Marr Chase", "Davante Adams", "Adam Thielen", "Garrett Wilson", "Chris Olave", "Chris Godwin" };
            String[] third6 = { "Mike Evans", "DeVonta Smith", "Nico Collins", "Amari Cooper", "Jaylen Waddle", "Justin Jefferson" };
            DefenseStats defenseStats = new DefenseStats(2022);
            Player player = null;
            TeamDefense team = null;
            for ( String name : first7 ) {
                player = new Player(name, 2023);
                for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                    for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES()) {
                        if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())))
                            team = teamDefense;
                    }
                    if ((game.getRecLine() != 0) && game.getREC() != -1) {
                        System.out.println("indep.add(" + team.getPassTDPG() + ", " + game.getREC() + ");");
                    }
                }


                //break;
                //Thread.sleep(rand.nextInt(500,1000));
            }

            /*
            double line;
            double pred;
            int act;
            boolean c;
            int correct = 0;
            int gameNum = 1;
            for ( PlayerGame game : moore.getGAME_LOG().GAME_LOG ){
                    line = game.getRecLine();
                    if ( line != 0.0 ) {
                        pred = model.performModel("receptions", game);
                        act = game.getREC();
                        c = (((pred > line) && (act > line)) || ((pred < line) && (act < line)));
                        if (c) correct++;
                        String formattedString = String.format("Game: %d | Line: %f | Prediction: %f | Actual %d | Correct: %d", gameNum, line, pred, act, correct);
                        System.out.println(formattedString);
                        gameNum++;
                    }
            }
            */
            /*
            double line;
            double pred;
            int act;
            boolean c;
            int correct = 0;
            int gamesPlayed = 0;
            int totalRec = 0;
            PlayerSeasonStats stats = moore.getSEASON_STATS(2022);
            double avgRec = 0.0;
            if ( stats.getGames() != 0 )
                avgRec = (double) stats.getReceptions() / stats.getGames();
            for ( PlayerGame game : moore.getGAME_LOG().GAME_LOG ){
                for ( TeamDefense teamDefense : defenseStats.getNFL_DEFENSES() ){
                    if ( teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())) ) team = teamDefense;
                }
                line = game.getRecLine();
                //pred = modelOne.performModel("receptions", game);
                act = game.getREC();
                //c = ( ( (pred > line) && (act > line) ) || ( (pred < line) && (act < line) ) );
                //if ( c ) correct++;
                if ( (game.getRecLine() != 0 ) && game.getREC() != -1 )  {
                    /*String formattedString = String.format("{ %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f },",
                            game.getFeelsLike(), game.getWindSpeed(), game.getPrecipitation(), game.getVisibility(), game.getRecLine(),
                            game.getCloudCover(), game.getDewPoint(), game.getHumidity(), game.getWindGust(), team.getReceptionsPG(),
                            team.getTargetsPG(), team.getExpectedPointsPG(), team.getPointsPG(), team.getYardsPG(), team.getPlaysPG(),
                            team.getYardsPerPlay(), team.getTurnoversPG(), team.getFumblesPG(), team.getFirstDownsPG(), team.getPassCompsPG(),
                            team.getPassAttPG(), team.getPassTDPG(), team.getPassIntPG(), team.getPassFirstDownsPG(), team.getPenaltiesPG(),
                            team.getPenaltyFirstDownsPG(), team.getScoresPerOffDrivePG(), team.getExpectedPointsPG(), avgRec);
                    System.out.println(formattedString);
                    //String formattedString = String.format("{ %.4f, %.4f, %.4f, %.4f, %.4f },",
                    //       team.getReceptionsPG(), game.getWindSpeed(), game.getFeelsLike(), team.getExpectedPointsPG(), team.getFirstDownsPG());
                    //System.out.println(formattedString);
                    System.out.println("indep.add(" + game.getFeelsLike() + ", " + game.getREC() + ");");
                }
            }; */

            /*
            double line;
            double pred;
            int act;
            boolean c;
            int correct = 0;
            TeamDefense team = null;
            for ( PlayerGame game : moore.getGAME_LOG().GAME_LOG ){
                for ( TeamDefense teamDefense : defenseStats.getNFL_DEFENSES() ){
                    if ( teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())) ) team = teamDefense;
                }
                line = game.getRecLine();
                //pred = modelOne.performModel("receptions", game);
                act = game.getREC();
                //c = ( ( (pred > line) && (act > line) ) || ( (pred < line) && (act < line) ) );
                //if ( c ) correct++;
                if ( !(act == -1) && game.getFeelsLike() > 34 && game.getFeelsLike() <80 ) {
                    String formattedString = String.format("(%d, %f)", act, game.getWindSpeed());
                    System.out.println(formattedString);
                }
            }
            */

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}