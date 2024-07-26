package org.nfl.data;

public class QBSeasonStats extends PlayerSeasonStats{

    private String record;
    private int completions;
    private int attempts;
    private String completionPct;
    private int yards;
    private int touchdowns;
    private String touchdownPct;
    private int interceptions;
    private String interceptionPct;
    private int firstDowns;
    private String successPct;
    private int longest;
    private double ypa;
    private double aypa;
    private double ypc;
    private double ypg;
    private double rate;
    private double qbr;
    private int sacks;
    private int sackYards;
    private String sackPct;
    private double nypa;
    private double anypa;
    private int comebacks;
    private int gwd;

    @Override
    public int getCompletions() {
        return completions;
    }

    @Override
    public void setCompletions(int completions) {
        this.completions = completions;
    }

    @Override
    public int getAttempts() {
        return attempts;
    }

    @Override
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    @Override
    public String getCompletionPct() {
        return completionPct;
    }

    @Override
    public void setCompletionPct(String completionPct) {
        this.completionPct = completionPct;
    }

    @Override
    public int getYards() {
        return yards;
    }

    @Override
    public void setYards(int yards) {
        this.yards = yards;
    }

    @Override
    public int getTouchdowns() {
        return touchdowns;
    }

    @Override
    public void setTouchdowns(int touchdowns) {
        this.touchdowns = touchdowns;
    }

    @Override
    public String getTouchdownPct() {
        return touchdownPct;
    }

    @Override
    public void setTouchdownPct(String touchdownPct) {
        this.touchdownPct = touchdownPct;
    }

    @Override
    public int getInterceptions() {
        return interceptions;
    }

    @Override
    public void setInterceptions(int interceptions) {
        this.interceptions = interceptions;
    }

    @Override
    public String getInterceptionPct() {
        return interceptionPct;
    }

    @Override
    public void setInterceptionPct(String interceptionPct) {
        this.interceptionPct = interceptionPct;
    }

    @Override
    public int getFirstDowns() {
        return firstDowns;
    }

    @Override
    public void setFirstDowns(int firstDowns) {
        this.firstDowns = firstDowns;
    }

    @Override
    public String getSuccessPct() {
        return successPct;
    }

    @Override
    public void setSuccessPct(String successPct) {
        this.successPct = successPct;
    }

    @Override
    public int getLongest() {
        return longest;
    }

    @Override
    public void setLongest(int longest) {
        this.longest = longest;
    }

    @Override
    public double getYpa() {
        return ypa;
    }

    @Override
    public void setYpa(double ypa) {
        this.ypa = ypa;
    }

    @Override
    public double getAypa() {
        return aypa;
    }

    @Override
    public void setAypa(double aypa) {
        this.aypa = aypa;
    }

    @Override
    public double getYpc() {
        return ypc;
    }

    @Override
    public void setYpc(double ypc) {
        this.ypc = ypc;
    }

    @Override
    public double getYpg() {
        return ypg;
    }

    @Override
    public void setYpg(double ypg) {
        this.ypg = ypg;
    }

    @Override
    public double getRate() {
        return rate;
    }

    @Override
    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public double getQbr() {
        return qbr;
    }

    @Override
    public void setQbr(double qbr) {
        this.qbr = qbr;
    }

    @Override
    public int getSacks() {
        return sacks;
    }

    @Override
    public void setSacks(int sacks) {
        this.sacks = sacks;
    }

    @Override
    public int getSackYards() {
        return sackYards;
    }

    @Override
    public void setSackYards(int sackYards) {
        this.sackYards = sackYards;
    }

    @Override
    public String getSackPct() {
        return sackPct;
    }

    @Override
    public void setSackPct(String sackPct) {
        this.sackPct = sackPct;
    }

    @Override
    public double getNypa() {
        return nypa;
    }

    @Override
    public void setNypa(double nypa) {
        this.nypa = nypa;
    }

    @Override
    public double getAnypa() {
        return anypa;
    }

    @Override
    public void setAnypa(double anypa) {
        this.anypa = anypa;
    }

    @Override
    public int getComebacks() {
        return comebacks;
    }

    @Override
    public void setComebacks(int comebacks) {
        this.comebacks = comebacks;
    }

    @Override
    public int getGwd() {
        return gwd;
    }

    @Override
    public void setGwd(int gwd) {
        this.gwd = gwd;
    }

    public QBSeasonStats(){
        super();
    }

}
