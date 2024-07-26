package org.nfl.data;

public class WRSeasonStats extends PlayerSeasonStats{
    public int targets;
    public int receptions;
    public String catchPct;
    public int yards;
    public double ypr;
    public int touchdowns;
    public int firstDowns;

    @Override
    public int getTargets() {
        return targets;
    }

    public void setTargets(int targets) {
        this.targets = targets;
    }

    @Override
    public int getReceptions() {
        return receptions;
    }

    public void setReceptions(int receptions) {
        this.receptions = receptions;
    }

    @Override
    public String getCatchPct() {
        return catchPct;
    }

    @Override
    public void setCatchPct(String catchPct) {
        this.catchPct = catchPct;
    }

    @Override
    public int getYards() {
        return yards;
    }

    public void setYards(int yards) {
        this.yards = yards;
    }

    @Override
    public double getYpr() {
        return ypr;
    }

    public void setYpr(double ypr) {
        this.ypr = ypr;
    }

    @Override
    public int getTouchdowns() {
        return touchdowns;
    }

    public void setTouchdowns(int touchdowns) {
        this.touchdowns = touchdowns;
    }

    @Override
    public int getFirstDowns() {
        return firstDowns;
    }

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

    public void setLongest(int longest) {
        this.longest = longest;
    }

    @Override
    public double getYpt() {
        return ypt;
    }

    public void setYpt(double ypt) {
        this.ypt = ypt;
    }

    @Override
    public double getRpg() {
        return rpg;
    }

    public void setRpg(double rpg) {
        this.rpg = rpg;
    }

    @Override
    public double getYpg() {
        return ypg;
    }

    public void setYpg(double ypg) {
        this.ypg = ypg;
    }

    @Override
    public int getFumbles() {
        return fumbles;
    }

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

    public String successPct;
    public int longest;
    public double ypt;
    public double rpg;
    public double ypg;
    public int fumbles;
    public String id;

    public WRSeasonStats(){
        super();
    }

}
