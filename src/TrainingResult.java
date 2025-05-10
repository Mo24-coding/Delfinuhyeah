import java.time.LocalDate;

public class TrainingResult { // Class with all attributes etc needed for SwimmerDatabase class

    public enum Discipline {
        CRAWL, BUTTERFLY, BRYSTSVØMNING, RYGCRAWL
    }

    private Discipline discipline;
    private double time;
    private LocalDate date;
    private int memberId;

    public TrainingResult(Discipline discipline, double time, LocalDate date, int memberId) {
        this.discipline = discipline;
        this.time = time;
        this.date = date;
        this.memberId = memberId;
    }

    // Getters
    public Discipline getDiscipline() {
        return discipline; }

    public double getTime() {
        return time; }

    public LocalDate getDate() {
        return date; }

    public int getMemberId() {
        return memberId; }
}