/**
 * 
 */
package org.cassandramigration.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ajjay
 *
 */
public class InfoCommand {
	private static final Logger LOG = LoggerFactory.getLogger(InfoCommand.class);
	private String infoQuery = "select * from schema_version";
	private SessionBuilder sessionBuilder;

	/**
	 * 
	 */
	public InfoCommand() {
		sessionBuilder = new SessionBuilder();
	}

	public void execute() {
		try {
			sessionBuilder.getSession().execute(infoQuery);
		} catch (Exception e) {
			LOG.error("Exception while performing Info query through migrations.", e);
		} finally {
			sessionBuilder.close();
			sessionBuilder = null;
		}

	}

}
