package org.nfl.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * predicts player targets
 */
public class ModelOne {

    private DefenseStats defenseStats;

    public ModelOne(int previousYear) {
        defenseStats = new DefenseStats(previousYear);
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
        double e = 0.0;
        double f = 0.0;
        double g = 0.0;
        if ( stat.equals("receptions") ) {
            try {
                a = opp.getReceptionsPG() * -0.0466;
                b = game.getWindSpeed() * -0.1274;
                c = game.getFeelsLike() * 0.1060;
                d = opp.getExpectedPointsPG() * 0.2280;
                e = game.getVisibility() * 0.0062;
                f = game.getPrecipitation() * -0.0001;
                g = opp.getFirstDownsPG() * -0.0509;
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
