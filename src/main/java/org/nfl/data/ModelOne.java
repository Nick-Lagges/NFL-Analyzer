package org.nfl.data;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;
import java.util.List;

/*
 * predicts player targets
 */
public class ModelOne {

    private PlayerSearch PLAYER;

    public ModelOne(PlayerSearch player) {
        PLAYER = player;
    }

    public double performModel() {
        PLAYER.getStat("receptions", 7);
        ArrayList<Integer> mooreRec = new ArrayList<>(List.of(7, 6, 9, 10, 8));
        return getMean(mooreRec);
    }

    private double getMean(ArrayList<Integer> list) {
        double mean = 0;
        for ( Integer val : list ) {
            mean += val;
        }
        mean = mean / list.size();
        return regression(10.0);
    }

    public static double calculateSD(ArrayList<Integer> numList) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numList.size();
        for (double num : numList) {
            sum += num;
        }
        double mean = sum / length;
        for (double num : numList) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation / length);
    }

    private double regression(double predictor) {
        SimpleRegression regression = new SimpleRegression(true);

        double[][] season = new double[][] {{1, 1}, {2, 2}};
        regression.addData(season);

        return regression.predict(predictor);
    }
}
