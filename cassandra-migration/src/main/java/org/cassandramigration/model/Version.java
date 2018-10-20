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
public class Version {
	/**
	 * version number of the cql resource
	 */
	private long number;
	/**
	 * Migration Type
	 */
	private MigrationType type;
	/**
	 * CQL resource description
	 */
	private String description;
}
