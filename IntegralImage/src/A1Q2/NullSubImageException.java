package A1Q2;

/**
 *
 * @author yuxian wang
 */
public class NullSubImageException extends Exception {

    /**
     * Creates a new instance of <code>NullSubImageException</code> without
     * detail message.
     */
    public NullSubImageException() {
    }

    /**
     * Constructs an instance of <code>NullSubImageException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NullSubImageException(String msg) {
        super(msg);
    }
}
