package org.nfl.data;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private String URI = "https://www.pro-football-reference.com";
    private String URL;
    private Document DOCUMENT;

    public Game(String address) throws IOException, InterruptedException {
        URL = URI + address;
        getDoc();
    }

    public void getDoc() throws IOException, InterruptedException {
        int maxTries = 5;
        boolean success = false;
        String[] proxies = {
                "152.26.229.86:9443",
                "152.26.229.42:9443",
                "35.185.196.38:3128"
        };
        Random rand = new Random();
        int i = 0;
        while ( ( i < maxTries ) && !success) {
            String proxy = proxies[rand.nextInt(0, proxies.length)];
            try {
                String[] proxyParts = proxy.split(":");
                String proxyHost = proxyParts[0];
                int proxyPort = Integer.parseInt(proxyParts[1]);

                Connection connection = Jsoup.connect(URL)
                        .referrer("http://www.google.com")
                        .proxy(proxyHost, proxyPort)
                        .timeout(10000);// Set a reasonable timeout

                Thread.sleep(rand.nextInt(1000, 5000));
                DOCUMENT = connection.get();
                System.out.println("Proxy " + proxy + " is valid.");
                success = true;
            } catch (Exception e) {
                i++;
                System.out.println("Proxy " + proxy + " is invalid: " + e.getMessage());
            }
        }
    }

    public ArrayList<String> getGameInfo() {
        /*
         * Won Toss
         * Roof
         * Surface
         * Duration
         * Attendance
         * Weather (optional)
         * Vegas Line
         * Over/Under (optional)
         */
        ArrayList<String> gameMetaInfo = new ArrayList<>();
        String statNode = "td[data-stat=\"stat\"]";
        Element[] gameInfo = Jsoup.parse(DOCUMENT.getElementById("all_game_info").getAllElements().get(0).data()).getElementById("div_game_info").select(statNode).toArray(new Element[0]);
        for ( Element e : gameInfo ) {
            gameMetaInfo.add(e.text());
        }
        return gameMetaInfo;
    }

    public ArrayList<ArrayList<String>> getGameStats() {
        /*
         * home stats, visitor stats
         * First Downs
         * Rush-Yds-TDs
         * Cmp-Att-Yd-TD-INT
         * Sacked-Yards
         * Net Pass Yards
         * Total Yards
         * Fumbles-Lost
         * Turnovers
         * Penalties-Yards
         * Third Down Conv.
         * Fourth Down Conv.
         * Time of Possession
         */
        ArrayList<String> homeStats = new ArrayList<>();
        ArrayList<String> visStats = new ArrayList<>();
        ArrayList<ArrayList<String>> gameStats = new ArrayList<>();
        String homeStatNode = "td[data-stat=\"home_stat\"]";
        String visStatNode = "td[data-stat=\"vis_stat\"]";
        Element[] homeStatArray = Jsoup.parse(DOCUMENT.getElementById("all_team_stats").getAllElements().get(0).data()).getElementById("team_stats").select(homeStatNode).toArray(new Element[0]);
        Element[] visStatArray = Jsoup.parse(DOCUMENT.getElementById("all_team_stats").getAllElements().get(0).data()).getElementById("team_stats").select(visStatNode).toArray(new Element[0]);
        for (Element e : homeStatArray) {
            homeStats.add(e.text());
        }
        for (Element e : visStatArray) {
            visStats.add(e.text());
        }
        gameStats.add(homeStats);
        gameStats.add(visStats);
        return gameStats;
    }
}
