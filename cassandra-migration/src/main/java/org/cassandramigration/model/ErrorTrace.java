/**
 * 
 */
package org.cassandramigration.model;

import lombok.Data;

/**
 * @author ajjay
 *
 */
@Data
public class ErrorTrace {
	/**
	 * CQL statement for which error has occurred
	 */
	private String cqlStatement;
	/**
	 * CQL statement start line number from the source
	 */
	private int lineNumber;
	/**
	 * Short message for the reason of error
	 */
	private String reason;
	/**
	 * Complete Stack trace log of the Exception.
	 */
	private String errorLog;
}
