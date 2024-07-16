package org.nfl.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.nfl.data.Utils.*;

/*
 * Nick Lagges
 */

public class DefenseSearch {
    private final static String URI = "https://www.pro-football-reference.com/years/";
    private final String TEAM;
    private final int YEAR;
    private Document DOCUMENT;

    public DefenseSearch(String team, int year) throws IOException {
        TEAM = team;
        YEAR = year;

    }

    public double getStatAgainst(String statName, String position) throws IOException {
        String url = pathGetter(position);
        try {
            DOCUMENT = Jsoup.connect(url).get();
            Thread.sleep(1000);
            String statNode = "td[data-stat=\"" + STAT_TO_ID.get(statName) + "\"]";
            StringBuilder cssQuery = new StringBuilder("a[href=\"/teams/");
            cssQuery.append(TEAM_TO_LOCATOR.get(TEAM.toLowerCase())).append("/").append(YEAR).append(".htm\"]");
            double stat = Double.parseDouble(DOCUMENT.getElementById("all_fantasy_def").select(cssQuery.toString()).parents().get(1).select(statNode).text());
            return stat;
        } catch ( Exception e ) {
            throw new NullPointerException("stat not found");
        }
    }

    private String pathGetter(String position) throws IOException {
        StringBuilder url = new StringBuilder(URI);
        url.append(YEAR + "/fantasy-points-against-" + position.toUpperCase() + ".htm");
        return url.toString();
    }

}