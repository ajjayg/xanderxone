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
public class CQLStatement {
	/*
	 * CQL statement to be executed on the DB
	 */
	private String statement;
	/*
	 * Start of line number from the CQL source file.
	 */
	private int lineNumber;
	/*
	 * ';' check with a proper termination for a statement recorded from the CQL
	 * source file
	 */
	private boolean isTerminated;
	/*
	 * Valid statement to be executed on the Database
	 */
	private boolean isValid;
	
	
}
