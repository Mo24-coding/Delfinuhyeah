import java.util.Scanner; // Importerer Scanner-klassen, så vi kan læse input fra brugeren

public class Club {

    // Hovedmetoden
    public static void main(String[] args) {

        // Opretter et Scanner-objekt
        Scanner scanner = new Scanner(System.in);


        MemberDatabase memberDb = new MemberDatabase();
        SwimmerDatabase swimmerDb = new SwimmerDatabase(memberDb);
        CompetitionDatabase competitionDB = new CompetitionDatabase(swimmerDb);
        //swimmerDb.populateTestSwimmers();     // TEST CODE - IKKE SLET
        Menu menu = new Menu(memberDb, swimmerDb, competitionDB);
        menu.showMenu(scanner);
    }
}