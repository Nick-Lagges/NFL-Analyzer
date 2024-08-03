package org.nfl.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ModelD {

    private DefenseStats defenseStats;

    public ModelD(int previousYear) {
        defenseStats = new DefenseStats(previousYear);
    }

    /*
                            Estimate Std. Error t value Pr(>|t|)
(Intercept)                 2.578878   1.074573   2.400 0.017009 *
game.getVisibility          0.040561   0.013247   3.062 0.002399 **
game.getRecLine            -0.097733   0.025253  -3.870 0.000134 ***
game.getDewPoint            0.002043   0.001143   1.787 0.074976 .
opp.getReceptionsPG        -0.074720   0.033657  -2.220 0.027161 *
opp.getFirstDownsPG        -0.134843   0.047595  -2.833 0.004921 **
opp.getPassAttPG           -0.046430   0.030168  -1.539 0.124840
opp.getPassTDPG            -0.281177   0.137607  -2.043 0.041893 *
opp.getPassIntPG           -0.431322   0.174053  -2.478 0.013758 *
opp.getPassFirstDownsPG     0.275628   0.092014   2.996 0.002968 **
opp.getPenaltiesPG          0.115115   0.068427   1.682 0.093550 .
opp.getScoresPerOffDrivePG -0.028842   0.013739  -2.099 0.036628 *
opp.getYardsPerPlay         0.218968   0.153798   1.424 0.155562
---
Signif. codes:  0 ‘***’ 0.001 ‘**’ 0.01 ‘*’ 0.05 ‘.’ 0.1 ‘ ’ 1
     */
    public double performModel(String stat, PlayerGame game) throws IOException {
        TeamDefense opp = null;
        for ( TeamDefense team : defenseStats.getNFL_DEFENSES() ) {
            if ( team.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())) ) opp = team;
        }
        double intercept = 2.578878;
        double a = 0.040561;
        double b = -0.097733;
        double c = 0.002043;
        double d = -0.074720;
        double e = -0.134843;
        double f = -0.046430;
        double g = -0.281177;
        double h = -0.431322;
        double i = 0.275628;
        double j = 0.115115;
        double k = -0.028842;
        double l = 0.218968;

        if ( stat.equals("receptions") ) {
            try {
                a *= game.getVisibility();
                b *=  game.getRecLine();
                c *= game.getDewPoint();
                d *= opp.getReceptionsPG();
                e *= opp.getFirstDownsPG();
                f *= opp.getPassAttPG();
                g *= opp.getPassTDPG();
                h *= opp.getPassFirstDownsPG();
                i *= opp.getPenaltiesPG();
                j *= opp.getScoresPerOffDrivePG();
                k *= opp.getYardsPerPlay();
            } catch (NullPointerException ex){
                ex.printStackTrace();
            }
        }
        double statValue = intercept + a + b + c + d + e + f + g + h + i + j + k + l;
        return round(statValue, 4);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

