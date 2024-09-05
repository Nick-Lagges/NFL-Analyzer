package org.nfl.data;

import com.opencsv.CSVReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LineSearch {
    public ArrayList<Double> getReceptionsLines() {
        return receptionsLines;
    }

    public void setReceptionsLines(ArrayList<Double> receptionsLines) {
        this.receptionsLines = receptionsLines;
    }

    public ArrayList<Double> getReceivingYardsLines() {
        return receivingYardsLines;
    }

    public void setReceivingYardsLines(ArrayList<Double> receivingYardsLines) {
        this.receivingYardsLines = receivingYardsLines;
    }

    public ArrayList<Double> getRushingAttemptsLines() {
        return rushingAttemptsLines;
    }

    public void setRushingAttemptsLines(ArrayList<Double> rushingAttemptsLines) {
        this.rushingAttemptsLines = rushingAttemptsLines;
    }

    public ArrayList<Double> getRushingYardsLines() {
        return rushingYardsLines;
    }

    public void setRushingYardsLines(ArrayList<Double> rushingYardsLines) {
        this.rushingYardsLines = rushingYardsLines;
    }

    public ArrayList<Double> getCompletionsLines() {
        return completionsLines;
    }

    public void setCompletionsLines(ArrayList<Double> completionsLines) {
        this.completionsLines = completionsLines;
    }

    public ArrayList<Double> getPassAttemptsLines() {
        return passAttemptsLines;
    }

    public void setPassAttemptsLines(ArrayList<Double> passAttemptsLines) {
        this.passAttemptsLines = passAttemptsLines;
    }

    public ArrayList<Double> getPassTDLines() {
        return passTDLines;
    }

    public void setPassTDLines(ArrayList<Double> passTDLines) {
        this.passTDLines = passTDLines;
    }

    public ArrayList<Double> getPassIntsLines() {
        return passIntsLines;
    }

    public void setPassIntsLines(ArrayList<Double> passIntsLines) {
        this.passIntsLines = passIntsLines;
    }

    public ArrayList<Double> getPassYardsLines() {
        return passYardsLines;
    }

    public void setPassYardsLines(ArrayList<Double> passYardsLines) {
        this.passYardsLines = passYardsLines;
    }

    private ArrayList<Double> receptionsLines = new ArrayList<>();
    private ArrayList<Double> receivingYardsLines = new ArrayList<>();
    private ArrayList<Double> rushingAttemptsLines = new ArrayList<>();
    private ArrayList<Double> rushingYardsLines = new ArrayList<>();
    private ArrayList<Double> completionsLines = new ArrayList<>();
    private ArrayList<Double> passAttemptsLines = new ArrayList<>();
    private ArrayList<Double> passTDLines = new ArrayList<>();
    private ArrayList<Double> passIntsLines = new ArrayList<>();
    private ArrayList<Double> passYardsLines = new ArrayList<>();

    private String PLAYER_NAME;
    private Integer YEAR;
    private String URI = "https://evanalytics.com";
    private String pastLineUrls;

    public LineSearch(String playerName, Integer year){
        YEAR = year;
        String fName = playerName.split(" ")[0];
        String lName = playerName.split(" ")[1];
        if ( fName.equals("DJ") ) fName = "D.J.";
        else if ( fName.equals("AJ")) fName = "A.J.";
        else if ( fName.equals("CJ") ) fName = "C.J.";
        else if ( fName.equals("KJ") ) fName = "K.J.";
        else if ( fName.equals("TJ") ) fName = "T.J.";
        if ( playerName.equals("Amon-Ra St. Brown") ) PLAYER_NAME = playerName;
        else PLAYER_NAME = fName + " " + lName;
        for ( int x = 0; x < 19; x++) {
            receptionsLines.add(x, 0d);
            receivingYardsLines.add(x, 0d);
            rushingAttemptsLines.add(x, 0d);
            rushingYardsLines.add(x, 0d);
            passAttemptsLines.add(x, 0d);
            completionsLines.add(x, 0d);
            passTDLines.add(x, 0d);
            passIntsLines.add(x, 0d);
            passYardsLines.add(x, 0d);
        }
    }

    public void findLines() throws IOException {
        if ( YEAR > 2021 ) {
            StringBuilder lineUrl = new StringBuilder(URI);
            lineUrl.append("/nfl/writenow/player-props/week-");
            boolean found = false;
            int week = 1;
            String yearFind = "&season=" + YEAR;
            try {
                if ( PLAYER_NAME.equals("Tom Brady") && YEAR == 2022) {
                    pastLineUrls = "/nfl/writenow/player-props/2276-tom-brady&season=2022";
                    found = true;
                } else if (PLAYER_NAME.equals("Jacoby Brissett") && YEAR == 2022) {
                    pastLineUrls = "/nfl/writenow/player-props/6207-jacoby-brissett&season=2022";
                    found = true;
                } else if (PLAYER_NAME.equals("Matt Ryan") && YEAR == 2022) {
                    pastLineUrls = "/nfl/writenow/player-props/2859-matt-ryan&season=2022";
                    found = true;
                } else {
                    while (!found && week < 19) {
                        lineUrl.append(week);
                        Document document = Jsoup.connect(lineUrl.toString()).get();
                        ArrayList<Element> players = document.select("tr").select("a");
                        for (Element e : players) {
                            if (e.text().equals(PLAYER_NAME)) {
                                pastLineUrls = e.attributes().get("href");
                                found = true;
                                break;
                            }
                        }
                        if (week > 9) {
                            lineUrl.deleteCharAt(lineUrl.length() - 1);
                            lineUrl.deleteCharAt(lineUrl.length() - 1);
                        }
                        else lineUrl.deleteCharAt(lineUrl.length() - 1);
                        week++;
                    }
                }
                if (found) {
                    String url = URI + pastLineUrls;
                    Document playerDoc = Jsoup.connect(url + yearFind).get();
                    Integer currWeek = 0;
                    String weekFinder = "";
                    for (Element e : playerDoc.select("span[style=\"background-color: transparent;\"]")) {
                        weekFinder = e.select("a").attr("href");
                        currWeek = Integer.valueOf(weekFinder.substring(weekFinder.lastIndexOf("-") + 1));
                        if (currWeek > 18) continue;
                        if (e.text().contains("Receptions")) {
                            receptionsLines.add(currWeek, Double.valueOf(e.text().split("[(]")[0].split("Under")[1]));
                            receptionsLines.remove(currWeek + 1);
                        } else if (e.text().contains("Receiving Yards")) {
                            receivingYardsLines.add(currWeek, Double.valueOf(e.text().split("[(]")[0].split("Under")[1]));
                            receivingYardsLines.remove(currWeek + 1);
                        } else if (e.text().contains("Carries")) {
                            rushingAttemptsLines.add(currWeek, Double.valueOf(e.text().split("[(]")[0].split("Under")[1]));
                            rushingAttemptsLines.remove(currWeek + 1);
                        } else if (e.text().contains("Rushing Yards")) {
                            rushingYardsLines.add(currWeek, Double.valueOf(e.text().split("[(]")[0].split("Under")[1]));
                            rushingYardsLines.remove(currWeek + 1);
                        } else if (e.text().contains("Completions")) {
                            completionsLines.add(currWeek, Double.valueOf(e.text().split("[(]")[0].split("Under")[1]));
                            completionsLines.remove(currWeek + 1);
                        } else if (e.text().contains("Pass Attempts")) {
                            passAttemptsLines.add(currWeek, Double.valueOf(e.text().split("[(]")[0].split("Under")[1]));
                            passAttemptsLines.remove(currWeek + 1);
                        } else if (e.text().contains("Passing Yards")) {
                            passYardsLines.add(currWeek, Double.valueOf(e.text().split("[(]")[0].split("Under")[1]));
                            passYardsLines.remove(currWeek + 1);
                        } else if (e.text().contains("Interceptions")) {
                            passIntsLines.add(currWeek, Double.valueOf(e.text().split("[(]")[0].split("Under")[1]));
                            passIntsLines.remove(currWeek + 1);
                        } else if (e.text().contains("TD Passes")) {
                            passTDLines.add(currWeek, Double.valueOf(e.text().split("[(]")[0].split("Under")[1]));
                            passTDLines.remove(currWeek + 1);
                        }
                    }
                } else throw new IllegalArgumentException("player not found");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            FileReader fileReader;
            if ( YEAR == 2021 ) fileReader = new FileReader(Utils.REC_LINES_2021);
            else fileReader = new FileReader(Utils.REC_LINES_2021);

            CSVReader csvReader = new CSVReader(fileReader);
            String[] nextRecord;
            // we are going to read data line by line
            while ( (nextRecord = csvReader.readNext()) != null ) {
                if ( nextRecord[0].equals(PLAYER_NAME) ) {
                    for ( int i = 1; i < nextRecord.length; i++ ) {
                        receptionsLines.add(i, Double.valueOf(nextRecord[i]));
                        receptionsLines.remove(i+1);
                    }
                }
            }
        }
    }
}
