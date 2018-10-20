/**
 * 
 */
package org.cassandramigration.parsers;

import org.cassandramigration.model.MigrationResource;

/**
 * @author ajjay
 *
 */
public class CQLValidator {

	public void validateSourceContent(MigrationResource resource) {
		resource.getCqlStatements().forEach(statement -> {
			if (!statement.isTerminated()) {

			}

		});
	}

}
