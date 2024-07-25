package org.nfl.data;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.xml.crypto.dsig.SignatureMethod;
import java.io.IOException;

public class NumberPredictor {
    public static class NumberPredictor1 extends ApplicationFrame {

        /*
         * trends based on scholarship.claremont.com
         * Home team:
         *  3.7% more pass yards, 4.6% more completion percentage
         *  11.5% more rush attempts, 25.5% more rush yards
         *
         * Wind:
         *  10mph: -6.8% passing yards, -2.4% completion percentage, 3.2% rush attempts, 4.5% rush yards
         *
         * Temperature:
         *  every 10 degree under 32 = -1.7% pass yards, -0.8% completion percentage
         *  Hot conditions (80+): -3.9& completion percentage, -6.5% pass yards
         *  Home and every 10 degree drop under 32: -2.3% rush yards
         *
         * Home vs Away:
         *  Visiting Wind: -9% pass yards, -3% completion percentage AND 5% rush attempts, rush yards 7% for 10mph winds (+1.2-1.8 rush att per game, 9.4% rush yards per 10mph wind)
         *  Home Wind: -5% pass yards and 2% completion percentage for 10mph winds
         *  Temperature:
         *      every +-10 degree over/under 80/32:
         *          Visiting = +-2.2% pass yards
         *          Home = +-1.62% pass yards
         *      Every 10 deg increase (over 80):
         *          Home: 1.2% more pass attempts than rush attempts, -1.5% rush att, -3.9% rush yards,
         *      Freezing games (sub 32):
         *          Home: -8.7% rush yards, -3.3% completion percentage
         *      Hot games (80+):
         *          visiting: -7.7% pass yards, -4.3% completion pct
         *
         * Teams from a home dome:
         *      10 mph wind: -6.3% pass yards, -5.1% comp pct, 4.6% rush att
         *      every 10mph wind: 6.
         * Teams from light wind:
         *      10 mph wind: -8.3% pass yards, -2.0% comp pct, 4.0% rush att
         * Teams from heavy wind:
         *      10 mph wind: -13.3% pass yards, -4.4% comp pct, 6.1% rush att
         *
         * Teams from warm climates:
         *      Every 10 degree difference: -3.6% pass yards, -1.6% comp pct, -1.7% tot yrds
         * Teams from mild climates:
         *      Every 10 degree difference: -2.4% pass yards, -0.9% comp pct, -1.5% tot yrds
         */

        public double[] mooreLines = new double[]{3.5, 3.5, 3.5, 4.5, 3.5, 4.5, 4.5, 4.5, 5.5, 4.5, 4.5, 4.5, 5.5, 4.5, 5.5, 4.5, 5.5};

        //temp F, humidity, wind, spread
        public double[][] mooreGameInfo = new double[][]{
                {75, 53, 7, -1.5},
                {89, 68, 6, 2.5},
                {70, 40, 0, 13},
                {80, 64, 2, 3},
                {70, 83, 1, 6},
                {53, 74, 16, 3},
                {52, 68, 8, 2.5},
                {70, 40, 0, 10.5},
                {70, 40, 0, 8.5},
                {51, 40, 8, -3.5},
                {70, 40, 0, 8},
                {70, 40, 0, 3},
                {36, 70, 12, 3},
                {45, 84, 15, 3},
                {52, 78, 4, -4},
                {32, 92, 5, -2.5},
                {34, 69, 7, 3}

        };

        public double[][] targetList = new double[][]{
                {1, 14.9},
                {2, 15.8},
                {3, 17},
                {4, 17.2},
                {5, 17.4},
                {6, 17.4},
                {7, 17.4},
                {8, 17.5},
                {9, 17.6},
                {10, 18},
                {11, 18},
                {12, 18.2},
                {13, 18.7},
                {14, 18.8},
                {15, 19},
                {16, 19},
                {17, 19.2},
                {18, 19.4},
                {19, 19.5},
                {20, 19.6},
                {21, 19.8},
                {22, 19.8},
                {23, 20.1},
                {24, 20.3},
                {25, 21},
                {26, 21},
                {27, 21.2},
                {28, 21.5},
                {29, 21.7},
                {30, 21.8},
                {31, 22.6},
                {32, 23.9}
        };

