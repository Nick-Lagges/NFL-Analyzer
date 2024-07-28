package org.nfl.data;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PlayerGame {

    public Integer WEEK;
    public String OPPONENT;
    public int TAR;
    public int REC;
    public int REC_YD;
    public int REC_TD;
    public int RUSH_ATT;
    public int RUSH_YD;
    public int RUSH_TD;
    public int PASS_TD;
    public int PASS_ATT;
    public int PASS_COMP;
    public int PASS_YD;
    public int PASS_INT;
    public String FIELD;
    public double OFF_SNAP;

    private String homeTeam;
    private String date;

    private double temperature;
    private double dewPoint;
    private double humidity;
    private double heatIndex;
    private double windSpeed;
    private double windGust;
    private double windDirection;
    private double windChill;
    private double precipitation;
    private double snowDepth;
    private double visibility;
    private double cloudCover;
    private String weatherType;
    private String conditions;

    private double feelsLike;

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike() {
        if ( this.getHeatIndex() != 0.0 ) this.feelsLike = this.getHeatIndex();
        else if ( this.getWindChill() != 0.0 ) this.feelsLike = this.getWindChill();
        else this.feelsLike = getTemperature();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        date = date.replace("-", "/");
        this.date = date.substring(5);
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getHeatIndex() {
        return heatIndex;
    }

    public void setHeatIndex(double heatIndex) {
        this.heatIndex = heatIndex;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindGust() {
        return windGust;
    }

    public void setWindGust(double windGust) {
        this.windGust = windGust;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public double getWindChill() {
        return windChill;
    }

    public void setWindChill(double windChill) {
        this.windChill = windChill;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getSnowDepth() {
        return snowDepth;
    }

    public void setSnowDepth(double snowDepth) {
        this.snowDepth = snowDepth;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public PlayerGame() {
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getHomeORAway() {
        return FIELD;
    }

    public void setHomeOrAway(String FIELD) {
        this.FIELD = FIELD;
    }

    public Integer getWEEK() {
        return WEEK;
    }

    public void setWEEK(int WEEK) {
        this.WEEK = WEEK;
    }

    public String getOPPONENT() {
        return OPPONENT;
    }

    public void setOPPONENT(String OPPONENT) {
        this.OPPONENT = OPPONENT.toLowerCase();
    }

    public int getTAR() {
        return TAR;
    }

    public void setTAR(int TAR) {
        this.TAR = TAR;
    }

    public int getREC() {
        return REC;
    }

    public void setREC(int REC) {
        this.REC = REC;
    }

    public int getREC_YD() {
        return REC_YD;
    }

    public void setREC_YD(int REC_YD) {
        this.REC_YD = REC_YD;
    }

    public int getREC_TD() {
        return REC_TD;
    }

    public void setREC_TD(int REC_TD) {
        this.REC_TD = REC_TD;
    }

    public int getRUSH_ATT() {
        return RUSH_ATT;
    }

    public void setRUSH_ATT(int RUSH_ATT) {
        this.RUSH_ATT = RUSH_ATT;
    }

    public int getRUSH_YD() {
        return RUSH_YD;
    }

    public void setRUSH_YD(int RUSH_YD) {
        this.RUSH_YD = RUSH_YD;
    }

    public int getRUSH_TD() {
        return RUSH_TD;
    }

    public void setRUSH_TD(int RUSH_TD) {
        this.RUSH_TD = RUSH_TD;
    }

    public int getPASS_TD() {
        return PASS_TD;
    }

    public void setPASS_TD(int PASS_TD) {
        this.PASS_TD = PASS_TD;
    }

    public int getPASS_ATT() {
        return PASS_ATT;
    }

    public void setPASS_ATT(int PASS_ATT) {
        this.PASS_ATT = PASS_ATT;
    }

    public int getPASS_COMP() {
        return PASS_COMP;
    }

    public void setPASS_COMP(int PASS_COMP) {
        this.PASS_COMP = PASS_COMP;
    }

    public int getPASS_YD() {
        return PASS_YD;
    }

    public void setPASS_YD(int PASS_YD) {
        this.PASS_YD = PASS_YD;
    }

    public int getPASS_INT() {
        return PASS_INT;
    }

    public void setPASS_INT(int PASS_INT) {
        this.PASS_INT = PASS_INT;
    }

    public double getOFF_SNAP() {
        return OFF_SNAP;
    }

    public void setOFF_SNAP(String OFF_SNAP) {
        this.OFF_SNAP = Double.valueOf(OFF_SNAP.substring(0,OFF_SNAP.length()-1));
    }

    public void generateWeather() throws IOException {
        if ( Utils.DOMED_TEAMS.contains(this.getHomeTeam()) ){
            this.setTemperature(70.0);
            this.setDewPoint(0.0);
            this.setHumidity(50.0);
            this.setHeatIndex(70.0);
            this.setWindSpeed(0.0);
            this.setWindGust(0.0);
            this.setWindDirection(0.0);
            this.setWindChill(70.0);
            this.setPrecipitation(0.0);
            this.setSnowDepth(0.0);
            this.setVisibility(10.0);
            this.setCloudCover(0.0);
            this.setWeatherType("");
            this.setConditions("Clear");
            this.setFeelsLike();
        }
        else {
            FileReader fileReader = new FileReader(Utils.WEATHER23);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] nextRecord;
            // we are going to read data line by line
            while ( (nextRecord = csvReader.readNext()) != null ){
                if ( nextRecord[0].contains(this.getHomeTeam()) && nextRecord[1].contains(this.getDate()) ) {
                    this.setTemperature(Double.valueOf(nextRecord[2]));
                    this.setDewPoint(Double.valueOf(nextRecord[3]));
                    this.setHumidity(Double.valueOf(nextRecord[4]));
                    if ( nextRecord[5].isEmpty() ) this.setHeatIndex(0.0);
                    else this.setHeatIndex(Double.valueOf(nextRecord[5]));
                    this.setWindSpeed(Double.valueOf(nextRecord[6]));
                    if ( nextRecord[7].isEmpty() ) this.setWindGust(0.0);
                    else this.setWindGust(Double.valueOf(nextRecord[7]));
                    this.setWindDirection(Double.valueOf(nextRecord[8]));
                    if ( nextRecord[9].isEmpty() ) this.setWindChill(this.getTemperature());
                    else this.setWindChill(Double.valueOf(nextRecord[9]));
                    this.setPrecipitation(Double.valueOf(nextRecord[10]));
                    if ( nextRecord[11].isEmpty() ) this.setSnowDepth(0.0);
                    else this.setSnowDepth(Double.valueOf(nextRecord[11]));
                    this.setVisibility(Double.valueOf(nextRecord[12]));
                    this.setCloudCover(Double.valueOf(nextRecord[13]));
                    if ( nextRecord[14].isEmpty() ) this.setWeatherType("none");
                    else this.setWeatherType(nextRecord[14]);
                    this.setConditions(nextRecord[15]);
                    this.setFeelsLike();
                    break;
                }
            }
        }

    }
}
