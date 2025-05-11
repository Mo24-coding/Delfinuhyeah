import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    private final MemberDatabase database;
    private final SwimmerDatabase swimmerDb;

    public Menu(MemberDatabase database, SwimmerDatabase swimmerDb) {
        this.database = database;
        this.swimmerDb = swimmerDb;
    }

    public void visMenu(Scanner scanner) {

        while (true) {
            System.out.println("\n--- Svømmeklubben Delfinen ---");
            System.out.println("1. Opret medlem");
            System.out.println("2. Rediger medlem");
            System.out.println("3. Slet medlem");
            System.out.println("4. Vis alle medlemmer");
            System.out.println("5. Opret svømmer");
            System.out.println("6. Vis alle svømmere");
            System.out.println("7. Vis top 5 svømmere");
            System.out.println("8. Opret konkurrencesvømmer");
            System.out.println("9.  ......kontingent1");
            System.out.println("10. 🔴 Afslut program");
            System.out.print("Vælg en mulighed: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine()); //Reads input as String and parse (change) to int

            switch (choice) {
                case 1:
                    createMember(scanner);
                    returnToMenu(scanner);
                    break;
                case 2:
                    editMember(scanner);
                    returnToMenu(scanner);
                    break;
                case 3:
                    deleteMember(scanner);
                    returnToMenu(scanner);
                    break;
                case 4:
                    listMembers();
                    returnToMenu(scanner);
                    break;
                case 5:
                    System.out.println("Viser liste med medlemmer...");
                    listMembers();
                    createSwimmers(scanner);
                    returnToMenu(scanner);
                    break;
                case 6:
                    swimmerDb.showSwimmerList();
                    returnToMenu(scanner);
                    break;
                case 7:
                    showTop5Svimmers(scanner);
                    returnToMenu(scanner);
                    break;
                case 8:
                    System.out.println("Opret konkurrencesvømmer");
                    System.out.println("mangler");
                    returnToMenu(scanner);
                    break;
                case 9:
                    System.out.println("......kontingent");
                    System.out.println("mangler");
                    returnToMenu(scanner);
                    break;
                case 10:
                    System.out.println("\n🔴 Afslutter programmet...");
                    return;
                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
                }
            } catch (NumberFormatException e) { //Catches anything other than int inputs
                System.out.println("\n⚠ Fejl: Indtast et gyldigt tal mellem 1 og 10");
            }
        }
    }


    private void createMember(Scanner scanner) {
        System.out.print("Navn: ");
        String name = scanner.nextLine();
        System.out.print("Alder: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Kontaktinfo: ");
        String contact = scanner.nextLine();
        System.out.print("Aktiv? (ja/nej): ");
        boolean isActive = scanner.nextLine().equalsIgnoreCase("ja");

        Member.MembershipType membershipType = age < 18 ? Member.MembershipType.JUNIOR : Member.MembershipType.SENIOR;

        System.out.print("Aktivitetsform (motionist/konkurrence): ");
        String activityInput = scanner.nextLine().toLowerCase();
        Member.ActivityType activityType = activityInput.equals("konkurrence") ? Member.ActivityType.KONKURRENCE : Member.ActivityType.MOTIONIST;

        Member member = new Member(name, age, contact, isActive, membershipType, activityType);
        database.addMember(member);
        System.out.println("Medlem oprettet.");
    }

    private void editMember(Scanner scanner) {
        System.out.print("Indtast navnet på det medlem, der skal redigeres: ");
        String name = scanner.nextLine();
        Member existing = database.findMemberByName(name);

        if (existing == null) {
            System.out.println("Medlem ikke fundet.");
            return;
        }

        System.out.print("Nyt navn: ");
        String newName = scanner.nextLine();
        System.out.print("Ny alder: ");
        int newAge = Integer.parseInt(scanner.nextLine());
        System.out.print("Ny kontaktinfo: ");
        String newContact = scanner.nextLine();
        System.out.print("Aktiv? (ja/nej): ");
        boolean isActive = scanner.nextLine().equalsIgnoreCase("ja");

        Member.MembershipType membershipType = newAge < 18 ? Member.MembershipType.JUNIOR : Member.MembershipType.SENIOR;

        System.out.print("Aktivitetsform (motionist/konkurrence): ");
        String activityInput = scanner.nextLine().toLowerCase();
        Member.ActivityType activityType = activityInput.equals("konkurrence") ? Member.ActivityType.KONKURRENCE : Member.ActivityType.MOTIONIST;

        Member newInfo = new Member(newName, newAge, newContact, isActive, membershipType, activityType);
        database.editMember(name, newInfo);
        System.out.println("Medlem opdateret.");
    }

    private void deleteMember(Scanner scanner) {
        System.out.print("Indtast navnet på det medlem, der skal slettes: ");
        String name = scanner.nextLine();
        Member member = database.findMemberByName(name);

        if (member != null) {
            database.removeMember(member);
            System.out.println("Medlem slettet.");
        } else {
            System.out.println("Medlem ikke fundet.");
        }
    }

    private void listMembers() {
        System.out.println("\n--- Alle medlemmer ---");
        for (Member m : database.getAllMembers()) {
            System.out.println(m);
            System.out.println("-----------------------");
        }
    }

        // Method to add training results to a members and create swimmer
        private void createSwimmers(Scanner scanner) {
            System.out.print("Indtast medlem ID: ");
            int memberId = scanner.nextInt();
            scanner.nextLine();

            // Shows a list of possible disciplines
            System.out.println("Vælg disciplin:");
            for (int i = 0; i < SwimmerResult.Discipline.values().length; i++) {
                System.out.println((i + 1) + ". " + SwimmerResult.Discipline.values()[i]);
            }

            int disciplinValg;
            do {
                System.out.print("Indtast nummer (1-" + SwimmerResult.Discipline.values().length + "): ");
                disciplinValg = scanner.nextInt();
                scanner.nextLine();
            } while (disciplinValg < 1 || disciplinValg > SwimmerResult.Discipline.values().length);

            SwimmerResult.Discipline disciplines = SwimmerResult.Discipline.values()[disciplinValg - 1];

            // Adds time result
            System.out.print("Indtast tid (mm:ss): ");
            String tid = scanner.nextLine();

            // Adds date of training
            System.out.print("Indtast dato (dd-MM-yyyy): ");
            String dato = scanner.nextLine();


            swimmerDb.addSwimmer(memberId, disciplines, tid, dato);
        }

        // Method that shows top 5 swimmers in each discipline for each age group
    private void showTop5Svimmers(Scanner scanner) {
        System.out.println("Vælg medlemsgruppe:");
        System.out.println("1. Junior");
        System.out.println("2. Senior");
        System.out.print("Valg: ");

        int groupChoice = scanner.nextInt();
        scanner.nextLine();

        Member.MembershipType groupType;
        if (groupChoice == 1) {
            groupType = Member.MembershipType.JUNIOR;
        } else {
            groupType = Member.MembershipType.SENIOR;
        }

        System.out.println("Vælg disciplin:");
        SwimmerResult.Discipline[] disciplines = SwimmerResult.Discipline.values();
        for (int i = 0; i < disciplines.length; i++) {
            System.out.println((i + 1) + ". " + disciplines[i]);
        }

        System.out.print("Valg: ");
        int disciplineChoice = scanner.nextInt();
        scanner.nextLine();

        if (disciplineChoice < 1 || disciplineChoice > disciplines.length) {
            System.out.println("Ugyldigt disciplinvalg.");
            return;
        }

        SwimmerResult.Discipline selectedDiscipline = disciplines[disciplineChoice - 1];

        swimmerDb.sortTop5Swimmers(
                selectedDiscipline,
                groupType,
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }


    //Method that returns to start of the menu
    private void returnToMenu (Scanner scanner) {
        while (true) {
            System.out.print("\n🔄 Tryk (1) for at gå tilbage til menuen: ");
            String input = scanner.nextLine().trim();

            if (input.equals("1")) {
                return;
            } else {
                System.out.println("\n⚠ Ugyldigt input. Tryk (1) for at vende tilbage til menuen.");
            }
        }
    }
}