import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SwimmerDatabase {

    private MemberDatabase memberDatabase;
    private List<SwimmerResult> swimmers = new ArrayList<>(); // new list for swimmers

    public SwimmerDatabase(MemberDatabase memberDatabase) {
        this.memberDatabase = memberDatabase;
    }

    // Method that formats date+time and adds new member to swimmer list
    public void addSwimmer(int memberId, SwimmerResult.Discipline discipline, String timeString, String dateString) {

        Member medlem = findSwimmerById(memberId);
        if (medlem != null) {

            // Converts time to total seconds
            double seconds;
            try {
                String[] parts = timeString.split(":");
                int minutes = Integer.parseInt(parts[0]);
                int secs = Integer.parseInt(parts[1]);
                seconds = minutes * 60 + secs;
            } catch (Exception e) {
                System.out.println("Ugyldigt tidsformat. Brug mm:ss.");
                return;
            }

            // Convert date to localdate
            LocalDate date;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                date = LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldigt datoformat. Brug dd-MM-yyyy.");
                return;
            }

            // Adds new swimmer object to the list
            SwimmerResult tr = new SwimmerResult(discipline, seconds, date, memberId);
            swimmers.add(tr);
        } else {
            System.out.println("Medlem med ID " + memberId + " findes ikke som aktiv konkurrencesvømmer.");
        }
    }

    // Finds valid member who is active and has activity type "konkurrence"
    public Member findSwimmerById(int memberId) {
        for (Member m : memberDatabase.getAllMembers()) {
            if (m.getMemberId() == memberId && m.isActive() && m.getActivityType() == Member.ActivityType.KONKURRENCE) {
                return m;
            }
        }
        return null;
    }

    // Shows a list of all swimmers with a for loop and converts date+time to a different format
    public void showSwimmerList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (SwimmerResult swimmerResults : swimmers) {
            Member member = findSwimmerById(swimmerResults.getMemberId());
            int minutes = (int) swimmerResults.getTime() / 60;
            int seconds = (int) swimmerResults.getTime() % 60;
            String timeFormatted = String.format("%02d:%02d", minutes, seconds);

            if (member != null) {
                System.out.println("Navn: " + member.getName() +
                        " | Alder: " + member.getAge() +
                        " | Tlf: " + member.getPhoneNumber() +
                        " | Aktiv: " + (member.isActive() ? "Ja" : "Nej") +
                        " | Medlemstype: " + member.getMembershipType() +
                        " | Aktivitetsform: " + member.getActivityType());
            }

            System.out.println("  -> Disciplin: " + swimmerResults.getDiscipline() +
                    " | Tid: " + timeFormatted +
                    " | Dato: " + swimmerResults.getDate().format(formatter));
            System.out.println();
        }
    }

    // Methods that filters/sort the list for discipline and age groups
    public void sortTop5Swimmers(SwimmerResult.Discipline discipline, Member.MembershipType groupType, DateTimeFormatter formatter) {
        System.out.println("  [" + groupType + "]");
        List<SwimmerResult> filtered = new ArrayList<>();

        for (SwimmerResult tr : swimmers) {
            if (tr.getDiscipline() == discipline) {
                Member m = findSwimmerById(tr.getMemberId());
                if (m != null && m.getMembershipType() == groupType) {
                    filtered.add(tr);
                }
            }
        }

        // Sort by fastest time with comparator method
        filtered.sort(Comparator.comparingDouble(SwimmerResult::getTime));

        // Prints top 5 swimmers
        for (int i = 0; i < Math.min(5, filtered.size()); i++) {
            SwimmerResult tr = filtered.get(i);
            Member member = findSwimmerById(tr.getMemberId());
            if (member == null) continue;

            int minutes = (int) tr.getTime() / 60;
            int seconds = (int) tr.getTime() % 60;
            String timeFormatted = String.format("%02d:%02d", minutes, seconds);

            System.out.println("    Navn: " + member.getName() +
                    " | Alder: " + member.getAge() +
                    " | Tid: " + timeFormatted +
                    " | Dato: " + tr.getDate().format(formatter));
        }
    }
}
