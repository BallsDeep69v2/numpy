package errorhandling;

public class RuntimeSQLException extends RuntimeException {
    public RuntimeSQLException(String errorMessage) {
        super(errorMessage);
    }

    public RuntimeSQLException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
