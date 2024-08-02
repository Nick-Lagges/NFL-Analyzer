package org.nfl.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ModelA {

    private DefenseStats defenseStats;

    public ModelA(int previousYear) {
        defenseStats = new DefenseStats(previousYear);
    }

    public double performModel(String stat, PlayerGame game, double avgRec, double line) throws IOException {
        TeamDefense opp = null;
        for ( TeamDefense team : defenseStats.getNFL_DEFENSES() ) {
            if ( team.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())) ) opp = team;
        }
        double intercept = 249.7770;
        double a = 2.4298;
        double b = -1.6241;
        double c = 0.6744;
        double d = -3.4380;
        double e = -41.4877;
        double f = -0.9659;
        double g = -0.4656;
        double h = 0.6398;

        if ( stat.equals("receptions") ) {
            try {
                a *= opp.getReceptionsPG();
                b *=  opp.getTargetsPG();
                c *= opp.getYardsPG();
                d *= opp.getPlaysPG();
                e *= opp.getYardsPerPlay();
                f *= opp.getFirstDownsPG();
                g *= opp.getPassAttPG();
                h *= avgRec;
            } catch (NullPointerException ex){
                ex.printStackTrace();
            }
        }
        double statValue = intercept + a + b + c + d + e + f + g +h;
        double confidence = Math.abs(statValue-line);
        //if ( confidence < 2 ){ return round(statValue, 4); }
        //else return round(statValue, 4);
        return round(statValue, 4);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

