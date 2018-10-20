/**
 * 
 */
package org.cassandramigration.model;

import java.util.List;

import lombok.Data;

/**
 * @author ajjay
 *
 */
@Data
public class MigrationResource {
	private String name;
	private String path;
	private String checksum;
	private List<CQLStatement> cqlStatements;
	private List<ErrorTrace> errorTrace;
	private Version version;
	
}
