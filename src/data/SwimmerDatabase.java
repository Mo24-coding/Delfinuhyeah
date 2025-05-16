package data;
import model.Member;
import model.SwimmerResult;
import utility.InvalidTimeFormatException;
import utility.SwimmerNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static utility.Colors.*;

public class SwimmerDatabase {

    private MemberDatabase memberDatabase;
    private List<SwimmerResult> swimmers = new ArrayList<>(); // new list for swimmers

    public SwimmerDatabase(MemberDatabase memberDatabase) {
        this.memberDatabase = memberDatabase;
    }

    //Method that takes input from createSwimmers() method and adds objects to swimmers[] list
    public void addSwimmer(int memberId, SwimmerResult.Discipline discipline, String timeString, String dateString) {

        // Finds swimmer by ID
        Member medlem = findSwimmerById(memberId);
        if (medlem != null) {

            // Converts time string from mm:ss to total seconds (double) for sorting and calculations
            double seconds;
            try {
                String[] parts = timeString.split(":");
                int minutes = Integer.parseInt(parts[0]);
                int secs = Integer.parseInt(parts[1]);
                seconds = minutes * 60 + secs;
            } catch (Exception e) {
                throw new InvalidTimeFormatException(timeString); // Custom Exception for wrong time format
            }

            // Convert date string to localdate
            LocalDate date;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                date = LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldigt datoformat. Brug dd-MM-yyyy.");
                return;
            }

            // Adds new swimmer object to the swimmers[] list
            SwimmerResult tr = new SwimmerResult(discipline, seconds, date, memberId);
            swimmers.add(tr);
        } else {
            throw new SwimmerNotFoundException(memberId); // Custom Exception for non "aktiv konkurrencesvømmer"
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

    // Shows a list of all swimmers with a for loop and converts date+time to a string format
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

    // Method that sorts and prints a top 5 list for every discipline in both age groups as a table format
    public void top5ListForSvimmwers() {
        // Loop that goes through all disciplines
        for (SwimmerResult.Discipline discipline : SwimmerResult.Discipline.values()) {

            List<SwimmerResult> seniorList = new ArrayList<>();
            List<SwimmerResult> juniorList = new ArrayList<>();

            // Filters competitors by discipline and age groups then adds to senior[] or junior[] lists
            for (SwimmerResult result : swimmers) {
                if (result.getDiscipline() == discipline) {
                    Member m = findSwimmerById(result.getMemberId());
                    if (m != null) {
                        if (m.getMembershipType() == Member.MembershipType.SENIOR) {
                            seniorList.add(result);
                        } else if (m.getMembershipType() == Member.MembershipType.JUNIOR) {
                            juniorList.add(result);
                        }
                    }
                }
            }

            // Sorts by fastest time with comparator
            seniorList.sort(Comparator.comparingDouble(SwimmerResult::getTime));
            juniorList.sort(Comparator.comparingDouble(SwimmerResult::getTime));

            // Titles
            System.out.printf("\n%-35s | %-35s\n", blue("Top 5 SENIOR (" + discipline + ")"),
                    blue("Top 5 JUNIOR (" + discipline + ")"));

            // Limits to print 5 members in each category
            int seniorSize = Math.min(seniorList.size(), 5);
            int juniorSize = Math.min(juniorList.size(), 5);
            int rows = Math.max(seniorSize, juniorSize);

            if (rows == 0) {
                System.out.println(red("Mangler Data..."));
                continue;
            }

            // Loop that prints top 5 in every category
            for (int i = 0; i < rows; i++) {
                String seniorEntry = "", juniorEntry = "";

                if (i < seniorSize) {
                    SwimmerResult sr = seniorList.get(i);
                    Member m = findSwimmerById(sr.getMemberId());
                    String name = String.format("%s (ID:%d)", m.getName(), m.getMemberId());
                    String time = green(String.format("%02d:%02d", (int)(sr.getTime() / 60), (int)(sr.getTime() % 60)));
                    seniorEntry = String.format("%s %-22s | %-7s", yellow("#" + (i + 1)), name, time);
                }

                if (i < juniorSize) {
                    SwimmerResult jr = juniorList.get(i);
                    Member m = findSwimmerById(jr.getMemberId());
                    String name = String.format("%s (ID:%d)", m.getName(), m.getMemberId());
                    String time = green(String.format("%02d:%02d", (int)(jr.getTime() / 60), (int)(jr.getTime() % 60)));
                    juniorEntry = String.format("%s %-22s | %-7s", yellow("#" + (i + 1)), name, time);
                }

                System.out.printf("%-35s | %-35s\n", seniorEntry, juniorEntry);
            }
        }
    }

    // TEST CODE - IKKE SLET

    public void populateTestSwimmers() {
        String[] juniorNames = {"Adam", "Emma", "Clara"};
        String[] seniorNames = {"Jonas", "Anna", "Mads"};
        int idCounter = 1;

        for (SwimmerResult.Discipline discipline : SwimmerResult.Discipline.values()) {
            for (String name : juniorNames) {
                Member m = new Member(name, 14, "12345678", true, Member.MembershipType.SENIOR, Member.ActivityType.KONKURRENCE);
                memberDatabase.getAllMembers().add(m); // direct add
                swimmers.add(new SwimmerResult(discipline, 150 + idCounter, LocalDate.now(), idCounter));
                idCounter++;
            }
            for (String name : seniorNames) {
                Member m = new Member(name, 14, "12345678", true, Member.MembershipType.JUNIOR, Member.ActivityType.KONKURRENCE);
                memberDatabase.getAllMembers().add(m);
                swimmers.add(new SwimmerResult(discipline, 120 + idCounter, LocalDate.now(), idCounter));
                idCounter++;
            }
        }
    }


}