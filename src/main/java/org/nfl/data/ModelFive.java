package org.nfl.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ModelFive {

    private DefenseStats defenseStats;

    public ModelFive(int year) {
        defenseStats = new DefenseStats(year);
    }

    public double performModel(String stat, PlayerGame game) throws IOException {
        TeamDefense opp = null;
        for ( TeamDefense team : defenseStats.getNFL_DEFENSES() ) {
            if ( team.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())) ) opp = team;
        }
        double a = 0.4819;
        double b = 0.0827;
        double c = 0.5128;
        double d = -1.1857;
        double e = -0.4951;
        double f = 1.0670;
        double g = 0.0646;

        if ( stat.equals("receptions") ) {
            try {
                if ( game.getFeelsLike() > 34 && game.getFeelsLike() < 80 ) {
                    a *= opp.getPointsPG();
                    b *=  opp.getPlaysPG();
                    c *= opp.getExpectedPointsPG();
                    d *= opp.getReceptionsPG();
                    e *= opp.getPassAttPG();
                    f *= opp.getTargetsPG();
                    g *= opp.getScoresPerOffDrivePG();
                }
            } catch (NullPointerException ex){
                ex.printStackTrace();
            }
        }
        double statValue = a + b + c + d + e + f + g;
        return round(statValue, 4);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

