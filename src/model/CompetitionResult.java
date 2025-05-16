package model;

import java.io.Serializable;

public class CompetitionResult implements Serializable {

    public enum Competition {
        WORLD_SWIM_CUP,
        DANISH_SWIM_CUP
    }

    private int memberId;
    private Competition competition;
    private double time; // tid i sekunder
    private int ranking;

    // Constructor
    public CompetitionResult(int memberId, Competition competition, double time, int ranking) {
        this.memberId = memberId;
        this.competition = competition;
        this.time = time;
        this.ranking = ranking;
    }

    // Getters
    public int getMemberId() {
        return memberId;
    }

    public Competition getCompetition() {
        return competition;
    }

    public double getTime() {
        return time;
    }

    public int getRanking() {
        return ranking;
    }

    // Setters (hvis nødvendigt, ellers kan de udelades)
    // public void setTime(double time) { this.time = time; }
    // public void setRanking(int ranking) { this.ranking = ranking; }

    @Override
    public String toString() {
        int minutes = (int) time / 60;
        int seconds = (int) time % 60;
        String formattedTime = String.format("%02d:%02d", minutes, seconds);
        return "MedlemsID: " + memberId +
                " | Konkurrence: " + competition +
                " | Tid: " + formattedTime +
                " | Placering: " + ranking;
    }
}
