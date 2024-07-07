package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            //System.setProperty("javax.net.ssl.trustStore", "\"C:\\Users\\nlagges\\Downloads\\pfr.jks.p12\"");
            PlayerSearch jetas = new PlayerSearch();
            jetas.init("Brandon Aiyuk", 2023);
            int totalYards = 0;
            System.out.println(jetas.getStat("receiving yards"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}