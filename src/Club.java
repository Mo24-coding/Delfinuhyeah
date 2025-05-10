import java.util.Scanner;

public class Club {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MemberDatabase memberDb = new MemberDatabase();
        SwimmerDatabase swimmerDb = new SwimmerDatabase(memberDb);
        Menu menu = new Menu(memberDb, swimmerDb);
        menu.visMenu(scanner);
    }
}