        public double[][] recList = new double[][]{
                {1, 8.82},
                {2, 9.82},
                {3, 10.2},
                {4, 10.4},
                {5, 10.6},
                {6, 10.8},
                {7, 10.9},
                {8, 11.1},
                {9, 11.3},
                {10, 11.4},
                {11, 11.4},
                {12, 11.4},
                {13, 11.4},
                {14, 11.5},
                {15, 11.6},
                {16, 11.8},
                {17, 11.9},
                {18, 11.9},
                {19, 12.4},
                {20, 12.5},
                {21, 12.7},
                {22, 12.8},
                {23, 13.1},
                {24, 13.2},
                {25, 13.3},
                {26, 13.4},
                {27, 13.4},
                {28, 13.6},
                {29, 14.0},
                {30, 14.7},
                {31, 14.9},
                {32, 15.1}
        };

        public double[][] mooreTarList = new double[][]{
                {4, 2},
                {24, 7},
                {15, 6},
                {8, 9},
                {22, 10},
                {29, 8},
                {9, 9},
                {18, 6},
                {14, 5},
                {2, 9},
                {25, 9},
                {29, 13},
                {25, 10},
                {13, 8},
                {3, 6},
                {5, 13},
                {4, 6}
        };

        public double[][] mooreRecList = new double[][]{
                {14, 2, 0},     //packers
                {25, 6, 1},    //tampa
                {6, 3, 1},    //kc
                {12, 8, 0},     //denver
                {27, 8, 1},    //was
                {31, 5, 0},    //min
                {15, 8, 0},     //raid
                {18, 4, 1},    //chargers
                {7, 3, 1},    //nola
                {3, 5, 0},      //car
                {26, 7, 1},     //det
                {31, 11, 1},   //min
                {26, 6, 0},    //det
                {2, 4, 1},    //cle
                {16, 3, 0},     //ari
                {4, 9, 0},     //atl
                {14, 4, 1}      //pack
        };

        public NumberPredictor1(String title) throws IOException, InterruptedException {
            super(title);
            JFreeChart barChart = ChartFactory.createLineChart(
                    "2023",
                    "week",
                    "wr rec/game",
                    createDatasetPrediction(),
                    PlotOrientation.VERTICAL,
                    true, true, false);
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            setContentPane(chartPanel);
        }

        private CategoryDataset createDataset() throws IOException, InterruptedException {
            SimpleRegression simpleRegression = new SimpleRegression(true);
            simpleRegression.addData(mooreTarList);
            final DefaultCategoryDataset dataset =
                    new DefaultCategoryDataset();
            for (double[] x : targetList) {
                dataset.addValue((x[1] * 0.52), "teams", Integer.valueOf((int) x[0]));
            }
            for (double[] x : mooreTarList) {
                dataset.addValue(x[1], "moore", Integer.valueOf((int) x[0]));
                System.out.println(x[0] + "," + x[1]);
            }
            for (double i = 1; i < 33; i++) {
                dataset.addValue(simpleRegression.predict(i), "prediction", Integer.valueOf((int) i));
                dataset.addValue(7.5, "line", Integer.valueOf((int) i));
            }

            return dataset;
        }

