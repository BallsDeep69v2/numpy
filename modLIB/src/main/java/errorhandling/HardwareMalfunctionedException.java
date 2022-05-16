package errorhandling;

public class HardwareMalfunctionedException extends RuntimeException {
    public HardwareMalfunctionedException() {
        super();
    }

    public HardwareMalfunctionedException(String errorMessage) {
        super(errorMessage);
    }

    public HardwareMalfunctionedException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
