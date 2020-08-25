package com.realitybrackets.beans;

public class Score {
    private String teamKey;
    private String userKey;
    private Integer roundNumber;
    private Integer pickPosition;
    private Double score;
    private PickResult.Status status;

    public Score(String teamKey, String userKey, Integer roundNumber, Integer pickPosition, Double score, PickResult.Status status) {
        this.teamKey = teamKey;
        this.userKey = userKey;
        this.roundNumber = roundNumber;
        this.pickPosition = pickPosition;
        this.score = score;
        this.status = status;
    }

    public String getTeamKey() {
        return teamKey;
    }

    public void setTeamKey(String teamKey) {
        this.teamKey = teamKey;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Integer getPickPosition() {
        return pickPosition;
    }

    public void setPickPosition(Integer pickPosition) {
        this.pickPosition = pickPosition;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public PickResult.Status getStatus() {
        return status;
    }

    public void setStatus(PickResult.Status status) {
        this.status = status;
    }
}
