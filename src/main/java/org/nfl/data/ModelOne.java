package org.nfl.data;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * predicts player targets
 */
public class ModelOne {

    private DefenseStats defenseStats;

    public ModelOne(int year) {
        defenseStats = new DefenseStats(year);
    }

    public double performModel(String stat, String opponent) throws IOException {
        TeamDefense opp = null;
        for ( TeamDefense team : defenseStats.getNFL_DEFENSES() ) {
            if ( team.getTeamName().toLowerCase().contains(opponent) ) opp = team;
        }
        double statValue = 0.0;
        if ( stat.equals("receptions") ) {
            try {
                statValue = opp.getReceptionsPG();
                statValue *= 0.62;
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        return statValue;
    }


}
