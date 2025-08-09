package businesslayer;

/**
 * Thrown when a business‐rule check fails (invalid input, missing data, etc.).
 * @author oussema
 */
public class ValidationException extends RuntimeException {
    /** No‐arg constructor */
    public ValidationException() {
        super();
    }

    /** Message‐only constructor */
    public ValidationException(String message) {
        super(message);
    }

    /** Message + cause */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /** Cause‐only constructor */
    public ValidationException(Throwable cause) {
        super(cause);
    }
}
