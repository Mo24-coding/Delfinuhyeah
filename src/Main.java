import java.util.Scanner;

public class Main {
    private static final MemberDatabase database = new MemberDatabase();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createMember();
                    break;
                case "2":
                    editMember();
                    break;
                case "3":
                deleteMember();
                break;
                case "4":
                    listMembers();
                    break;
                case "5":
                    running = false;
                    System.out.println("Programmet afsluttes.");
                    break;
                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Svømmeklubben Delfinen ---");
        System.out.println("1. Opret medlem");
        System.out.println("2. Rediger medlem");
        System.out.println("3. Slet medlem");
        System.out.println("4. Vis alle medlemmer");
        System.out.println("5. Afslut");
        System.out.print("Vælg en mulighed: ");
    }

    private static void createMember() {
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

    private static void editMember() {
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

    private static void deleteMember() {
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

    private static void listMembers() {
        System.out.println("\n--- Alle medlemmer ---");
        for (Member m : database.getAllMembers()) {
            System.out.println(m);
            System.out.println("-----------------------");
        }
    }
}
