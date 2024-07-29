package org.nfl.data;

import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) {
        try {
            Utils utils = new Utils();
            //DefenseStats defenseStats = new DefenseStats(2022);
            ModelOne modelOne = new ModelOne(2022);
            Player moore = new Player("DJ Moore");
            double line;
            double pred;
            int act;
            boolean c;
            int correct = 0;
            for ( PlayerGame game : moore.getGAME_LOG().GAME_LOG ){

                line = game.getRecLine();
                pred = modelOne.performModel("receptions", game);
                act = game.getREC();
                c = ( ( (pred > line) && (act > line) ) || ( (pred < line) && (act < line) ) );
                if ( c ) correct++;
                String formattedString = String.format("Line: %f | Prediction: %f | Actual %d | Correct: %d", line, pred, act, correct);
                System.out.println(formattedString);
            }
            /*double line;
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
                String formattedString = String.format("%d = (%f * v) + (%f * w) + (%f * x) + (%f * z)", act, team.getReceptionsPG(), game.getWindSpeed(), game.getFeelsLike(), team.getExpectedPointsPG());
                System.out.println(formattedString);
            }

             */
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}