package org.nfl.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import static org.nfl.data.Utils.*;

/*
 * Nick Lagges
 */

public class PlayerSearch {
    private final static String URI = "https://pro-football-reference.com/players/";
    private final String NAME;
    private final int YEAR;
    private final Document DOCUMENT;

    public PlayerSearch(String name, int year) throws IOException {
        NAME = name;
        YEAR = year;
        String URL = pathGetter();
        DOCUMENT = Jsoup.connect(URL).get();
    }

    public ArrayList<Integer> getStat(String statName) {
        ArrayList<Integer> stat = new ArrayList<>();
        try {
            String node = "td[data-stat=\"" + STAT_TO_ID.get(statName) + "\"]";
            String[] statList = DOCUMENT.getElementById("stats").select(node).text().split(" ");
            for ( String statRecord : statList ) {
                stat.add(Integer.valueOf(statRecord));
            }
            stat.removeLast();
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

    private String pathGetter() throws IOException {
        String[] nameList = NAME.split(" ");
        StringBuilder path = new StringBuilder().append(URI);
        if (nameList[0].length() > 2 ) { path.append(nameList[1].charAt(0) + "/" + nameList[1].substring(0,4) + nameList[0].substring(0,2)); }
        else { path.append(nameList[1].charAt(0) + "/" + nameList[1].substring(0,4) + nameList[0].charAt(0) + "."); }
        path.append(handlePathIssue(path.toString()));
        path.append("/gamelog/" + YEAR + "/");
        return path.toString();
    }

    private String handlePathIssue(String path) throws IOException {
        StringBuilder id = new StringBuilder();
        try {
            StringBuilder testURL = new StringBuilder(path);
            testURL.append("00/gamelog/").append(YEAR).append("/");
            for ( Integer i = 0; i < 10; i++ ) {
                for ( Integer j = 0; j < 10; j++ ) {
                    testURL.deleteCharAt(51);
                    testURL.deleteCharAt(51);
                    testURL.insert(51, (i.toString() + j.toString()));
                    Element responseStatus = Jsoup.connect(testURL.toString()).get().getElementById("stats");
                    if ( responseStatus != null ) {
                        id.append(i).append(j);
                        return id.toString();
                    }
                }
            }
        } catch( Exception e ) {
            throw new NullPointerException("player not found");
        }
        return "not found";
    }

}