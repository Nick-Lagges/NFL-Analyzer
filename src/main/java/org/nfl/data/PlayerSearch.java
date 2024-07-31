package org.nfl.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static org.nfl.data.Utils.*;

/*
 * Nick Lagges
 */

public class PlayerSearch {
    private final static String URI = "https://pro-football-reference.com/players/";
    private final String NAME;
    private final int YEAR;
    public String POSITION_INFO;
    private Document DOCUMENT;
    private String URL;
    public String TEAM;
    private Random rand = new Random();

    public PlayerSearch(String name, int year) throws IOException, InterruptedException {
        NAME = name;
        YEAR = year;
        URL = pathGetter();
        DOCUMENT = Jsoup.connect(URL).get();
        POSITION_INFO = getPosition();
        TEAM = Utils.ABBR_TO_TEAM.get(getStatString("team").getFirst().toLowerCase());
        //Thread.sleep(500 + rand.nextInt(1500));
    }

    private String getPosition() {
        String positionInfo;
        positionInfo = DOCUMENT.getElementById("meta").select("p").get(1).text();
        return positionInfo;
    }


    public ArrayList<String> getStatString(String statName) {
        ArrayList<String> stat = new ArrayList<>();
        try {
            String node = "td[data-stat=\"" + STAT_TO_ID.get(statName) + "\"]";
            if (statName.equals("location") || statName.equals("age") ) {
                Elements elements = DOCUMENT.getElementById("stats").select(node);
                for (int a = 0; a < elements.size(); a++) {
                    stat.add(elements.get(a).text());
                }
            }
            else {
                String[] statList = DOCUMENT.getElementById("stats").select(node).text().split(" ");
                for (String statRecord : statList) {
                    stat.add(statRecord);
                }
            }
            return stat;
        } catch ( Exception e ) {
            e.printStackTrace();
            throw new NullPointerException("stat not found");
        }
    }

    public ArrayList<Integer> getStat(String statName) {
        ArrayList<Integer> stat = new ArrayList<>();
        try {
            String node = "td[data-stat=\"" + STAT_TO_ID.get(statName) + "\"]";
            String[] statList = DOCUMENT.getElementById("stats").select(node).text().split(" ");
            for ( String statRecord : statList ) {
                stat.add(Integer.valueOf(statRecord));
            }
            if ( ! statName.equals("week") ){ stat.removeLast(); }
            return stat;
        } catch ( Exception e ) {
            e.printStackTrace();
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
            e.printStackTrace();
            throw new NullPointerException("stat not found");
        }
    }

    private String pathGetter() throws IOException {
        String[] nameList = NAME.split(" ");
        StringBuilder path = new StringBuilder().append(URI);
        if ( NAME.equals("DJ Moore") ) path.append(nameList[1].charAt(0) + "/" + nameList[1].substring(0,4) + nameList[0].charAt(0) + ".");
        else if (NAME.equals("Amon-Ra St. Brown") ) path.append("S/StxxAm");
        else  path.append(nameList[1].charAt(0) + "/" + nameList[1].substring(0,4) + nameList[0].substring(0,2));
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
                    try {
                        Document testDoc = Jsoup.connect(testURL.toString()).get();
                        Element responseStatus = testDoc.getElementById("stats");
                        String positionInfo = testDoc.getElementById("meta").select("p").get(1).text().split(" ")[1];
                        if (responseStatus != null && ( positionInfo.contains("WR") || positionInfo.contains("QB") || positionInfo.contains("RB") || positionInfo.contains("TE"))) {
                            id.append(i).append(j);
                            return id.toString();
                        }
                    } catch (NullPointerException e){
                    }
                }
            }
        } catch( Exception e ) {
            e.printStackTrace();
            throw new NullPointerException("player not found");
        }
        return "not found";
    }

}