import utility.ProgressBar;
import java.util.Scanner;

// Main class
public class Club {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in); // Creates scanner object
        MemberDatabase memberDb = new MemberDatabase(); // Creates MemberDatabase object
        SwimmerDatabase swimmerDb = new SwimmerDatabase(memberDb); // Creates SwimmerDataBase object using MemberDatabase
        CompetitionDatabase competitionDB = new CompetitionDatabase(swimmerDb); // Creates competitionDataBase object using SwimmerDatabase
        Menu menu = new Menu(memberDb, swimmerDb, competitionDB); // Created Menu object using all databases
        //swimmerDb.populateTestSwimmers();     // TEST CODE - IKKE SLET
        ProgressBar.runProgressBar(100); // Progress bar
        menu.showMenu(scanner); // Calls the showMenu method
    }
}