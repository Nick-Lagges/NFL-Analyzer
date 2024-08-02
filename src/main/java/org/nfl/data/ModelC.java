package org.nfl.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ModelC {

    private DefenseStats defenseStats;

    public ModelC(int previousYear) {
        defenseStats = new DefenseStats(previousYear);
    }

    public double performModel(String stat, PlayerGame game) throws IOException {
        TeamDefense opp = null;
        for ( TeamDefense team : defenseStats.getNFL_DEFENSES() ) {
            if ( team.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())) ) opp = team;
        }
        double intercept = 3.98751;
        double a = 0.0202;
        double b = 0.12090;
        double c = 0.52750;
        double d = -1.52773;
        double e = 1.19721;
        double f = 0.04514;
        double g = -0.62111;
        double h = 0.79309;
        double i = -0.66946;
        double j = -1.33456;
        double k = -0.09934;

        if ( stat.equals("receptions") ) {
            try {
                a *= game.getFeelsLike();
                b *=  game.getVisibility();
                c *= game.getRecLine();
                d *= opp.getReceptionsPG();
                e *= opp.getTargetsPG();
                f *= opp.getYardsPG();
                g *= opp.getFirstDownsPG();
                h *= opp.getPassCompsPG();
                i *= opp.getPassAttPG();
                j *= opp.getPassTDPG();
                k *= opp.getScoresPerOffDrivePG();
            } catch (NullPointerException ex){
                ex.printStackTrace();
            }
        }
        double statValue = intercept + a + b + c + d + e + f + g + h + i + j + k;
        return round(statValue, 4);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

