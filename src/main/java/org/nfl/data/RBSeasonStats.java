package org.nfl.data;

public class RBSeasonStats extends PlayerSeasonStats{

    @Override
    public int getAttempts() {
        return attempts;
    }

    @Override
    public void setAttempts(int attempts) {
        this.attempts = attempts;
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
    public double getYpg() {
        return ypg;
    }

    @Override
    public void setYpg(double ypg) {
        this.ypg = ypg;
    }

    @Override
    public int getFumbles() {
        return fumbles;
    }

    @Override
    public void setFumbles(int fumbles) {
        this.fumbles = fumbles;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    private int attempts;
    private int yards;
    private int touchdowns;
    private int firstDowns;
    private String successPct;
    private int longest;
    private double ypa;
    private double ypg;
    private int fumbles;
    private String id;
    public RBSeasonStats(){
        super();
    }

}
