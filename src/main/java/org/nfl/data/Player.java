package org.nfl.data;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Player {
    private PlayerSeasonStats seasonStats;
    private final PlayerGameLog GAME_LOG = new PlayerGameLog();
    private final PlayerSearch playerSearch;
    public final String NAME;
    public final String POSITION;
    public final String TEAM;

    public Player(String name) throws IOException, InterruptedException {
        NAME = name;
        playerSearch = new PlayerSearch(NAME, 2023);
        POSITION = playerSearch.POSITION_INFO.split(" ")[1];
        TEAM = playerSearch.TEAM;
    }

    public void generateSeasonStats(){
        try {
            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader;
            if ( POSITION.equals("WR") || POSITION.equals("TE") ) {
                seasonStats = new WRSeasonStats();
                filereader = new FileReader(Utils.RECEIVING23);
            } else if ( POSITION.equals("QB") ) {
                seasonStats = new QBSeasonStats();
                filereader = new FileReader(Utils.PASSING23);
            }
            else {
                seasonStats = new RBSeasonStats();
                filereader = new FileReader(Utils.RUSHING23);
            }
            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            // we are going to read data line by line
            while ( (nextRecord = csvReader.readNext()) != null ) {
                if ( nextRecord[1].contains(NAME) ) {
                    seasonStats.setRank(nextRecord[0]);
                    seasonStats.setPlayer(nextRecord[1]);
                    seasonStats.setTeam(nextRecord[2]);
                    seasonStats.setAge(Integer.valueOf(nextRecord[3]));
                    seasonStats.setPosition(nextRecord[4]);
                    seasonStats.setGames(Integer.valueOf(nextRecord[5]));
                    seasonStats.setGamesStarted(Integer.valueOf(nextRecord[6]));
                    if ( POSITION.equals("WR") || POSITION.equals("TE") ) {
                        seasonStats.setTargets(Integer.valueOf(nextRecord[7]));
                        seasonStats.setReceptions(Integer.valueOf(nextRecord[8]));
                        seasonStats.setCatchPct(nextRecord[9]);
                        seasonStats.setYards(Integer.valueOf(nextRecord[10]));
                        seasonStats.setYpr(Double.valueOf(nextRecord[11]));
                        seasonStats.setTouchdowns(Integer.valueOf(nextRecord[12]));
                        seasonStats.setFirstDowns(Integer.valueOf(nextRecord[13]));
                        seasonStats.setSuccessPct(nextRecord[14]);
                        seasonStats.setLongest(Integer.valueOf(nextRecord[15]));
                        seasonStats.setYpt(Double.valueOf(nextRecord[16]));
                        seasonStats.setRpg(Double.valueOf(nextRecord[17]));
                        seasonStats.setYpg(Double.valueOf(nextRecord[18]));
                        seasonStats.setFumbles(Integer.valueOf(nextRecord[19]));
                        seasonStats.setId(nextRecord[20]);
                    } else if ( POSITION.equals("QB")) {
                        seasonStats.setRecord(nextRecord[7]);
                        seasonStats.setCompletions(Integer.valueOf(nextRecord[8]));
                        seasonStats.setAttempts(Integer.valueOf(nextRecord[9]));
                        seasonStats.setCompletionPct(nextRecord[10]);
                        seasonStats.setYards(Integer.valueOf(nextRecord[11]));
                        seasonStats.setTouchdowns(Integer.valueOf(nextRecord[12]));
                        seasonStats.setTouchdownPct(nextRecord[13]);
                        seasonStats.setInterceptions(Integer.valueOf(nextRecord[14]));
                        seasonStats.setInterceptionPct(nextRecord[15]);
                        seasonStats.setFirstDowns(Integer.valueOf(nextRecord[16]));
                        seasonStats.setSuccessPct(nextRecord[17]);
                        seasonStats.setLongest(Integer.valueOf(nextRecord[18]));
                        seasonStats.setYpa(Double.valueOf(nextRecord[19]));
                        seasonStats.setAypa(Double.valueOf(nextRecord[20]));
                        seasonStats.setYpc(Double.valueOf(nextRecord[21]));
                        seasonStats.setYpg(Double.valueOf(nextRecord[22]));
                        seasonStats.setRate(Double.valueOf(nextRecord[23]));
                        seasonStats.setQbr(Double.valueOf(nextRecord[24]));
                        seasonStats.setSacks(Integer.valueOf(nextRecord[25]));
                        seasonStats.setSackYards(Integer.valueOf(nextRecord[26]));
                        seasonStats.setSackPct(nextRecord[27]);
                        seasonStats.setNypa(Double.valueOf(nextRecord[28]));
                        seasonStats.setAnypa(Double.valueOf(nextRecord[29]));
                        seasonStats.setComebacks(Integer.valueOf(nextRecord[30]));
                        seasonStats.setGwd(Integer.valueOf(nextRecord[31]));
                        seasonStats.setId(nextRecord[32]);
                    }
                    else {
                        seasonStats.setAttempts(Integer.valueOf(nextRecord[7]));
                        seasonStats.setYards(Integer.valueOf(nextRecord[8]));
                        seasonStats.setTouchdowns(Integer.valueOf(nextRecord[9]));
                        seasonStats.setFirstDowns(Integer.valueOf(nextRecord[10]));
                        seasonStats.setSuccessPct(nextRecord[11]);
                        seasonStats.setLongest(Integer.valueOf(nextRecord[12]));
                        seasonStats.setYpa(Double.valueOf(nextRecord[13]));
                        seasonStats.setYpg(Double.valueOf(nextRecord[14]));
                        seasonStats.setFumbles(Integer.valueOf(nextRecord[15]));
                        seasonStats.setId(nextRecord[16]);
                    }
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateGameLog() throws IOException {
        ArrayList<String> DATE = playerSearch.getStatString("date");
        ArrayList<String> OPPONENT = playerSearch.getStatString("opponent");
        ArrayList<String> AGE = playerSearch.getStatString("age");
        AGE.removeLast();
        ArrayList<String> LOCATION = playerSearch.getStatString("location");
        ArrayList<Integer> WEEK = playerSearch.getStat("week");
        ArrayList<Integer> TAR = null;
        ArrayList<Integer> REC = null;
        ArrayList<Integer> REC_YD = null;
        ArrayList<Integer> REC_TD = null;
        ArrayList<Integer> RUSH_ATT = null;
        ArrayList<Integer> RUSH_YD = null;
        ArrayList<Integer> RUSH_TD = null;
        ArrayList<Integer> PASS_TD = null;
        ArrayList<Integer> PASS_ATT = null;
        ArrayList<Integer> PASS_COMP = null;
        ArrayList<Integer> PASS_YD = null;
        ArrayList<Integer> PASS_INT = null;
        if ( POSITION.equals("WR") || POSITION.equals("TE") ) {
            TAR = playerSearch.getStat("targets");
            REC = playerSearch.getStat("receptions");
            REC_YD = playerSearch.getStat("receiving yards");
            REC_TD = playerSearch.getStat("receiving touchdowns");
            RUSH_ATT = playerSearch.getStat("rushing attempts");
            RUSH_YD = playerSearch.getStat("rushing yards");
            RUSH_TD = playerSearch.getStat("rushing touchdowns");
        } else if ( POSITION.equals("RB") ) {
            RUSH_ATT = playerSearch.getStat("rushing attempts");
            RUSH_YD = playerSearch.getStat("rushing yards");
            RUSH_TD = playerSearch.getStat("rushing touchdowns");
            TAR = playerSearch.getStat("targets");
            REC = playerSearch.getStat("receptions");
            REC_YD = playerSearch.getStat("receiving yards");
            REC_TD = playerSearch.getStat("receiving touchdowns");
        } else if ( POSITION.equals("QB") ) {
            PASS_TD = playerSearch.getStat("passing touchdowns");
            PASS_ATT = playerSearch.getStat("passing attempts");
            PASS_COMP = playerSearch.getStat("passing completions");
            PASS_YD = playerSearch.getStat("passing yards");
            PASS_INT = playerSearch.getStat("passing interceptions");
            RUSH_ATT = playerSearch.getStat("rushing attempts");
            RUSH_YD = playerSearch.getStat("rushing yards");
            RUSH_TD = playerSearch.getStat("rushing touchdowns");
        }
        ArrayList<String> OFF_SNAP = playerSearch.getStatString("offensive snaps");

        for ( int x = 0; x < LOCATION.size(); x++ ) {
            if ( LOCATION.get(x).isEmpty() ) {LOCATION.set(x, "home"); }
            else LOCATION.set(x, "away");
        }
        int gamesMissed = 0;
        int i = 0;
        for ( int j = 0; j < WEEK.size(); j++ ){
            PlayerGame playerGame = new PlayerGame();
            playerGame.setDate(DATE.get(j));
            playerGame.setOPPONENT(OPPONENT.get(j));
            playerGame.setWEEK(WEEK.get(j));
            playerGame.setHomeOrAway(LOCATION.get(j));
            if ( AGE.get(j).isEmpty() ) {
                playerGame.setTAR(-1);
                playerGame.setREC(-1);
                playerGame.setREC_YD(-1);
                playerGame.setREC_TD(-1);
                playerGame.setPASS_ATT(-1);
                playerGame.setPASS_COMP(-1);
                playerGame.setRUSH_ATT(-1);
                playerGame.setRUSH_YD(-1);
                playerGame.setRUSH_TD(-1);
                playerGame.setPASS_TD(-1);
                playerGame.setPASS_ATT(-1);
                playerGame.setPASS_COMP(-1);
                playerGame.setPASS_YD(-1);
                playerGame.setPASS_INT(-1);
                playerGame.setOFF_SNAP("-1.0%");
                gamesMissed++;
            }
            else {
                if ( POSITION.equals("WR") || POSITION.equals("TE") ) {
                    playerGame.setTAR(TAR.get(i));
                    playerGame.setREC(REC.get(i));
                    playerGame.setREC_YD(REC_YD.get(i));
                    playerGame.setREC_TD(REC_TD.get(i));
                    playerGame.setRUSH_ATT(RUSH_ATT.get(i));
                    playerGame.setRUSH_YD(RUSH_YD.get(i));
                    playerGame.setRUSH_TD(RUSH_TD.get(i));
                } else if ( POSITION.equals("RB") ) {
                    playerGame.setTAR(TAR.get(i));
                    playerGame.setREC(REC.get(i));
                    playerGame.setREC_YD(REC_YD.get(i));
                    playerGame.setREC_TD(REC_TD.get(i));
                    playerGame.setRUSH_ATT(RUSH_ATT.get(i));
                    playerGame.setRUSH_YD(RUSH_YD.get(i));
                    playerGame.setRUSH_TD(RUSH_TD.get(i));
                }
                else if ( POSITION.equals("QB") ) {
                    playerGame.setPASS_TD(PASS_TD.get(i));
                    playerGame.setPASS_ATT(PASS_ATT.get(i));
                    playerGame.setPASS_COMP(PASS_COMP.get(i));
                    playerGame.setPASS_YD(PASS_YD.get(i));
                    playerGame.setPASS_INT(PASS_INT.get(i));
                    playerGame.setRUSH_ATT(RUSH_ATT.get(i));
                    playerGame.setRUSH_YD(RUSH_YD.get(i));
                    playerGame.setRUSH_TD(RUSH_TD.get(i));
                }
                playerGame.setOFF_SNAP(OFF_SNAP.get(i));
                i++;
            }
            if ( playerGame.getHomeORAway().equals("home") ) playerGame.setHomeTeam(TEAM);
            else playerGame.setHomeTeam(Utils.ABBR_TO_TEAM.get(playerGame.getOPPONENT()));
            playerGame.generateWeather();
            GAME_LOG.addGame(playerGame);
        }
    }

    public PlayerGameLog getGAME_LOG() throws IOException {
        if ( GAME_LOG.GAME_LOG.isEmpty() ) generateGameLog();
        return GAME_LOG;
    }

    public PlayerSeasonStats getSEASON_STATS() {
        if ( seasonStats == null ) generateSeasonStats();
        return seasonStats;
    }
}
