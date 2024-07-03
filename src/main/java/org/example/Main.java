package org.example;

public class Main {
    public static String url;

    public static void main(String[] args) {
        url = "https://www.pro-football-reference.com/players/";
        String mainLink = url + "J/JeffJu00" + ".htm";
        String recentLink = url + "J/JeffJu00/gamelog/2023/";
        try {
            //System.setProperty("javax.net.ssl.trustStore", "\"C:\\Users\\nlagges\\Downloads\\pfr.jks.p12\"");
            PlayerSearch jetas = new PlayerSearch();
            jetas.init("Justin Jefferson", 2022);
            int totalYards = 0;
            System.out.println(jetas.getStat("receiving yards"));
            System.out.println(jetas.getStat("receiving yards"));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}