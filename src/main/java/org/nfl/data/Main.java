package org.nfl.data;

import java.util.*;

public class Main {
    private static String[] wrList = { "Tee Higgins", "DJ Moore", "AJ Brown", "Keenan Allen", "Brandon Aiyuk", "Tyreek Hill",
            "CeeDee Lamb", "Michael Pittman", "Stefon Diggs", "Diontae Johnson", "Davante Adams",
            "Adam Thielen", "Tyler Lockett", "Christian Kirk", "Chris Godwin", "Mike Evans",
            "DeVonta Smith", "Amari Cooper", "DeAndre Hopkins", "Justin Jefferson",
            "Amon-Ra St. Brown", "Cooper Kupp", "Terry McLaurin", "DK Metcalf", "Deebo Samuel" };

    private static String[] qbList = { "Patrick Mahomes", "Josh Allen", "Justin Herbert", "Derek Carr", "Jared Goff",
            "Kirk Cousins", "Matthew Stafford", "Joe Burrow", "Russell Wilson", "Geno Smith",
            "Dak Prescott", "Baker Mayfield", "Tua Tagovailoa", "Aaron Rodgers", "Kyler Murray",
            "Trevor Lawrence", "Jalen Hurts", "Ryan Tannehill", "Lamar Jackson", "Daniel Jones" };

    private static HashMap<String, String[]> correlations = new HashMap<>();

