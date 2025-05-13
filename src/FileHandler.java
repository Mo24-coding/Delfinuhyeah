import java.io.*;
import java.util.List;

public class FileHandler {

    // Gemmer liste af medlemmer til fil
    public static void saveMembers(List<Member> members, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(members);
            System.out.println("Medlemsdata gemt til fil: " + filename);
        } catch (IOException e) {
            System.out.println("Fejl ved gemning af medlemsdata: " + e.getMessage());
        }
    }

    // Indlæser liste af medlemmer fra fil
    @SuppressWarnings("unchecked")
    public static List<Member> loadMembers(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Member>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fejl ved indlæsning af medlemsdata: " + e.getMessage());
            return null;
        }
    }

    // Gemmer liste af svømmeresultater til fil
    public static void saveResults(List<SwimmerResult> results, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(results);
            System.out.println("Resultatdata gemt til fil: " + filename);
        } catch (IOException e) {
            System.out.println("Fejl ved gemning af resultatdata: " + e.getMessage());
        }
    }

    // Indlæser liste af svømmeresultater fra fil
    @SuppressWarnings("unchecked")
    public static List<SwimmerResult> loadResults(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<SwimmerResult>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fejl ved indlæsning af resultatdata: " + e.getMessage());
            return null;
        }
    }
}
