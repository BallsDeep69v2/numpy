package errorhandling;

public class ISBNHttpException extends RuntimeException {
    public ISBNHttpException() {
    }

    public ISBNHttpException(String message) {
        super(message);
    }

    public ISBNHttpException(String message, Throwable cause) {
        super(message, cause);
    }
}
