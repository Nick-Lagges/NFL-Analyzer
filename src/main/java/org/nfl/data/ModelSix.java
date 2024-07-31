package org.nfl.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ModelSix {

    private DefenseStats defenseStats;

    public ModelSix(int year) {
        defenseStats = new DefenseStats(year);
    }

    public double performModel(String stat, PlayerGame game) throws IOException {
        TeamDefense opp = null;
        for ( TeamDefense team : defenseStats.getNFL_DEFENSES() ) {
            if ( team.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())) ) opp = team;
        }
        double a =  -0.3765;
        double b =  0.3653;
        double c =  -0.0525;
        double d =  3.1185;
        double e =  -0.0113;
        double f =  -2.1031;
        double g =  -0.1242;
        double h =  -1.1196;
        double i =  2.1174;

        if ( stat.equals("receptions") ) {
            try {
                a *= opp.getPointsPG();
                b *=  opp.getPlaysPG();
                c *= opp.getExpectedPointsPG();
                d *= opp.getReceptionsPG();
                e *= opp.getPassAttPG();
                f *= opp.getTargetsPG();
                g *= opp.getScoresPerOffDrivePG();
                h *= opp.getPassCompsPG();
                i *= opp.getPassFirstDownsPG();

            } catch (NullPointerException ex){
                ex.printStackTrace();
            }
        }
        double statValue = a + b + c + d + e + f + g + h + i;
        return round(statValue, 4);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
