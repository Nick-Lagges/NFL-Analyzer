package org.nfl.data;

import java.util.Random;

public class Main {
    private static String[] playerList = { "Tee Higgins", "DJ Moore", "AJ Brown", "Keenan Allen", "Brandon Aiyuk", "Tyreek Hill",
            "CeeDee Lamb", "Michael Pittman", "Stefon Diggs", "Diontae Johnson", "Davante Adams",
            "Adam Thielen", "Tyler Lockett", "Christian Kirk", "Chris Godwin", "Mike Evans",
            "DeVonta Smith", "Amari Cooper", "DeAndre Hopkins", "Justin Jefferson",
            "Amon-Ra St. Brown", "Cooper Kupp", "Terry McLaurin", "DK Metcalf", "Deebo Samuel" };

    public static void main(String[] args) {
        try {
            /*
            (Intercept)                 3.98751    3.73210   1.068 0.286180
game.getFeelsLike           0.02020    0.01115   1.812 0.071042 .
game.getVisibility          0.12090    0.07600   1.591 0.112716
game.getRecLine             0.52750    0.14382   3.668 0.000289 ***
opp.getReceptionsPG        -1.52773    0.57300  -2.666 0.008086 **
opp.getTargetsPG            1.19721    0.47540   2.518 0.012311 *
opp.getYardsPG              0.04514    0.01596   2.828 0.005001 **
opp.getFirstDownsPG        -0.62111    0.27977  -2.220 0.027155 *
opp.getPassCompsPG          0.79309    0.38564   2.057 0.040589 *
opp.getPassAttPG           -0.66946    0.31991  -2.093 0.037219 *
opp.getPassTDPG            -1.33456    0.76464  -1.745 0.081948 .
opp.getScoresPerOffDrivePG -0.09934    0.07071  -1.405 0.161077

             */

            Utils utils = new Utils();
            Player player = null;
            TeamDefense team = null;
            //DefenseStats defenseStats = new DefenseStats(2022);


            double line;
            double pred;
            int act;
            boolean c;
            int correct = 0;
            int gameNum = 0;
            int realOvers = 0;
            int realUnders = 0;
            int correctOvers = 0;
            int correctUnders = 0;
            double minPred = 10000;
            double maxPred = -10000;
            for (int i = 2022; i < 2024; i++) {
                ModelC model = new ModelC(i-1);
                for (String name : playerList) {
                    player = new Player(name, i);
                    System.out.println(name);
                    PlayerSeasonStats stats = player.getSEASON_STATS(i-1);
                    double avgRec = (double) stats.getReceptions() / stats.getGames();
                    for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                        line = game.getRecLine();
                        if (line != 0.0 && game.getREC() != -1) {
                            pred = model.performModel("receptions", game);
                            act = game.getREC();
                            c = (((pred > line) && (act > line)) || ((pred < line) && (act < line)));
                            double conf = Math.abs(pred - line);
                            double diff = Math.abs(pred - act);
                            if (pred != -1.0000) {
                                if (c) {
                                    correct++;
                                    if (pred < line) correctUnders++;
                                    else correctOvers++;
                                    if (act > line) {
                                    } else {
                                    }
                                } else {
                                }
                                if (act > line) {
                                    realOvers++;
                                } else {
                                    realUnders++;

                                }
                                minPred = Math.min(minPred, pred);
                                maxPred = Math.max(maxPred, pred);
                                gameNum++;
                                String formatS = String.format("correct: %d | total: %d | line: %f | prediction: %f | actual: %d | percent: %f%%", correct, gameNum, line, pred, act, ((double) correct / gameNum * 100));
                                System.out.println(formatS);
                                //String formattedString = String.format("2.5 %d-%d | 3.5 %d-%d | 4.5 %d-%d | 5.0 %d-%d | 5.5 %d-%d | 6.5 %d-%d | 7.5 %d-%d", twopointfiveC, twopointfiveO, threepointfiveC, threepointfiveO, fourpointfiveC, fourpointfiveO, fivepointC, fivepointO, fivepointfiveC, fivepointfiveO, sixpointfiveC, sixpointfiveO, sevenpointfiveC, sevenpointfiveO);
                                //System.out.println(formattedString);
                            }
                        }
                    }
                }
            }


            /*
            //System.out.println("");
            boolean over = false;
            int o = 0;
            for ( String s : playerList) {
                player = new Player(s, 2023);
                PlayerSeasonStats stats = player.getSEASON_STATS(2022);
                double avgRec = 0.0;
                if (stats.getGames() != 0)
                    avgRec = (double) stats.getReceptions() / stats.getGames();
                for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                    for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES()) {
                        if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())))
                            team = teamDefense;
                    }
                    if ((game.getRecLine() != 0) && game.getREC() != -1) {
                        if ( game.getREC() > game.getRecLine() ) o = 1;
                        else o = 0;
                        String formattedString = String.format("%f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %d",
                                game.getFeelsLike(), game.getWindSpeed(), game.getPrecipitation(), game.getVisibility(), game.getRecLine(),
                                game.getCloudCover(), game.getDewPoint(), game.getHumidity(), game.getWindGust(), team.getReceptionsPG(),
                                team.getTargetsPG(), team.getExpectedPointsPG(), team.getPointsPG(), team.getYardsPG(), team.getPlaysPG(),
                                team.getYardsPerPlay(), team.getTurnoversPG(), team.getFumblesPG(), team.getFirstDownsPG(), team.getPassCompsPG(),
                                team.getPassAttPG(), team.getPassTDPG(), team.getPassIntPG(), team.getPassFirstDownsPG(), team.getPenaltiesPG(),
                                team.getPenaltyFirstDownsPG(), team.getScoresPerOffDrivePG(), team.getExpectedPointsPG(), avgRec, game.getRecLine(), o);
                        System.out.println(formattedString);
                        //String formattedString = String.format("{ %.4f, %.4f, %.4f, %.4f, %.4f },",
                        //       team.getReceptionsPG(), game.getWindSpeed(), game.getFeelsLike(), team.getExpectedPointsPG(), team.getFirstDownsPG());
                        //System.out.println(formattedString);
                        //System.out.println("indep.add(" + game.getFeelsLike() + ", " + game.getREC() + ");");
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