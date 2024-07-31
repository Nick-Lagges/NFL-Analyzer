package org.nfl.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ModelSeven {

    private DefenseStats defenseStats;

    public ModelSeven(int year) {
        defenseStats = new DefenseStats(year);
    }

    public double performModel(String stat, PlayerGame game) throws IOException {
        TeamDefense opp = null;
        for ( TeamDefense team : defenseStats.getNFL_DEFENSES() ) {
            if ( team.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())) ) opp = team;
        }
        double a = -0.6986046498014316;
        double b = 0.5596136177711213;
        double c = 0.040383977382759784;
        double d = 0.2974574590433117;

        if ( stat.equals("receptions") ) {
            try {
                a *= opp.getReceptionsPG();
                b *=  opp.getTargetsPG();
                c *= opp.getPassCompsPG();
                d *= opp.getPassFirstDownsPG();
            } catch (NullPointerException ex){
                ex.printStackTrace();
            }
        }
        double statValue = a + b + c + d ;
        return round(statValue, 4);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
