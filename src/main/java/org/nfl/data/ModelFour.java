package org.nfl.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ModelFour {

    private DefenseStats defenseStats;

    public ModelFour(int year) {
        defenseStats = new DefenseStats(year);
    }

    public double performModel(String stat, PlayerGame game) throws IOException {
        TeamDefense opp = null;
        for ( TeamDefense team : defenseStats.getNFL_DEFENSES() ) {
            if ( team.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())) ) opp = team;
        }
        double a = -0.213638;
        double b = 0.030906;
        double c = 0.187121;
        double d = 0.034117;
        double e = -0.074249;
        double f = 0.068246;
        double g = 0.688675;

        if ( stat.equals("receptions") ) {
            try {
                if ( game.getFeelsLike() > 34 && game.getFeelsLike() < 80 ) {
                    a *= game.getWindSpeed();
                    b *=  game.getFeelsLike();
                    c *= game.getVisibility();
                    d *= game.getCloudCover();
                    e *= game.getHumidity();
                    f *= game.getDewPoint();
                    g *= game.getRecLine();
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
