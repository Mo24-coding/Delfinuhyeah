package utility;

// subclass thats extends RuntimeException
public abstract class CustomException extends RuntimeException {

    // Constructor for custom messages
    protected CustomException(String message) {
        super(message);
    }
}
