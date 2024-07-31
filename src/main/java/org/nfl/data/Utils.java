package org.nfl.data;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    public final static Map<String,String> STAT_TO_ID = new HashMap<>();
    public final static Map<String,String> TEAM_TO_LOCATOR = new HashMap<>();
    public static Map<String,String> ABBR_TO_TEAM = new HashMap<>();
    public final static String RECEIVING23 = System.getProperty("user.dir") + "\\database\\statistics\\Receiving2023.csv";
    public final static String RECEIVING22 = System.getProperty("user.dir") + "\\database\\statistics\\Receiving2022.csv";
    public final static String RECEIVING21 = System.getProperty("user.dir") + "\\database\\statistics\\Receiving2021.csv";

    public final static String PASSING23 = System.getProperty("user.dir") + "\\database\\statistics\\Passing2023.csv";
    public final static String PASSING22 = System.getProperty("user.dir") + "\\database\\statistics\\Passing2022.csv";
    public final static String PASSING21 = System.getProperty("user.dir") + "\\database\\statistics\\Passing2021.csv";

    public final static String RUSHING23 = System.getProperty("user.dir") + "\\database\\statistics\\Rushing2023.csv";
    public final static String RUSHING22 = System.getProperty("user.dir") + "\\database\\statistics\\Rushing2022.csv";
    public final static String RUSHING21 = System.getProperty("user.dir") + "\\database\\statistics\\Rushing2021.csv";

    public final static String DEFENSE23 = System.getProperty("user.dir") + "\\database\\statistics\\Defense2023.csv";
    public final static String DEFENSE22 = System.getProperty("user.dir") + "\\database\\statistics\\Defense2022.csv";
    public final static String DEFENSE21 = System.getProperty("user.dir") + "\\database\\statistics\\Defense2021.csv";
    public final static String WEATHER23 = System.getProperty("user.dir") + "\\database\\weather\\gameWeather.csv";
    public final static String VSWR22 = System.getProperty("user.dir") + "\\database\\statistics\\VsWRs2022.csv";
    public final static String VSWR21 = System.getProperty("user.dir") + "\\database\\statistics\\VsWRs2021.csv";

    public final static String DOMED_TEAMS = "cardinals, falcons, cowboys, lions, texans, colts, raiders, chargers, rams, vikings, saints";

    public Utils() {
        STAT_TO_ID.put("targets", "targets");
        STAT_TO_ID.put("receptions", "rec");
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
        STAT_TO_ID.put("opponent", "opp");
        STAT_TO_ID.put("week", "week_num");
        STAT_TO_ID.put("age", "age");
        STAT_TO_ID.put("location", "game_location");
        STAT_TO_ID.put("date", "game_date");
        STAT_TO_ID.put("team", "team");
        STAT_TO_ID.put("reason", "reason");

        TEAM_TO_LOCATOR.put("commanders", "was");
        TEAM_TO_LOCATOR.put("eagles", "phi");
        TEAM_TO_LOCATOR.put("lions", "det");
        TEAM_TO_LOCATOR.put("chargers", "sdg");
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

        ABBR_TO_TEAM.put("ari", "cardinals");
        ABBR_TO_TEAM.put("atl", "falcons");
        ABBR_TO_TEAM.put("bal", "ravens");
        ABBR_TO_TEAM.put("buf", "bills");
        ABBR_TO_TEAM.put("car", "panthers");
        ABBR_TO_TEAM.put("chi", "bears");
        ABBR_TO_TEAM.put("cin", "bengals");
        ABBR_TO_TEAM.put("cle", "browns");
        ABBR_TO_TEAM.put("dal", "cowboys");
        ABBR_TO_TEAM.put("den", "broncos");
        ABBR_TO_TEAM.put("det", "lions");
        ABBR_TO_TEAM.put("gnb", "packers");
        ABBR_TO_TEAM.put("htx", "texans");
        ABBR_TO_TEAM.put("hou", "texans");
        ABBR_TO_TEAM.put("ind", "colts");
        ABBR_TO_TEAM.put("jax", "jaguars");
        ABBR_TO_TEAM.put("kan", "chiefs");
        ABBR_TO_TEAM.put("lvr", "raiders");
        ABBR_TO_TEAM.put("lac", "chargers");
        ABBR_TO_TEAM.put("lar", "rams");
        ABBR_TO_TEAM.put("mia", "dolphins");
        ABBR_TO_TEAM.put("min", "vikings");
        ABBR_TO_TEAM.put("nwe", "patriots");
        ABBR_TO_TEAM.put("nor", "saints");
        ABBR_TO_TEAM.put("nyg", "giants");
        ABBR_TO_TEAM.put("nyj", "jets");
        ABBR_TO_TEAM.put("phi", "eagles");
        ABBR_TO_TEAM.put("pit", "steelers");
        ABBR_TO_TEAM.put("sfo", "49ers");
        ABBR_TO_TEAM.put("sea", "seahawks");
        ABBR_TO_TEAM.put("tam", "buccaneers");
        ABBR_TO_TEAM.put("ten", "titans");
        ABBR_TO_TEAM.put("was", "commanders");

    }
}
