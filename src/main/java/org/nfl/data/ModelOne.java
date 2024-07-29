package org.nfl.data;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * predicts player targets
 */
public class ModelOne {

    private DefenseStats defenseStats;

    public ModelOne(int year) {
        defenseStats = new DefenseStats(year);
    }

    public double performModel(String stat, PlayerGame game) throws IOException {
        TeamDefense opp = null;
        for ( TeamDefense team : defenseStats.getNFL_DEFENSES() ) {
            if ( team.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())) ) opp = team;
        }
        double a = 0.0;
        double b = 0.0;
        double c = 0.0;
        double d = 0.0;
        if ( stat.equals("receptions") ) {
            try {
                a = opp.getReceptionsPG() * 0.5868;
                b = game.getWindSpeed() * -0.0983;
                c = game.getFeelsLike() * -0.0150;
                d = opp.getExpectedPointsPG() * 0.0156;
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        double statValue = a + b + c + d;
        return round(statValue, 4);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
