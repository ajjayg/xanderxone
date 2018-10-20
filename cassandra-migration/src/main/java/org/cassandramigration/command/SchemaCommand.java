/**
 * 
 */
package org.cassandramigration.command;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ajjay
 *
 */
public class SchemaCommand {
	private static final Logger LOG = LoggerFactory.getLogger(SchemaCommand.class);

	private SessionBuilder sessionBuilder;

	/**
	 * 
	 */
	public SchemaCommand() {
		sessionBuilder = new SessionBuilder();
	}

	public void execute(final List<String> ddlQueries) {
		try {
			for (final String query : ddlQueries) {
				sessionBuilder.getSession().execute(query);
			}
		} catch (Exception e) {
			LOG.error("Exception while performing DDL through migrations.", e);
		} finally {
			sessionBuilder.close();
			sessionBuilder = null;
		}

	}

}