        private CategoryDataset createDatasetPrediction() throws IOException, InterruptedException {
            double REC_PCT = 0.475 * 0.982;
            double TEMP_WT = 0.0;
            double HUM_WT = 0.0;
            double WIN_WT = 0.0;
            double SPD_WT = 0.0;
            SimpleRegression simpleRegression = new SimpleRegression(true);
            simpleRegression.addData(recList);

            /*
            double[] tempArray = new double[mooreGameInfo.length];
            double[] humArray = new double[mooreGameInfo.length];
            double[] winArray = new double[mooreGameInfo.length];
            double[] spdArray = new double[mooreGameInfo.length];
            double[] mooreRecArray = new double[mooreRecList.length];

            for (int i = 0; i < mooreGameInfo.length; i++) {
                tempArray[i] = mooreGameInfo[i][0];
                humArray[i] = mooreGameInfo[i][1];
                winArray[i] = mooreGameInfo[i][2];
                spdArray[i] = mooreGameInfo[i][3];
                mooreRecArray[i] = mooreRecList[i][1];
            }
            SimpleRegression tempVsRec = makeReg(tempArray, mooreRecArray);
            SimpleRegression humVsRec = makeReg(humArray, mooreRecArray);
            SimpleRegression winVsRec = makeReg(winArray, mooreRecArray);
            SimpleRegression spdVsRec = makeReg(spdArray, mooreRecArray);
             */
            int correct = 0;
            int incorrect = 0;
            final DefaultCategoryDataset dataset =
                    new DefaultCategoryDataset();
            for (int i = 1; i < 18; i++) {
                double temp = mooreGameInfo[i - 1][0];
                double hum = mooreGameInfo[i - 1][1];
                double win = mooreGameInfo[i - 1][2];
                double spread = mooreGameInfo[i - 1][3];
                double pred = simpleRegression.predict(mooreRecList[i - 1][0]);
                double windchill = 35.74 + (0.6215 * temp) - (35.75 * Math.pow(win, 0.16)) + (0.4275 * temp * Math.pow(win, 0.16));

                if (windchill >= 80) {
                    if (mooreRecList[i - 1][2] == 0) pred *= 0.961;
                    else pred *= 0.957;
                }
                if (windchill <= 32) {
                    if (mooreRecList[i - 1][2] == 0) pred *= 0.967;
                    else pred *= 0.992;
                }

                if (mooreRecList[i - 1][2] == 0) pred *= Math.pow(0.97, win / 10);
                else pred *= Math.pow(0.98, win / 10);
                pred *= REC_PCT;

                double act = mooreRecList[i - 1][1];
                double line = mooreLines[i - 1];
                dataset.addValue(act, "actual", Integer.valueOf(i));
                dataset.addValue(pred, "predictionA", Integer.valueOf(i));
                dataset.addValue(line, "line", Integer.valueOf(i));

                //dataset.addValue(temp, "temp", Integer.valueOf(i));
                //dataset.addValue(hum, "humidity", Integer.valueOf(i));
                //dataset.addValue(win, "wind", Integer.valueOf(i));
                //dataset.addValue(spread, "spread", Integer.valueOf(i));

                if ((pred < line && act < line) || (pred > line && act > line)) correct++;
                else incorrect++;
                if (pred >= line) System.out.println("over");
                else System.out.println("under");
                System.out.println(correct + " - " + incorrect);
            }

            return dataset;
        }

        public void start() {
            this.pack();
            RefineryUtilities.centerFrameOnScreen(this);
            this.setVisible(true);
        }

        public SimpleRegression makeReg(double[] x, double[] y) {
            SimpleRegression simpleRegression = new SimpleRegression(true);
            double[][] dataSet = new double[x.length][2];
            for (int i = 0; i < x.length; i++) {
                dataSet[i][0] = x[i];
                dataSet[i][1] = y[i];
            }
            simpleRegression.addData(dataSet);
            return simpleRegression;
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // creating regression object, passing true to have intercept term
        NumberPredictor1 showData = new NumberPredictor1("test");
        showData.start();
        /*SimpleRegression simpleRegression = new SimpleRegression(true);

        // passing data to the model
        // model will be fitted automatically by the class
        simpleRegression.addData(showData.recList);

        // querying for model parameters
        System.out.println("slope = " + simpleRegression.getSlope());
        System.out.println("intercept = " + simpleRegression.getIntercept());

        // trying to run model for unknown data
        System.out.println("prediction = " + simpleRegression.predict(6)*0.52);
        System.out.println(simpleRegression.getSlopeConfidenceInterval());*/

    }
}