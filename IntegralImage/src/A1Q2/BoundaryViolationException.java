package A1Q2;

/**
 *
 * @author yuxian wang
 */
public class BoundaryViolationException extends Exception {

    /**
     * Creates a new instance of <code>BoundaryViolationException</code> without
     * detail message.
     */
    public BoundaryViolationException() {
    }

    /**
     * Constructs an instance of <code>BoundaryViolationException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public BoundaryViolationException(String msg) {
        super(msg);
    }
}
