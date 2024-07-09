package org.nfl.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * Nick Lagges
 */

public class DefenseSearch {
    private final static String URI = "https://www.pro-football-reference.com/years/";
    private final static Map<String,String> STAT_TO_ID = new HashMap<>();
    private final static Map<String,String> TEAM_TO_LOCATOR = new HashMap<>();
    private final String TEAM;
    private final int YEAR;
    private Document DOCUMENT;

    public DefenseSearch(String team, int year) throws IOException {
        TEAM = team;
        YEAR = year;
        STAT_TO_ID.put("targets", "targets");
        STAT_TO_ID.put("receptions" , "rec");
        STAT_TO_ID.put("receiving yards", "rec_yds");
        STAT_TO_ID.put("receiving touchdowns", "rec_td");
        STAT_TO_ID.put("rushing attempts", "rush_att");
        STAT_TO_ID.put("rushing yards", "rush_yds");
        STAT_TO_ID.put("rushing touchdowns", "rush_td");
        STAT_TO_ID.put("passing completions", "pass_cmp");
        STAT_TO_ID.put("passing attempts", "pass_att");
        STAT_TO_ID.put("passing yards", "pass_yds");
        STAT_TO_ID.put("passing touchdowns", "pass_td");
        STAT_TO_ID.put("passing interceptions", "pass_int");
        STAT_TO_ID.put("offensive snaps", "off_pct");

        TEAM_TO_LOCATOR.put("commanders", "was");
        TEAM_TO_LOCATOR.put("eagles", "phi");
        TEAM_TO_LOCATOR.put("lions", "det");
        TEAM_TO_LOCATOR.put("san diego", "sdg");
        TEAM_TO_LOCATOR.put("giants", "nyg");
        TEAM_TO_LOCATOR.put("vikings", "min");
        TEAM_TO_LOCATOR.put("bengals", "cin");
        TEAM_TO_LOCATOR.put("rams", "ram");
        TEAM_TO_LOCATOR.put("titans", "oti");
        TEAM_TO_LOCATOR.put("buccaneers", "tam");
        TEAM_TO_LOCATOR.put("dolphins", "mia");
        TEAM_TO_LOCATOR.put("jaguars", "jax");
        TEAM_TO_LOCATOR.put("seahawks", "sea");
        TEAM_TO_LOCATOR.put("steelers", "pit");
        TEAM_TO_LOCATOR.put("49ers", "sfo");
        TEAM_TO_LOCATOR.put("cardinals", "crd");
        TEAM_TO_LOCATOR.put("raiders", "rai");
        TEAM_TO_LOCATOR.put("broncos", "den");
        TEAM_TO_LOCATOR.put("colts", "clt");
        TEAM_TO_LOCATOR.put("texans", "htx");
        TEAM_TO_LOCATOR.put("patriots", "nwe");
        TEAM_TO_LOCATOR.put("packers", "gnb");
        TEAM_TO_LOCATOR.put("saints", "nor");
        TEAM_TO_LOCATOR.put("falcons", "atl");
        TEAM_TO_LOCATOR.put("bills", "buf");
        TEAM_TO_LOCATOR.put("cowboys", "dal");
        TEAM_TO_LOCATOR.put("ravens", "rav");
        TEAM_TO_LOCATOR.put("browns", "cle");
        TEAM_TO_LOCATOR.put("panthers", "car");
        TEAM_TO_LOCATOR.put("chiefs", "kan");
        TEAM_TO_LOCATOR.put("jets", "nyj");
        TEAM_TO_LOCATOR.put("bears", "chi");
    }

    public double getStatAgainst(String statName, String position) throws IOException {
        String url = pathGetter(position);
        try {
            DOCUMENT = Jsoup.connect(url).get();
            String statNode = "td[data-stat=\"" + STAT_TO_ID.get(statName) + "\"]";
            StringBuilder cssQuery = new StringBuilder("a[href=\"/teams/");
            cssQuery.append(TEAM_TO_LOCATOR.get(TEAM.toLowerCase())).append("/").append(YEAR).append(".htm\"]");
            //DOCUMENT.getElementById("all_fantasy_def").select("a[href=\"/teams/was/2023.htm\"]").text();
            double stat = Double.parseDouble(DOCUMENT.getElementById("all_fantasy_def").select(cssQuery.toString()).parents().get(1).select(statNode).text());
            return stat;
        } catch ( Exception e ) {
            throw new NullPointerException("stat not found");
        }
    }

    public ArrayList<Integer> getStat(String statName, int gameCount) {
        ArrayList<Integer> stat = new ArrayList<>();
        try {
            String node = "td[data-stat=\"" + STAT_TO_ID.get(statName) + "\"]";
            String[] statList = DOCUMENT.getElementById("stats").select(node).text().split(" ");
            for ( int i = statList.length-2; i >= statList.length - gameCount - 1; i-- ) {
                stat.add(Integer.valueOf(statList[i]));
            }
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