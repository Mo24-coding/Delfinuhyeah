package model;

public class CompetitionResult { // Class with all attributes etc needed for data.CompetitionDatabase class

    public enum Competition {
        WORLD_SWIM_CUP, DANISH_SWIM_CUP
    }

    private int memberId;
    private Competition competition;
    private double time;
    private int ranking;

    // Constructor
    public CompetitionResult(int memberId, Competition competition, double time, int ranking){
        this.memberId = memberId;
        this.competition = competition;
        this.time = time;
        this.ranking = ranking;
    }

    // Getters
    public int getMemberId (){
        return memberId;
    }

    public Competition getCompetition () {
        return competition;
    }


    public double getTime () {
        return time;
    }

    public int getRanking () {
        return ranking;
    }

}
