package main;
import data.CompetitionDatabase;
import data.MemberDatabase;
import data.SwimmerDatabase;
import ui.Menu;
import utility.ProgressBar;
import java.util.Scanner;

// Main class
public class Club {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in); // Creates a scanner object
        MemberDatabase memberDb = new MemberDatabase(); // Creates a data.MemberDatabase object
        SwimmerDatabase swimmerDb = new SwimmerDatabase(memberDb); // Creates a SwimmerDataBase object using data.MemberDatabase
        CompetitionDatabase competitionDB = new CompetitionDatabase(swimmerDb); // Creates a competitionDataBase object using data.SwimmerDatabase
        Menu menu = new Menu(memberDb, swimmerDb, competitionDB); // Created a ui.Menu object using all databases
        //swimmerDb.populateTestSwimmers();     // TEST CODE - IKKE SLET
        ProgressBar.runProgressBar(100); // Progress bar
        menu.showMenu(scanner); // Calls the showMenu method
    }
}