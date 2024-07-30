package org.nfl.data;

import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) {
        try {
            Utils utils = new Utils();
            DefenseStats defenseStats = new DefenseStats(2022);
            ModelFive model = new ModelFive(2022);
            Player moore = new Player("Keenan Allen");


            double line;
            double pred;
            int act;
            boolean c;
            int correct = 0;
            int gameNum = 1;
            for ( PlayerGame game : moore.getGAME_LOG().GAME_LOG ){
                if ( game.getFeelsLike() > 34 && game.getFeelsLike() < 80) {
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
                if ( game.getFeelsLike() > 34 && game.getFeelsLike() < 80) {
                    String formattedString = String.format("%d = (%f * a) + (%f * b) + (%f * c) + (%f * d) + (%f * e) + (%f * f) + (%f * g)", act, team.getPointsPG(), team.getPlaysPG(), team.getExpectedPointsPG(), team.getReceptionsPG(), team.getPassAttPG(), team.getTargetsPG(), team.getScoresPerOffDrivePG());
                    System.out.println(formattedString);
                }
            }
            */

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}