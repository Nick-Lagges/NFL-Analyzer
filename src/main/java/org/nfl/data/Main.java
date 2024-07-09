package org.nfl.data;

public class Main {
    public static void main(String[] args) {
        try {
            /*DataVisualization chart = new DataVisualization("Brandon Aiyuk", 2023, "receiving yards", 76.5, 10);
            chart.start();

            PlayerSearch moore = new PlayerSearch("DJ Moore", 2023);
            ModelOne modelOne = new ModelOne(moore);
            System.out.println(modelOne.performModel()); */

            DefenseSearch team = new DefenseSearch("bears", 2023);
            System.out.println(team.getStatAgainst("receiving touchdowns", "wr"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}