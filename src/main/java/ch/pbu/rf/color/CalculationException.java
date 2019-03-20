package ch.pbu.rf.color;

/**
 * Represents the calculation-exception.
 * 
 * @author Yanick Senn
 */
public class CalculationException extends RuntimeException {
	
	/**
	 * Constructor.
	 */
	public CalculationException() {
        super();
    }
	
	/**
	 * Constructor with message.
	 * 
	 * @param message Message.
	 */
    public CalculationException(String message) {
        super(message);
    }
    
    /**
     * Constructor with cause.
     * 
     * @param cause Cause.
     */
    public CalculationException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with message and cause.
     * 
     * @param message Message.
     * @param cause Cause.
     */
    public CalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
