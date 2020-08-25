package com.realitybrackets.beans;

public class BestPick {
    private String contestantKey;
    private Double pointDifferentialNextRound;
    private Double pointDifferentialFinalRound;

    public BestPick(String contestantKey) {
        this.contestantKey = contestantKey;
    }

    public String getContestantKey() {
        return contestantKey;
    }

    public void setContestantKey(String contestantKey) {
        this.contestantKey = contestantKey;
    }

    public Double getPointDifferentialNextRound() {
        return pointDifferentialNextRound;
    }

    public void setPointDifferentialNextRound(Double pointDifferentialNextRound) {
        this.pointDifferentialNextRound = pointDifferentialNextRound;
    }

    public Double getPointDifferentialFinalRound() {
        return pointDifferentialFinalRound;
    }

    public void setPointDifferentialFinalRound(Double pointDifferentialFinalRound) {
        this.pointDifferentialFinalRound = pointDifferentialFinalRound;
    }
}
