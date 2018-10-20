/**
 * 
 */
package org.cassandramigration;

import org.cassandramigration.exception.ResourceNotFoundException;
import org.cassandramigration.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ajjay
 *
 */
public class CassandraMigrationApplication {
	private static final Logger LOG = LoggerFactory.getLogger(CassandraMigrationApplication.class);

	public static void main(String[] args) {
		LOG.info("Request initiated for migrations @ {}", DateUtils.getCurrentTimeISO());

		initiateMigrations();

		LOG.info("Request processed for migrations @ {}", DateUtils.getCurrentTimeISO());
	}

	private static void initiateMigrations() {
		try {
			new Orchestration().cue();

		} catch (ResourceNotFoundException rnfe) {
			LOG.error(rnfe.getMessage());
			LOG.error(rnfe.getResourceObject().toString());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}
}
