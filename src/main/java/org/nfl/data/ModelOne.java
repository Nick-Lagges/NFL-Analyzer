package org.nfl.data;

import java.util.ArrayList;
import java.util.List;

/*
 * predicts player targets
 */
public class ModelOne extends  Model{

    private PlayerSearch PLAYER;

    public ModelOne(PlayerSearch player) {
        PLAYER = player;
    }

    @Override
    public double performModel() {
        PLAYER.getStat("receptions", 7);
        ArrayList<Integer> mooreRec = new ArrayList<>(List.of(2, 7, 6, 9, 10));
        double SD = calculateSD(mooreRec);
        double mean = 0;
        for ( Integer rec : mooreRec ) {
            mean += rec;
        }
        mean = mean / mooreRec.size();
        return mean;
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
}
