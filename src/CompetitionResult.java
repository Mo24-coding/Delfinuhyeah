public class CompetitionResult {

    public enum Competition {
        WORLD_SWIM_CUP, DANISH_SWIM_CUP
    }

    private int memberId;
    private Competition competition;
    private SwimmerResult.Discipline discipline;
    private double time;
    private int ranking;

    // Constructor
    public CompetitionResult(int memberId, Competition competition, SwimmerResult.Discipline discipline, double time, int ranking){
        this.memberId = memberId;
        this.competition = competition;
        this.discipline = discipline;
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

    public SwimmerResult.Discipline discipline () {
        return discipline;
    }

    public double getTime () {
        return time;
    }

    public int getRanking () {
        return ranking;
    }

}
