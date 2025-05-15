import java.util.Scanner;
import static utility.Colors.*;

public class Menu {
    private final MemberDatabase memberDatabase;
    private final SwimmerDatabase swimmerDatabase;
    private final CompetitionDatabase competitionDatabase;

    public Menu(MemberDatabase memberDatabase, SwimmerDatabase swimmerDatabase, CompetitionDatabase competitionDatabase) {
        this.memberDatabase = memberDatabase;
        this.swimmerDatabase = swimmerDatabase;
        this.competitionDatabase = competitionDatabase;
    }

    // Main menu method that creates a text-based menu that calls submenus
    public void showMenu(Scanner scanner) {

        while (true) {
            System.out.println(bold(cyan("\n🐬  SvømmeKlubben DELFINEN  🐬")));
            System.out.println(cyan("---------------------------------------------"));
            System.out.println(blue("1️⃣  👔  Formand‑login"));
            System.out.println(green("2️⃣  💰  Kasserer‑login"));
            System.out.println(yellow("3️⃣  🏊‍♂️  Træner‑login"));
            System.out.println(red("0️⃣  🔴  Afslut program"));
            System.out.print(magenta("\nVælg en mulighed ➜ "));

            try {
                int choice = Integer.parseInt(scanner.nextLine()); //Reads input as String and parse (change) to int

            switch (choice) {
                case 1:
                    System.out.println("\n🔐  Logget ind som " + bold("Formand"));
                    subMenuChairman(scanner);
                    break;
                case 2:
                    System.out.println("\n🔐  Logget ind som " + bold("Kasserer"));
                    subMenuCashier(scanner);
                    break;
                case 3:
                    System.out.println("\n🔐  Logget ind som " + bold("Træner"));
                    subMenuTrainer(scanner);
                    break;
                case 0:
                    System.out.println(red("\n🔴  Afslutter programmet..."));
                    return;
                default:
                    System.out.println(red("\n⚠  Ugyldigt valg – prøv igen."));
            }
            } catch (NumberFormatException e) { //Catches anything other than int inputs
                System.out.println(red("\n⚠  Indtast et tal mellem 0 og 3."));
            }
        }
    }

    // Chairman (Formand) submenu method that calls other methods
    private void subMenuChairman (Scanner scanner) {

        while (true) {

            System.out.println(cyan("\n👔  FORMANDS‑MENU"));
            System.out.println(cyan("----------------"));
            System.out.println(blue("1️⃣  ➕  Opret medlem"));
            System.out.println(yellow("2️⃣  ✏️  Rediger medlem"));
            System.out.println(red("3️⃣  🗑️  Slet medlem"));
            System.out.println(green("4️⃣  📋  Vis alle medlemmer"));
            System.out.println(magenta("0️⃣  🔙  Tilbage til hovedmenu"));
            System.out.print(magenta("\nVælg en mulighed ➜ "));

            try {
                int choice = Integer.parseInt(scanner.nextLine()); //Reads input as String and parse (change) to int

                switch (choice) {
                    case 1:
                        System.out.println(green("\n➕  Opretter medlem ..."));
                        createMember(scanner);
                        returnToMenu(scanner);
                        break;
                    case 2:
                        System.out.println(yellow("\n✏️  Redigerer medlem ..."));
                        editMember(scanner);
                        returnToMenu(scanner);
                        break;
                    case 3:
                        System.out.println(red("\n🗑️  Sletter medlem ..."));
                        deleteMember(scanner);
                        returnToMenu(scanner);
                        break;
                    case 4:
                        System.out.println(green("\n📋  Medlemsliste"));
                        memberDatabase.listMembers();
                        returnToMenu(scanner);
                        break;
                    case 0:
                        System.out.println(magenta("\n🔙  Tilbage til hovedmenuen ..."));
                        return; // Exit submenu
                    default:
                        System.out.println(red("\n⚠  Ugyldigt valg – prøv igen."));
                }
            } catch (NumberFormatException e) { //Catches anything other than int inputs
                System.out.println(red("\n⚠  Indtast et tal mellem 0 og 4."));
            }
        }
    }

