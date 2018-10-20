/**
 * 
 */
package org.cassandramigration.exception;

/**
 * @author ajjay
 *
 */
public class InvalidStatementException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10001L;

	/**
	 * 
	 */
	public InvalidStatementException() {
	}

	/**
	 * @param message
	 */
	public InvalidStatementException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidStatementException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidStatementException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidStatementException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
