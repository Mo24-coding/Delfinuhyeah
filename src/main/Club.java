package main;

import data.CompetitionDatabase;
import data.MemberDatabase;
import data.SwimmerDatabase;
import ui.Menu;
import utility.ProgressBar;

import java.util.Scanner;

public class Club {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        MemberDatabase memberDb = new MemberDatabase();
        SwimmerDatabase swimmerDb = new SwimmerDatabase(memberDb);
        CompetitionDatabase competitionDB = new CompetitionDatabase(swimmerDb);

        Menu menu = new Menu(memberDb, swimmerDb, competitionDB);

        //swimmerDb.populateTestSwimmers(); // TEST CODE - IKKE SLET

        ProgressBar.runProgressBar(100);

        menu.showMenu(scanner);
    }
}
