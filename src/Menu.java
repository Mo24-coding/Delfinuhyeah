import java.util.Scanner;

public class Menu {
    private final MemberDatabase memberDatabase;
    private final SwimmerDatabase swimmerDatabase;
    private final CompetitionDatabase competitionDatabase;

    public Menu(MemberDatabase memberDatabase, SwimmerDatabase swimmerDatabase, CompetitionDatabase competitionDatabase) {
        this.memberDatabase = memberDatabase;
        this.swimmerDatabase = swimmerDatabase;
        this.competitionDatabase = competitionDatabase;
    }

    // Methods that creates a text-based menu that calls other methods
    public void showMenu(Scanner scanner) {

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
            System.out.println("9. Vis alle konkurrencesvømmer");
            System.out.println("10. Mangler kontingent");
            System.out.println("11. 🔴 Afslut program");
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
                    memberDatabase.listMembers();
                    returnToMenu(scanner);
                    break;
                case 5:
                    System.out.println("Viser liste med medlemmer...");
                    memberDatabase.listMembers();
                    createSwimmers(scanner);
                    returnToMenu(scanner);
                    break;
                case 6:
                    swimmerDatabase.showSwimmerList();
                    returnToMenu(scanner);
                    break;
                case 7:
                    swimmerDatabase.top5ListForSvimmwers();
                    returnToMenu(scanner);
                    break;
                case 8:
                    System.out.println("Opretter konkurrencesvømmer");
                    addSwimmerToCompetition(scanner);
                    returnToMenu(scanner);
                    break;
                case 9:
                    System.out.println("Viser liste med konkurrencesvømmer");
                    competitionDatabase.showAllCompetitors();
                    returnToMenu(scanner);
                    break;
                case 10:
                    System.out.println("......kontingent");
                    System.out.println("mangler");
                    returnToMenu(scanner);
                    break;
                case 11:
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


    // Method that creates a new member
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
        memberDatabase.addMember(member);
        System.out.println("Medlem oprettet.");
    }

    // Method that edits member data
    private void editMember(Scanner scanner) {
        System.out.print("Indtast navnet på det medlem, der skal redigeres: ");
        String name = scanner.nextLine();
        Member existing = memberDatabase.findMemberByName(name);

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
        memberDatabase.editMember(name, newInfo);
        System.out.println("Medlem opdateret.");
    }

    // Method that deletes a member from the list
    private void deleteMember(Scanner scanner) {
        System.out.print("Indtast navnet på det medlem, der skal slettes: ");
        String name = scanner.nextLine();
        Member member = memberDatabase.findMemberByName(name);

        if (member != null) {
            memberDatabase.removeMember(member);
            System.out.println("Medlem slettet.");
        } else {
            System.out.println("Medlem ikke fundet.");
        }
    }


        // Method that adds training results to a members and creates swimmer by using addSwimmer() method
        private void createSwimmers(Scanner scanner) {
            System.out.print("Indtast medlems-ID: ");
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

            System.out.print("Indtast tid (mm:ss): ");
            String tid = scanner.nextLine();

            System.out.print("Indtast dato (dd-MM-yyyy): ");
            String dato = scanner.nextLine();


            swimmerDatabase.addSwimmer(memberId, disciplines, tid, dato);
        }

    // Method that adds a swimmer to a competition (konkurrencesvømmer) from inputs
    private void addSwimmerToCompetition(Scanner scanner) {
        System.out.print("Indtast medlems-ID: ");
        int memberId = Integer.parseInt(scanner.nextLine());

        // Choose which competition
        System.out.println("Vælg konkurrence:");
        CompetitionResult.Competition[] comps = CompetitionResult.Competition.values();
        for (int i = 0; i < comps.length; i++) {
            System.out.println((i + 1) + ". " + comps[i]);
        }
        int compChoice = Integer.parseInt(scanner.nextLine());
        CompetitionResult.Competition competition = comps[compChoice - 1];

        System.out.print("Indtast tid (mm:ss): ");
        String time = scanner.nextLine();

        System.out.print("Indtast placering (Mellem 1-5): ");
        int ranking = Integer.parseInt(scanner.nextLine());

        competitionDatabase.addCompetitionToList(memberId, competition, time, ranking);
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