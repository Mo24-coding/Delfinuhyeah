package utility;

// subclass that extends CustomException
public class SwimmerNotFoundException extends CustomException {

    // Constructor for swimmer not finding "aktiv konkurrencesvømmer" exception
    public SwimmerNotFoundException(int id) {
        super("Medlem med ID " + id + " findes ikke som aktiv konkurrencesvømmer eller forkert ID");
    }
}