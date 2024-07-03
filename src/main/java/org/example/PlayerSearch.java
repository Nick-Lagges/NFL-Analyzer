package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlayerSearch {
    private final static String URI = "https://pro-football-reference.com/players/";
    private final static Map<String,String> statToId = new HashMap<>();
    private String playerName;
    private int searchYear;
    private String URL;

    public void init(String name, int year) {
        playerName = name;
        searchYear = year;
        URL = pathGetter();
        statToId.put("targets", "targets");
        statToId.put("receptions" , "rec");
        statToId.put("receiving yards", "rec_yds");
        statToId.put("receiving touchdowns", "rec_td");
        statToId.put("rushing attempts", "rush_att");
        statToId.put("rushing yards", "rush_yds");
        statToId.put("rushing touchdowns", "rush_td");
        statToId.put("passing completions", "pass_cmp");
        statToId.put("passing attempts", "pass_att");
        statToId.put("passing yards", "pass_yds");
        statToId.put("passing touchdowns", "pass_td");
        statToId.put("passing interceptions", "pass_int");
        statToId.put("offensive snaps", "off_pct");

    }

    public ArrayList<Integer> getStat(String statName) throws IOException {
        ArrayList<Integer> stat = new ArrayList<>();
        try {
            Document document = Jsoup.connect(URL).get();
            String node = "td[data-stat=\"" + statToId.get(statName) + "\"]";
            String[] statList = document.getElementById("stats").select(node).text().split(" ");
            //int[] statListInt;
            for ( String statRecord : statList ) {
                stat.add(Integer.valueOf(statRecord));
            }
            stat.removeLast();
            return stat;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String pathGetter() {
        String[] nameList = playerName.split(" ");
        StringBuilder path = new StringBuilder()
                .append(URI)
                .append(nameList[1].charAt(0) + "/" + nameList[1].substring(0,4) + nameList[0].substring(0,2));
        if ( playerName.equals("Davante Adams") ) { path.append("01"); }
        else { path.append("00");}
        path.append("/gamelog/" + searchYear + "/");
        return path.toString();
    }

}