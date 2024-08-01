package org.nfl.data;

public class Main {
    public static void main(String[] args) {
        try {
            Utils utils = new Utils();
            String[] first7 = { "DJ Moore", "AJ Brown", "Keenan Allen", "Brandon Aiyuk", "Tyreek Hill", "CeeDee Lamb", "Michael Pittman" };
            String[] second7 = { "Stefon Diggs", "Ja'Marr Chase", "Davante Adams", "Adam Thielen", "Garrett Wilson", "Chris Olave", "Chris Godwin" };
            String[] third6 = { "Mike Evans", "DeVonta Smith", "Nico Collins", "Amari Cooper", "Jaylen Waddle", "Justin Jefferson" };
            DefenseStats defenseStats = new DefenseStats(2022);
            //ModelOne model = new ModelOne(2021);
            Player player = null;
            TeamDefense team = null;
            for ( String name : first7) {
                player = new Player(name, 2023);
                for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                    for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES()) {
                        if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())))
                            team = teamDefense;
                    }
                    if ((game.getRecLine() != 0) && game.getREC() != -1) {
                        System.out.println("indep.add(" + team.getReceptionsPG() + ", " + game.getREC() + ");");
                    }
                }
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