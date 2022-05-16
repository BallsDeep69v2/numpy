package errorhandling;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidInputException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
