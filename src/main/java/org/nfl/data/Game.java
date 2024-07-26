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

    public Game(String address) {
        URL = URI + address;
    }
}