    public static void main(String[] args) {
        try {
            correlations.put("Patrick Mahomes", new String[]{"Travis Kelce", "JuJu Smith-Schuster"});
            correlations.put("Jacoby Brissett", new String[]{"Amari Cooper", "Donovan Peoples-Jones"});
            correlations.put("Deshaun Watson", new String[]{"Amari Cooper", "Donovan Peoples-Jones"});
            correlations.put("Tua Tagovailoa", new String[]{"Tyreek Hill", "Jaylen Waddle"});
            correlations.put("Jalen Hurts", new String[]{"AJ Brown", "DeVonta Smith"});
            correlations.put("Jimmy Garoppolo", new String[]{"Deebo Samuel", "Brandon Aiyuk"});
            correlations.put("Brock Purdy", new String[]{"Deebo Samuel", "Brandon Aiyuk"});
            correlations.put("Joe Burrow", new String[]{"Ja'Marr Chase", "Tee Higgins"});
            correlations.put("Russell Wilson", new String[]{"Courtland Sutton", "Jerry Jeudy"});
            correlations.put("Tom Brady", new String[]{"Mike Evans", "Chris Godwin"});
            correlations.put("Trevor Lawrence", new String[]{"Christian Kirk", "Zay Jones"});
            correlations.put("Geno Smith", new String[]{"Tyler Lockett", "DK Metcalf"});
            correlations.put("Justin Herbert", new String[]{"Mike Williams", "Josh Palmer"});
            correlations.put("Mitchell Trubisky", new String[]{"Diontae Johnson", "George Pickens"});
            correlations.put("Kenny Pickett", new String[]{"Diontae Johnson", "George Pickens"});
            correlations.put("Josh Allen", new String[]{"Stefon Diggs", "Gabriel Davis"});
            correlations.put("Matt Ryan", new String[]{"Michael Pittman", "Parris Campbell"});
            correlations.put("Dak Prescott", new String[]{"CeeDee Lamb", "Dalton Schultz"});
            correlations.put("Aaron Rodgers", new String[]{"Allen Lazard", "Christian Watson"});
            correlations.put("Justin Fields", new String[]{"Cole Kmet", "Darnell Mooney"});
            correlations.put("Dak Prescott", new String[]{"CeeDee Lamb", "Dalton Schultz"});

            //correlations.put("Patrick Mahomes", new String[]{"Travis Kelce", "Rashee Rice"});

            //correlations.put("Joe Flacco", new String[]{"Amari Cooper", "David Njoku", "Elijah Moore"});
            //correlations.put("Deshaun Watson", new String[]{"Amari Cooper", "David Njoku", "Elijah Moore"});

            //correlations.put("Tua Tagovailoa", new String[]{"Tyreek Hill", "Jaylen Waddle"});

            //correlations.put("Jalen Hurts", new String[]{"AJ Brown", "DeVonta Smith"});

            //correlations.put("Brock Purdy", new String[]{"Deebo Samuel", "Brandon Aiyuk", "George Kittle"});

            //correlations.put("Joe Burrow", new String[]{"Ja'Marr Chase", "Tee Higgins", "Tyler Boyd"});
            //correlations.put("Jake Browning", new String[]{"Ja'Marr Chase", "Tee Higgins", "Tyler Boyd"});

            //correlations.put("Russell Wilson", new String[]{"Courtland Sutton", "Jerry Jeudy"});
            //correlations.put("Jarrett Stidham", new String[]{"Courtland Sutton", "Jerry Jeudy"});

            //correlations.put("Baker Mayfield", new String[]{"Mike Evans", "Chris Godwin"});

            //correlations.put("Trevor Lawrence", new String[]{"Calvin Ridley", "Christian Kirk", "Evan Engram"});

            //correlations.put("Geno Smith", new String[]{"Tyler Lockett", "DK Metcalf"});

            //correlations.put("Justin Herbert", new String[]{"Keenan Allen", "Mike Williams", "Josh Palmer", "Quentin Johnston"});
            //correlations.put("Easton Stick", new String[]{"Keenan Allen", "Quentin Johnston"});

            //correlations.put("Kenny Pickett", new String[]{"Diontae Johnson", "George Pickens"});

            //correlations.put("Josh Allen", new String[]{"Stefon Diggs", "Gabriel Davis", "Dalton Kincaid"});

            //correlations.put("Anthony Richardson", new String[]{"Michael Pittman", "Josh Downs"});
            //correlations.put("Gardner Minshew", new String[]{"Michael Pittman", "Josh Downs"});

            //correlations.put("Dak Prescott", new String[]{"CeeDee Lamb", "Jake Ferguson"});

            //correlations.put("Jordan Love", new String[]{"Romeo Doubs", "Jayden Reed"});

            //correlations.put("Justin Fields", new String[]{"DJ Moore", "Cole Kmet"});
            //correlations.put("Tyson Bagent", new String[]{"DJ Moore", "Cole Kmet"});

            //correlations.put("Kirk Cousins", new String[]{"Justin Jefferson", "Jordan Addison", "TJ Hockenson"});
            //correlations.put("Nick Mullens", new String[]{"Jordan Addison", "TJ Hockenson", "Justin Jefferson"});

            //correlations.put("Kyler Murray", new String[]{"Marquise Brown", "Trey McBride"});
            //correlations.put("Joshua Dobbs", new String[]{"Marquise Brown", "Trey McBride"});

            //correlations.put("Matthew Stafford", new String[]{"Puka Nacua", "Cooper Kupp", "Tyler Higbee"});

            //correlations.put("Jared Goff", new String[]{"Amon-Ra St. Brown", "Sam LaPorta"});

            //correlations.put("CJ Stroud", new String[]{"Nico Collins", "Tank Dell", "Noah Brown"});

            //correlations.put("Jimmy Garoppolo", new String[]{"Davante Adams", "Jakobi Meyers"});
            //correlations.put("Aidan O'Connell", new String[]{"Davante Adams", "Jakobi Meyers"});

            //correlations.put("Zach Wilson", new String[]{"Garrett Wilson", "Tyler Conklin"});

            //correlations.put("Trevor Siemian", new String[]{"Garrett Wilson", "Tyler Conklin"});

            //correlations.put("Mac Jones", new String[]{"Demario Douglas", "DeVante Parker"});
            //correlations.put("Bailey Zappe", new String[]{"Demario Douglas", "DeVante Parker"});

            //correlations.put("Will Levis", new String[]{"DeAndre Hopkins", "Chigoziem Okonkwo"});
            //correlations.put("Ryan Tannehill", new String[]{"DeAndre Hopkins", "Chigoziem Okonkwo"});

            //correlations.put("Lamar Jackson", new String[]{"Zay Jones", "Mark Andrews", "Isaiah Likely"});

            //correlations.put("Sam Howell", new String[]{"Terry McLaurin", "Jahan Dotson", "Curtis Samuel"});

            //correlations.put("Desmond Ridder", new String[]{"Drake London", "Kyle Pitts"});

            //correlations.put("Bryce Young", new String[]{"Adam Thielen", "Jonathan Mingo"});

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
            Random rand = new Random();
            DefenseStats defenseStats = new DefenseStats(2021);

            /*for (Map.Entry<String, String[]> entry : correlations.entrySet()) {
                String qbName = entry.getKey();
                System.out.println(qbName);
                player = new Player(qbName, 2023);
                player.getGAME_LOG();
                for ( String name : entry.getValue() ) {
                    System.out.println(name);
                    player = new Player(name, 2023);
                    player.getGAME_LOG();
                }
                System.out.println("done");
            }
            */
            int totalWon = 0;
            int totalBets = 0;
            for (Map.Entry<String, String[]> entry : correlations.entrySet()) {
                String qbName = entry.getKey();
                String corr1Name = entry.getValue()[0];
                String corr2Name = entry.getValue()[1];
                //System.out.println(qbName + " " + corr1Name + " " + corr2Name);

                Player qb = new Player(qbName, 2022);
                PlayerGameLog qbGAMELog = qb.getGAME_LOG();
                Player corr1 = new Player(corr1Name, 2022);
                PlayerGameLog corr1GAMELog = corr1.getGAME_LOG();
                Player corr2 = new Player(corr2Name, 2022);
                PlayerGameLog corr2GAMELog = corr2.getGAME_LOG();

                double passYPG = qb.getSEASON_STATS(2021).getYpg();
                double recYPG1 = corr1.getSEASON_STATS(2021).getYpg();
                double recYPG2 = corr2.getSEASON_STATS(2021).getYpg();
                ArrayList<Integer> passYards = new ArrayList();
                ArrayList<Integer> recYards1 = new ArrayList();
                ArrayList<Integer> recYards2 = new ArrayList();
                double medianPassYPG;
                double medianRecYPG1;
                double medianRecYPG2;

                //System.out.println(qb.TEAM);
                int allOver = 0;
                int allUnder = 0;
                int totalGames = 0;
                for (int week = 1; week < qbGAMELog.GAME_LOG.size() + 1; week++) {
                    int over = 0;
                    int under = 0;
                    int result = 0; // 0 = none, 1 = under, 2 = over
                    if ( qbGAMELog.getWeek(week).getPASS_YD() != -1 && corr1GAMELog.getWeek(week).getREC_YD() != -1 && corr2GAMELog.getWeek(week).getREC_YD() != -1 && qbGAMELog.getWeek(week).getPassYdLine() != 0  && corr1GAMELog.getWeek(week).getRecYdLine() != 0 && corr2GAMELog.getWeek(week).getRecYdLine() != 0 && qbGAMELog.getWeek(week).getOPPONENT().equals(corr2GAMELog.getWeek(week).getOPPONENT())) {//&& Math.abs(qbGAMELog.getWeek(week).getSpread()) < 10 && qbGAMELog.getWeek(week).getOverUnder() > 39.5) {
                        for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES()) {
                            if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(qb.getGAME_LOG().getWeek(week).getOPPONENT())))
                                team = teamDefense;
                        }
                        if ( qbGAMELog.getWeek(week).getPassYdLine() < qbGAMELog.getWeek(week).getPASS_YD() ) over++;
                        else if ( qbGAMELog.getWeek(week).getPassYdLine() > qbGAMELog.getWeek(week).getPASS_YD() ) under++;

                        if ( corr1GAMELog.getWeek(week).getRecYdLine() < corr1GAMELog.getWeek(week).getREC_YD() ) over++;
                        else if ( corr1GAMELog.getWeek(week).getRecYdLine() > corr1GAMELog.getWeek(week).getREC_YD() ) under++;

                        if ( corr2GAMELog.getWeek(week).getRecYdLine() < corr2GAMELog.getWeek(week).getREC_YD() ) over++;
                        else if ( corr2GAMELog.getWeek(week).getRecYdLine() > corr2GAMELog.getWeek(week).getREC_YD() ) under++;

                        if ( over == 3 ) {
                            result = 2;
                            allOver +=1;
                        }
                        else if ( under == 3 ) {
                            result = 1;
                            allUnder +=1;
                        }
                        totalGames+=1;
                        if ( passYards.size() != 0 && passYards.get((passYards.size()/2)) != null ) medianPassYPG = passYards.get((passYards.size() / 2));
                        else medianPassYPG = passYPG;
                        if ( recYards1.size() != 0 && recYards1.get((recYards1.size()/2)) != null ) medianRecYPG1 = recYards1.get((recYards1.size() / 2));
                        else medianRecYPG1 = recYPG1;
                        if ( recYards2.size() != 0 && recYards2.get((recYards2.size()/2)) != null ) medianRecYPG2 = recYards2.get((recYards2.size() / 2));
                        else medianRecYPG2 = recYPG2;

                        if ( true ) {
                            System.out.print("DUP " + qbName + " " + corr1Name + " " + corr2Name);
                            String formattedString = String.format(", %s, %s, %.1f, %d, %.1f, %d, %.1f, %d, %d, %d, %.1f, ", corr2GAMELog.getWeek(week).getDate(), qbGAMELog.getWeek(week).getOPPONENT(),
                                    qbGAMELog.getWeek(week).getPassYdLine(), qbGAMELog.getWeek(week).getPASS_YD(),
                                    corr1GAMELog.getWeek(week).getRecYdLine(), corr1GAMELog.getWeek(week).getREC_YD(),
                                    corr2GAMELog.getWeek(week).getRecYdLine(), corr2GAMELog.getWeek(week).getREC_YD(),
                                    result, qbGAMELog.getWeek(week).getOverUnder(), qbGAMELog.getWeek(week).getSpread());
                       String formattedInfo = String.format("%.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f",
                                team.getReceptionsPG(), team.getPlaysPG(), team.getTargetsPG(), team.getExpectedPointsPG(), team.getPointsPG(),
                                team.getNetYdsPerPassAtt(), team.getRushYdsPG(), team.getRushYdsPerAtt(), team.getRushFirstDownsPG(), team.getRecTDPG(),
                                team.getYardsPerPlay(), team.getTurnoversPG(), team.getFumblesPG(), team.getFirstDownsPG(), team.getPassCompsPG(),
                                team.getPassAttPG(), team.getPassTDPG(), team.getPassIntPG(), team.getPassFirstDownsPG(), team.getPenaltiesPG(),
                                team.getPenaltyFirstDownsPG(), team.getScoresPerOffDrivePG(), team.getYardsPG(), corr1GAMELog.getWeek(week).getFeelsLike(), corr1GAMELog.getWeek(week).getWindSpeed(),
                                passYPG, recYPG1, recYPG2, medianPassYPG, medianRecYPG1, medianRecYPG2);
                            System.out.print(formattedString);
                            System.out.println(formattedInfo);
                        }
                        passYards.add(qbGAMELog.getWeek(week).getPASS_YD());
                        recYards1.add(corr1GAMELog.getWeek(week).getREC_YD());
                        recYards2.add(corr2GAMELog.getWeek(week).getREC_YD());
                        Collections.sort(passYards);
                        Collections.sort(recYards1);
                        Collections.sort(recYards2);
                    }
                }
                //System.out.println("Games: " + totalGames + " O: " + allOver + " U: " + allUnder);
                totalWon += allOver + allUnder;
                totalBets += totalGames;
                System.out.println("done");
            }
            //System.out.println("Bets: " + totalBets*2 + " Won: " + totalWon + " odds needed: " + (double) totalWon/(totalBets*2));

            //RandomForestRegressor randForest = new RandomForestRegressor(3);
            //ModelOne model = new ModelOne(2022);
            //player = new Player("Amon-Ra St. Brown", 2021);
            //player.getGAME_LOG();
            /*
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
            for (int i = 2023; i < 2024; i++) {
                SuccessModel model = new SuccessModel(i-1);
                defenseStats = new DefenseStats(i);
                for (String name : wrList) {
                    player = new Player(name, i);
                    System.out.println(name);
                    PlayerSeasonStats stats = player.getSEASON_STATS(i-1);
                    double avgRec = (double) stats.getReceptions() / stats.getGames();
                    double avgTar = (double) stats.getTargets() / stats.getGames();
                    for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                        for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES()) {
                            if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())))
                                team = teamDefense;
                        }
                        line = game.getRecLine();

                        if (line != 0.0 && game.getREC() != -1) {
                            pred = randForest.predictDataPoint(new double[]{
                                    team.getReceptionsPG(), team.getPlaysPG(), team.getTargetsPG(), team.getExpectedPointsPG(), team.getPointsPG(),
                                    team.getYardsPerPlay(), team.getTurnoversPG(), team.getFumblesPG(), team.getFirstDownsPG(), team.getPassCompsPG(),
                                    team.getPassAttPG(), team.getPassTDPG(), team.getPassIntPG(), team.getPassFirstDownsPG(), team.getPenaltiesPG(),
                                    team.getPenaltyFirstDownsPG(), team.getScoresPerOffDrivePG(), team.getYardsPG(), game.getFeelsLike(), game.getWindSpeed(),
                                    game.getWindDirection(), game.getRecLine(), game.getWEEK(), avgRec, avgTar} );
                            pred = model.performModel("receptions", game, avgRec);
                            act = game.getREC();
                            //System.out.println(line + " : " + act);
                            double conf = Math.abs(pred - line);
                            if ( conf > -0.5 ) {
                                c = (((pred > line) && (act > line)) || ((pred < line) && (act < line)));

                                double diff = Math.abs(pred - act);
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
                                String formatS = String.format("correct: %d | total: %d | line: %.2f | prediction: %.3f | actual: %d | percent: %.2f%%", correct, gameNum, line, pred, act, ((double) correct / gameNum * 100));
                                //String formatS = String.format("{ %f, %f, %f, %f, %d, %f }, ",
                                //line, act, pred, conf, diff,
                                //       team.getTargetsPG(), team.getYardsPerPlay(), team.getScoresPerOffDrivePG(), game.getRecLine(), game.getWEEK(), game.getRecYdLine());
                                System.out.println(formatS);
                            }
                            //String formattedString = String.format("2.5 %d-%d | 3.5 %d-%d | 4.5 %d-%d | 5.0 %d-%d | 5.5 %d-%d | 6.5 %d-%d | 7.5 %d-%d", twopointfiveC, twopointfiveO, threepointfiveC, threepointfiveO, fourpointfiveC, fourpointfiveO, fivepointC, fivepointO, fivepointfiveC, fivepointfiveO, sixpointfiveC, sixpointfiveO, sevenpointfiveC, sevenpointfiveO);
                            //System.out.println(formattedString);
                        }
                    }
                }
            }
            */
            /*for ( int i = 2022; i < 2023; i++) {
                defenseStats = new DefenseStats(i-1);
                for (String s : wrList) {
                    //System.out.println(s);
                    player = new Player(s, i);
                    PlayerSeasonStats stats = player.getSEASON_STATS(i-1);
                    double avgRec = 0.0;
                    double avgTar = 0.0;
                    if (stats.getGames() != 0) {
                        //avgRec = (double) stats.getReceptions() / stats.getGames();
                        //avgTar = (double) stats.getTargets() / stats.getGames();
                    }
                    for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                        for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES()) {
                            if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())))
                                team = teamDefense;
                        }
                        if ((game.getRecYdLine() != 0) && game.getREC_YD() != -1) {
                            overs += 1;
                            /*int over;
                            if ( game.getREC() > game.getRecLine() ) over = 1;
                            else over = 0;
                            String formattedString = String.format("%.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %d, %.2f, %.2f, %d",
                                    team.getReceptionsPG(), team.getPlaysPG(), team.getTargetsPG(), team.getExpectedPointsPG(), team.getPointsPG(),
                                    team.getYardsPerPlay(), team.getTurnoversPG(), team.getFumblesPG(), team.getFirstDownsPG(), team.getPassCompsPG(),
                                    team.getPassAttPG(), team.getPassTDPG(), team.getPassIntPG(), team.getPassFirstDownsPG(), team.getPenaltiesPG(),
                                    team.getPenaltyFirstDownsPG(), team.getScoresPerOffDrivePG(), team.getYardsPG(), game.getFeelsLike(), game.getWindSpeed(),
                                    game.getWindDirection(), game.getRecLine(), game.getWEEK(), avgRec, avgTar, over);
                            //String formattedString = String.format("{ %.4f, %.4f, %.4f, %.4f, %.4f },",
                            //       team.getReceptionsPG(), game.getWindSpeed(), game.getFeelsLike(), team.getExpectedPointsPG(), team.getFirstDownsPG());
                            String formattedString = String.format("");
                            System.out.println(formattedString);
                            //System.out.println("indep.add(" + game.getFeelsLike() + ", " + game.getREC() + ");");
                        }
                    }
                }
            } */



        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}