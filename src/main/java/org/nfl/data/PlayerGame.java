package org.nfl.data;

public class PlayerGame {

    public int WEEK;
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

    public String getHomeORAway() {
        return FIELD;
    }

    public void setHomeOrAway(String FIELD) {
        this.FIELD = FIELD;
    }

    public String FIELD;
    public double OFF_SNAP;

    public PlayerGame() {
    }

    public int getWEEK() {
        return WEEK;
    }

    public void setWEEK(int WEEK) {
        this.WEEK = WEEK;
    }

    public String getOPPONENT() {
        return OPPONENT;
    }

    public void setOPPONENT(String OPPONENT) {
        this.OPPONENT = OPPONENT;
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

    public void setOFF_SNAP(double OFF_SNAP) {
        this.OFF_SNAP = OFF_SNAP;
    }
}
