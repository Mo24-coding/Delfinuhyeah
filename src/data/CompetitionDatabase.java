package data;

import model.CompetitionResult;
import model.Member;
import utility.InvalidTimeFormatException;
import utility.SwimmerNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CompetitionDatabase {

    private List<CompetitionResult> competitors = new ArrayList<>();
    private SwimmerDatabase swimmerDatabase;

    public CompetitionDatabase(SwimmerDatabase swimmerDatabase) {
        this.swimmerDatabase = swimmerDatabase;
    }

    // Tilføj konkurrenceresultat til listen
    public void addCompetitionToList(int memberId, CompetitionResult.Competition competition, String timeString, int ranking) {
        Member member = swimmerDatabase.findSwimmerById(memberId);
        if (member == null) {
            throw new SwimmerNotFoundException(memberId);
        }
        double seconds;
        try {
            String[] parts = timeString.split(":");
            int minutes = Integer.parseInt(parts[0]);
            int secs = Integer.parseInt(parts[1]);
            seconds = minutes * 60 + secs;
        } catch (Exception e) {
            throw new InvalidTimeFormatException(timeString);
        }
        competitors.add(new CompetitionResult(memberId, competition, seconds, ranking));
        System.out.println("Konkurrence tilføjet for " + member.getName());
    }

    // Vis alle konkurrenceresultater
    public void showAllCompetitors() {
        for (CompetitionResult c : competitors) {
            Member m = swimmerDatabase.findSwimmerById(c.getMemberId());
            if (m == null) continue;
            int minutes = (int) c.getTime() / 60;
            int seconds = (int) c.getTime() % 60;
            String formatted = String.format("%02d:%02d", minutes, seconds);
            System.out.println("Navn: " + m.getName() +
                    " | Konkurrence: " + c.getCompetition() +
                    " | Tid: " + formatted +
                    " | Placering: " + c.getRanking());
        }
    }

    // Returnér alle konkurrenceresultater
    public List<CompetitionResult> getAllCompetitionResults() {
        return competitors;
    }
}
