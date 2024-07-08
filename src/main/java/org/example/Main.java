package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            DataVisualization chart = new DataVisualization("Brandon Aiyuk", 2023, "receiving yards", 76.5);
            chart.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}