package org.nfl.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

import static org.nfl.data.Utils.*;

public class Team {
    private String NAME;
    private Integer YEAR;
    private String URI = "https://www.pro-football-reference.com/teams/";
    private String URL;
    private Document DOCUMENT;
    public ArrayList<Game> TEAM_GAMES;

    public Team(String name, Integer year) throws IOException, InterruptedException {
        NAME = name;
        YEAR = year;
        URL = pathGetter();
        DOCUMENT = Jsoup.connect(URL).get();
        TEAM_GAMES = getGames();
    }

    private ArrayList<String> getGameURLs() {
        ArrayList<String> gameURLs = new ArrayList<>();
        for (int i = 0; i < DOCUMENT.getElementById("games").select("a").size(); i++) {
            if ( i%2 == 0 ) {
                gameURLs.add(DOCUMENT.getElementById("games").select("a").get(i).attributes().toString().split("\"")[1]);
            }
        }
        Jsoup.parse(DOCUMENT.getElementById("games").text());
        return gameURLs;
    }

    public ArrayList<Game> getGames() throws IOException, InterruptedException {
        ArrayList<Game> seasonGames = new ArrayList<>();
        for ( String url : getGameURLs() ) {
            seasonGames.add(new Game(url));
        }
        return seasonGames;
    }

    private String pathGetter() {
        StringBuilder path = new StringBuilder(URI);
        path.append(TEAM_TO_LOCATOR.get(NAME)).append("/").append(YEAR).append(".htm");
        return path.toString();
    }
}
