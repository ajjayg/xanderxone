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
public class DataCommand {
	private static final Logger LOG = LoggerFactory.getLogger(DataCommand.class);

	private SessionBuilder sessionBuilder;

	/**
	 * 
	 */
	public DataCommand() {
		sessionBuilder = new SessionBuilder();
	}

	public void execute(final List<String> dmlQueries) {
		try {
			for (final String query : dmlQueries) {
				sessionBuilder.getSession().execute(query);
			}
		} catch (Exception e) {
			LOG.error("Exception while performing DML through migrations.", e);
		} finally {
			sessionBuilder.close();
			sessionBuilder = null;
		}

	}

}
