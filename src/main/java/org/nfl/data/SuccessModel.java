package org.nfl.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SuccessModel {

    private DefenseStats defenseStats;

    public SuccessModel(int previousYear) {
        defenseStats = new DefenseStats(previousYear);
    }

    /*
      linevec numsuccess numtotal successrate
 [1,]     2.5          1        1   1.0000000
 [3,]     3.5         30       51   0.5882353
 [5,]     4.5        159      289   0.5501730
 [6,]     5.0          2        3   0.6666667
 [7,]     5.5        103      214   0.4813084
 [8,]     6.0          0        2   0.0000000
 [9,]     6.5         67      133   0.5037594
[11,]     7.5         24       60   0.4000000
[13,]     8.5          2        4   0.5000000

 To find actual receptions = Coefficients:
                            Estimate Std. Error t value Pr(>|t|)
(Intercept)                 -3.88138    1.89895  -2.044 0.041306 *
team.getTargetsPG            0.19127    0.05746   3.329 0.000916 ***
team.getYardsPerPlay         1.14594    0.41587   2.756 0.006002 **
team.getScoresPerOffDrivePG -0.10498    0.03649  -2.877 0.004132 **
game.getRecLine              0.72357    0.08867   8.160 1.41e-15 ***
week                        -0.04238    0.01862  -2.276 0.023153 *
---

To find overs = Coefficients:
                      Estimate Std. Error t value Pr(>|t|)
(Intercept)           0.195133   0.346533   0.563  0.57353
team.getReceptionsPG -0.069311   0.035431  -1.956  0.05081 .
team.getTargetsPG     0.081534   0.027826   2.930  0.00349 **
team.getFirstDownsPG -0.066319   0.026486  -2.504  0.01249 *
team.getYardsPG       0.003086   0.001498   2.061  0.03969 *
avgRec                0.030606   0.019174   1.596  0.11087
game.getRecLine      -0.058608   0.020597  -2.846  0.00455 **
Signif. codes:  0 ‘***’ 0.001 ‘**’ 0.01 ‘*’ 0.05 ‘.’ 0.1 ‘ ’ 1

(Intercept)
  0.7127667

   linevec     probs
1      2.5 0.6238954
2      3.0 0.6048145
3      3.5 0.5854089
4      4.0 0.5657345
5      4.5 0.5458509
6      5.0 0.5258198
7      5.5 0.5057054
8      6.0 0.4855724
9      6.5 0.4654862
10     7.0 0.4455112
11     7.5 0.4257108
12     8.0 0.4061458
13     8.5 0.3868742

           Pass Comps:                 Estimate Std. Error t value Pr(>|t|)
(Intercept)                 174.83774   76.05988   2.299 0.022051 *
team.getPlaysPG              -2.67596    1.19800  -2.234 0.026070 *
team.getTargetsPG            -0.56393    0.27795  -2.029 0.043146 *
team.getYardsPerPlay        -27.39166   12.91843  -2.120 0.034607 *
team.getFirstDownsPG         -0.92573    0.56770  -1.631 0.103764
team.getPassIntPG            -3.02382    1.72950  -1.748 0.081184 .
team.getPassFirstDownsPG      2.18448    0.60680   3.600 0.000359 ***
team.getScoresPerOffDrivePG  -0.31135    0.14270  -2.182 0.029716 *
team.getYardsPG               0.48662    0.20892   2.329 0.020360 *
avgAtt                        0.16653    0.08809   1.891 0.059427 .
compLine                      0.48042    0.13053   3.680 0.000266 ***
     */
    public double performModel(String stat, PlayerGame game, double avgAtt) throws IOException {
        TeamDefense opp = null;
        for ( TeamDefense team : defenseStats.getNFL_DEFENSES() ) {
            if ( team.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())) ) opp = team;
        }

        double intercept = 0;
        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;
        double e = 0;
        double f = 0;
        double g = 0;
        double h = 0;
        double i = 0;
        double j = 0;
        double k = 0;
        double l = 0;

        double statValue = 0;
        if ( stat.equals("receptions") ) {
            intercept = -3.88138;
            a = 0.19127;
            b = 1.14594;
            c = -0.10498;
            d = 0.72357;
            e = -0.04238;
            a *= opp.getTargetsPG();
            b *=  opp.getYardsPerPlay();
            c *= opp.getScoresPerOffDrivePG();
            d *= game.getRecLine();
            e *= game.getWEEK();
            statValue = intercept + a + b + c + d + e;

            /*

            double confidence = statValue - game.getRecLine();
            intercept = -4.81297;
            a = 0.37143;
            b = -0.63357;
            c = 0.24967;
            d = 1.38427;
            e = -0.13479;
            f = -0.05165;
            g = 0.02928;
            a *= game.getRecLine();
            b *= confidence;
            c *= opp.getTargetsPG();
            d *= opp.getYardsPerPlay();
            e *= opp.getScoresPerOffDrivePG();
            f *= game.getWEEK();
            g *= game.getRecYdLine();

            statValue = intercept + a + b + c + d + e + f + g;

             */
        } else if ( stat.equals("completions") ){
            intercept = 174.83774;
            a = -2.67596;
            b = -0.56393;
            c = -27.39166;
            d = -0.92573;
            e = -3.02382;
            f = 2.18448;
            g = -0.31135;
            h = 0.48662;
            i = 0.16653;
            j = 0.48042;
            a *= opp.getPlaysPG();
            b *=  opp.getTargetsPG();
            c *= opp.getYardsPerPlay();
            d *= opp.getFirstDownsPG();
            e *= opp.getPassIntPG();
            f *= opp.getPassFirstDownsPG();
            g *= opp.getScoresPerOffDrivePG();
            h *= opp.getYardsPG();
            i *= avgAtt;
            j *= game.getPassCompLine();

            statValue = intercept + a + b + c + d + e + f + g + h + i + j;
        }
        return round(statValue, 4);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

