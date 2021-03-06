package com.realitybrackets.beans;

public class Chart {
    private String contestantKey;
    private Integer roundNumber;
    private Integer position;
    private String status;

    public Chart(String contestantKey, Integer roundNumber, Integer position, String status) {
        this.contestantKey = contestantKey;
        this.roundNumber = roundNumber;
        this.position = position;
        this.status = status;
    }

    public String getContestantKey() {
        return contestantKey;
    }

    public void setContestantKey(String contestantKey) {
        this.contestantKey = contestantKey;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
