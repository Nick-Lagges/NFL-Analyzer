package org.nfl.data;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DefenseStats {

    private Integer YEAR;

    private ArrayList<TeamDefense> NFL_DEFENSES = new ArrayList<>();

    public DefenseStats(Integer year) {
        YEAR = year;
    }

    public void generateStats() throws IOException {
        FileReader fileReader = new FileReader(Utils.DEFENSE23);
        CSVReader csvReader = new CSVReader(fileReader);
        String[] nextRecord;
        // we are going to read data line by line
        while ((nextRecord = csvReader.readNext()) != null) {
            if ( nextRecord[0].equals("Rk") ) continue;
            TeamDefense teamDefense = new TeamDefense();
            teamDefense.setTeamName(nextRecord[1]);
            teamDefense.setGames(Integer.valueOf(nextRecord[2]));
            teamDefense.setPointsPG(Double.valueOf(nextRecord[3]));
            teamDefense.setYardsPG(Double.valueOf(nextRecord[4]));
            teamDefense.setPlaysPG(Double.valueOf(nextRecord[5]));
            teamDefense.setYardsPerPlay(Double.valueOf(nextRecord[6]));
            teamDefense.setTurnoversPG(Double.valueOf(nextRecord[7]));
            teamDefense.setFumblesPG(Double.valueOf(nextRecord[8]));
            teamDefense.setFirstDownsPG(Double.valueOf(nextRecord[9]));
            teamDefense.setPassCompsPG(Double.valueOf(nextRecord[10]));
            teamDefense.setPassAttPG(Double.valueOf(nextRecord[11]));
            teamDefense.setPassYdsPG(Double.valueOf(nextRecord[12]));
            teamDefense.setPassTDPG(Double.valueOf(nextRecord[13]));
            teamDefense.setPassIntPG(Double.valueOf(nextRecord[14]));
            teamDefense.setNetYdsPerPassAtt(Double.valueOf(nextRecord[15]));
            teamDefense.setPassFirstDownsPG(Double.valueOf(nextRecord[16]));
            teamDefense.setRushAttPG(Double.valueOf(nextRecord[17]));
            teamDefense.setRushYdsPG(Double.valueOf(nextRecord[18]));
            teamDefense.setRushTDPG(Double.valueOf(nextRecord[19]));
            teamDefense.setRushYdsPerAtt(Double.valueOf(nextRecord[20]));
            teamDefense.setRushFirstDownsPG(Double.valueOf(nextRecord[21]));
            teamDefense.setPenaltiesPG(Double.valueOf(nextRecord[22]));
            teamDefense.setPenaltyYdsPG(Double.valueOf(nextRecord[23]));
            teamDefense.setPenaltyFirstDownsPG(Double.valueOf(nextRecord[24]));
            teamDefense.setScoresPerOffDrivePG(Double.valueOf(nextRecord[25]));
            teamDefense.setTurnoverPct(Double.valueOf(nextRecord[26]));
            teamDefense.setExpectedPointsPG(Double.valueOf(nextRecord[27]));
            NFL_DEFENSES.add(teamDefense);
        }
    }

    public ArrayList<TeamDefense> getNFL_DEFENSES() throws IOException {
        if (NFL_DEFENSES.isEmpty()) this.generateStats();
        return NFL_DEFENSES;
    }
}