    // Cashier (kasserer) submenu method that calls other methods
    private void subMenuCashier (Scanner scanner) {

        while (true) {

            System.out.println(cyan("\n💰  KASSERER‑MENU"));
            System.out.println(cyan("-----------------"));
            System.out.println(yellow("1️⃣    kontingent (mangler)"));
            System.out.println(magenta("0️⃣  🔙  Tilbage til hovedmenu"));
            System.out.print(magenta("\nVælg en mulighed ➜ "));

            try {
                int choice = Integer.parseInt(scanner.nextLine()); //Reads input as String and parse (change) to int

                switch (choice) {
                    case 1:
                        System.out.println(yellow("\n  ......kontingent"));
                        returnToMenu(scanner);
                        break;
                    case 0:
                        System.out.println(magenta("\n🔙  Tilbage til hovedmenuen ..."));
                        return;
                    default:
                        System.out.println(red("\n⚠  Ugyldigt valg – prøv igen."));
                }
            } catch (NumberFormatException e) { //Catches anything other than int inputs
                System.out.println(red("\n⚠  Indtast et tal mellem 0 og 1."));
            }
        }
    }

    // Trainer (træner) submenu method that calls other methods
    private void subMenuTrainer (Scanner scanner) {

        while (true) {

            System.out.println(cyan("\n🏊‍♂️  TRÆNER‑MENU"));
            System.out.println(cyan("----------------"));
            System.out.println(blue("1️⃣  ➕  Opret svømmer"));
            System.out.println(green("2️⃣  📋  Vis alle svømmere"));
            System.out.println(yellow("3️⃣  🏆  Top 5 svømmere"));
            System.out.println(blue("4️⃣  ➕  Opret konkurrencesvømmer"));
            System.out.println(green("5️⃣  📋  Vis konkurrencesvømmere"));
            System.out.println(magenta("0️⃣  🔙  Tilbage til hovedmenu"));
            System.out.print(magenta("\nVælg en mulighed ➜ "));

            try {
                int choice = Integer.parseInt(scanner.nextLine()); //Reads input as String and parse (change) to int

                switch (choice) {
                    case 1:
                        System.out.println(green("\n📋  Medlemsliste"));
                        memberDatabase.listMembers();
                        System.out.println(blue("\n➕  Opretter ny svømmer ..."));
                        createSwimmers(scanner);
                        returnToMenu(scanner);
                        break;
                    case 2:
                        System.out.println(green("\n📋  Viser alle svømmere ..."));
                        swimmerDatabase.showSwimmerList();
                        returnToMenu(scanner);
                        break;
                    case 3:
                        System.out.println(yellow("\n🏆  Viser alle top 5 svømmere ..."));
                        swimmerDatabase.top5ListForSvimmwers();
                        returnToMenu(scanner);
                        break;
                    case 4:
                        System.out.println(yellow("\n🏆  Viser alle top 5 svømmere ..."));
                        swimmerDatabase.top5ListForSvimmwers();
                        System.out.println(blue("\n➕  Opretter konkurrencesvømmer ..."));
                        addSwimmerToCompetition(scanner);
                        returnToMenu(scanner);
                        break;
                    case 5:
                        System.out.println(green("\n📋  Viser alle Konkurrence‑svømmere ..."));
                        competitionDatabase.showAllCompetitors();
                        returnToMenu(scanner);
                        break;
                    case 0:
                        System.out.println(magenta("\n🔙  Tilbage til hovedmenuen ..."));
                        return; // Exit submenu
                    default:
                        System.out.println(red("\n⚠  Ugyldigt valg – prøv igen."));
                }
            } catch (NumberFormatException e) { //Catches anything other than int inputs
                System.out.println(red("\n⚠  Indtast et tal mellem 0 og 5."));
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
            System.out.print("\n🔄 Tryk (1) for at gå tilbage: ");
            String input = scanner.nextLine().trim();

            if (input.equals("1")) {
                return;
            } else {
                System.out.println(red("\n⚠ Ugyldigt input. Tryk (1) for at vende tilbage til menuen."));
            }
        }
    }
}