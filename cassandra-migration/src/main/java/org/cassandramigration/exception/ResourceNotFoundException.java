/**
 * 
 */
package org.cassandramigration.exception;

import org.cassandramigration.config.model.CassandraConfig;

/**
 * @author ajjay
 *
 */
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1000L;
	private CassandraConfig resourceObject;

	public CassandraConfig getResourceObject() {
		return this.resourceObject;
	}

	/**
	 * 
	 */
	public ResourceNotFoundException() {
	}

	/**
	 * @param message
	 */
	public ResourceNotFoundException(String message, CassandraConfig resourceObject) {
		super(message);
		this.resourceObject = resourceObject;
	}

	/**
	 * @param cause
	 */
	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
