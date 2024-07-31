package org.nfl.data;

import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) {
        try {
            Utils utils = new Utils();
            DefenseStats defenseStats = new DefenseStats(2022);
            //ModelOne model = new ModelOne(2021);
            Player moore = new Player("DJ Moore", 2023);

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
                //if ( game.getFeelsLike() > 34 && game.getFeelsLike() < 80 && game.getWindSpeed() < 10 ) {
                    //String formattedString = String.format("{ %f, %f, %f, %f, %f, %f },", team.getReceptionsPG(), game.getWindSpeed(), game.getFeelsLike(), team.getExpectedPointsPG(), team.getFirstDownsPG(), avgRec);
                    //System.out.println(formattedString);
                System.out.println(game.getREC());
                totalRec += game.getREC();
                gamesPlayed++;
                avgRec = totalRec/gamesPlayed;
                //}
            }

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