package utility;

// subclass that extends CustomException
public class InvalidTimeFormatException extends CustomException {

    // Constructor for Invalid time format exception
    public InvalidTimeFormatException(String input) {
        super("Ugyldigt tidsformat: \"" + input + "\". Brug mm:ss.");
    }
}