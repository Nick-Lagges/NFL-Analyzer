package org.nfl.data;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Player {
    private ArrayList<String> SEASON_STATS;
    private ArrayList<String[]> SEASON_WEATHER;
    private ArrayList<PlayerGame> GAME_LOG;
    private String NAME;
    private String TEAM;
    private PlayerSearch playerSearch;

    public Player(String name) throws IOException, InterruptedException {
        NAME = name;
        playerSearch = new PlayerSearch(name, 2023);
        generateSeasonStats();
        String[] teamArray = playerSearch.TEAM.split(" ");
        TEAM = teamArray[teamArray.length-1];
        generateGameLog();
    }

    public void generateSeasonStats(){
        try {
            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader;
            if ( playerSearch.POSITION_INFO.contains("WR") ) {
                filereader = new FileReader(Utils.RECEIVING);
            } else if ( playerSearch.POSITION_INFO.contains("QB") ) {
                filereader = new FileReader(Utils.PASSING);
            }
            else {
                filereader = new FileReader(Utils.RUSHING);
            }
            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            // we are going to read data line by line
            while ( (nextRecord = csvReader.readNext()) != null ) {
                if ( ! nextRecord.toString().contains(NAME) ){
                    continue;
                }
                else{
                    for (String cell : nextRecord) {
                        SEASON_STATS.add(cell);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateGameLog() {
        ArrayList<String> OPPONENT = playerSearch.getStatString("opponent");
        ArrayList<String> AGE = playerSearch.getStatString("age");
        ArrayList<String> LOCATION = playerSearch.getStatString("location");
        ArrayList<Integer> WEEK = playerSearch.getStat("week");
        ArrayList<Integer> TAR = playerSearch.getStat("targets");
        ArrayList<Integer> REC = playerSearch.getStat("receptions");
        ArrayList<Integer> REC_YD = playerSearch.getStat("receiving yards");
        ArrayList<Integer> REC_TD = playerSearch.getStat("receiving touchdowns");
        ArrayList<Integer> RUSH_ATT = playerSearch.getStat("rushing attempts");
        ArrayList<Integer> RUSH_YD = playerSearch.getStat("rushing yards");
        ArrayList<Integer> RUSH_TD = playerSearch.getStat("rushing touchdowns");
        ArrayList<Integer> PASS_TD = playerSearch.getStat("passing touchdowns");
        ArrayList<Integer> PASS_ATT = playerSearch.getStat("passing attempts");
        ArrayList<Integer> PASS_COMP = playerSearch.getStat("passing completions");
        ArrayList<Integer> PASS_YD = playerSearch.getStat("passing yards");
        ArrayList<Integer> PASS_INT = playerSearch.getStat("passing interceptions");
        ArrayList<Integer> OFF_SNAP = playerSearch.getStat("offensive snaps");

        for ( int x = 0; x < LOCATION.size(); x++ ) {
            if ( LOCATION.get(x).isEmpty() ) {LOCATION.set(x, "home"); }
            else LOCATION.set(x, "away");
        }
        int gamesMissed = 0;
        int i = 0;
        for ( int j = 0; j < AGE.size(); j++ ){
            PlayerGame playerGame = new PlayerGame();
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
                playerGame.setOFF_SNAP(-1.0);
                gamesMissed++;
            }
            else {
                playerGame.setTAR(TAR.get(i));
                playerGame.setREC(REC.get(i));
                playerGame.setREC_YD(REC_YD.get(i));
                playerGame.setREC_TD(REC_TD.get(i));
                playerGame.setPASS_ATT(PASS_ATT.get(i));
                playerGame.setPASS_COMP(PASS_COMP.get(i));
                playerGame.setRUSH_ATT(RUSH_ATT.get(i));
                playerGame.setRUSH_YD(RUSH_YD.get(i));
                playerGame.setRUSH_TD(RUSH_TD.get(i));
                playerGame.setPASS_TD(PASS_TD.get(i));
                playerGame.setPASS_ATT(PASS_ATT.get(i));
                playerGame.setPASS_COMP(PASS_COMP.get(i));
                playerGame.setPASS_YD(PASS_YD.get(i));
                playerGame.setPASS_INT(PASS_INT.get(i));
                playerGame.setOFF_SNAP(OFF_SNAP.get(i));
                i++;
            }
            GAME_LOG.add(playerGame);
        }
    }

    public ArrayList<PlayerGame> getGAME_LOG() {
        return GAME_LOG;
    }

    public ArrayList<String> getSEASON_STATS() {
        return SEASON_STATS;
    }
}
