/**
 * 
 */
package org.cassandramigration;

import org.cassandramigration.config.MigrationSourceConfig;
import org.cassandramigration.config.model.CassandraConfig;
import org.cassandramigration.parsers.ResourceScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ajjay
 *
 */
public class Orchestration {
	private static final Logger LOG = LoggerFactory.getLogger(Orchestration.class);

	private MigrationSourceConfig migrationSourceConfig;
	private ResourceScanner resourceScanner;

	public Orchestration() {
		migrationSourceConfig = new MigrationSourceConfig();
		resourceScanner = new ResourceScanner();
	}

	public void cue() {
		CassandraConfig configDetails = migrationSourceConfig.getCassandraConfigDetails();
		LOG.info("Loaded Configuration details. {}", configDetails);
		healthCheck(configDetails);

		reset();
	}

	private void healthCheck(CassandraConfig configDetails) {

	}

	private void reset() {
		LOG.info("retiring initialized instances.");
		migrationSourceConfig = null;
		resourceScanner = null;
	}
}
