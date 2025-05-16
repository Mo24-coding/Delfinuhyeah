package data;

import java.io.*;
import java.util.List;

/**
 * FileHandler håndterer al fil-læsning og -skrivning for medlemmer og resultater.
 * Gemmer og indlæser lister af objekter vha. serialization.
 */
public class FileHandler {

    // Gemmer en liste af medlemmer til fil
    public static void saveMembers(List members, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(members);
            System.out.println("Medlemsdata gemt til fil: " + filename);
        } catch (IOException e) {
            System.out.println("Fejl ved gemning af medlemsdata: " + e.getMessage());
        }
    }

    // Indlæser en liste af medlemmer fra fil
    @SuppressWarnings("unchecked")
    public static List loadMembers(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fejl ved indlæsning af medlemsdata: " + e.getMessage());
            return null;
        }
    }

    // Gemmer en liste af svømmeresultater eller konkurrence-resultater til fil
    public static void saveResults(List results, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(results);
            System.out.println("Resultatdata gemt til fil: " + filename);
        } catch (IOException e) {
            System.out.println("Fejl ved gemning af resultatdata: " + e.getMessage());
        }
    }

    // Indlæser en liste af svømmeresultater eller konkurrence-resultater fra fil
    @SuppressWarnings("unchecked")
    public static List loadResults(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fejl ved indlæsning af resultatdata: " + e.getMessage());
            return null;
        }
    }
}
