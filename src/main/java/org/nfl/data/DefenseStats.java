package org.nfl.data;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DefenseStats implements Comparable<TeamDefense>{

    private Integer YEAR;

    private ArrayList<TeamDefense> NFL_DEFENSES = new ArrayList<>();

    public DefenseStats(Integer year) {
        YEAR = year;
    }

    public void generateStats() throws IOException {
        FileReader fileReaderAll;
        if ( YEAR == 2023 ) fileReaderAll = new FileReader(Utils.DEFENSE23);
        else fileReaderAll = new FileReader(Utils.DEFENSE22);
        CSVReader csvReader = new CSVReader(fileReaderAll);
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
        FileReader fileReaderWR  = new FileReader(Utils.VSWR22);
        CSVReader csvReaderWR = new CSVReader(fileReaderWR);
        String[] nextRecordWR;
        // we are going to read data line by line
        while ( (nextRecordWR = csvReaderWR.readNext()) != null ) {
            if ( nextRecordWR[0].equals("Tm") ) continue;
            for ( TeamDefense teamDefense : NFL_DEFENSES ){
                if ( teamDefense.getTeamName().contains(nextRecordWR[0]) ){
                    teamDefense.setTargetsPG(Double.valueOf(nextRecordWR[2]));
                    teamDefense.setReceptionsPG(Double.valueOf(nextRecordWR[3]));
                    teamDefense.setRecYardsPG(Double.valueOf(nextRecordWR[4]));
                    teamDefense.setRecTDPG(Double.valueOf(nextRecordWR[5]));
                }
            }
        }
    }

    public ArrayList<TeamDefense> getNFL_DEFENSES() throws IOException {
        if (NFL_DEFENSES.isEmpty()) this.generateStats();
        return NFL_DEFENSES;
    }

    public void sortTeams(String statComparer) throws IOException {
        if ( NFL_DEFENSES.isEmpty() ) this.generateStats();
        Collections.sort(NFL_DEFENSES, new Comparator<>() {
            public int compare(TeamDefense o1, TeamDefense o2) {
                if ( statComparer.equals("points per game") ){
                    if (o1.getPointsPG() == o2.getPointsPG())
                        return 0;
                    return o1.getPointsPG() < o2.getPointsPG() ? -1 : 1;
                } else if ( statComparer.equals("yards per game") ) {
                    if (o1.getYardsPG() == o2.getYardsPG())
                        return 0;
                    return o1.getYardsPG() < o2.getYardsPG() ? -1 : 1;
                } else if ( statComparer.equals("plays per game") ) {
                    if (o1.getPlaysPG() == o2.getPlaysPG())
                        return 0;
                    return o1.getPlaysPG() < o2.getPlaysPG() ? -1 : 1;
                } else if ( statComparer.equals("yards per play") ) {
                    if (o1.getYardsPerPlay() == o2.getYardsPerPlay())
                        return 0;
                    return o1.getYardsPerPlay() < o2.getYardsPerPlay() ? -1 : 1;
                } else if ( statComparer.equals("turnovers per game") ) {
                    if (o1.getTurnoversPG() == o2.getTurnoversPG())
                        return 0;
                    return o1.getTurnoversPG() < o2.getTurnoversPG() ? -1 : 1;
                }
                return 0;
            }
        });
    }

    @Override
    public int compareTo(TeamDefense o) {
        return 0;
    }
}
