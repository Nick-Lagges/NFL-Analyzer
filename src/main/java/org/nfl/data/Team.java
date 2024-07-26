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

    public Team(String name, Integer year) throws IOException, InterruptedException {
        NAME = name;
        YEAR = year;
    }

}
