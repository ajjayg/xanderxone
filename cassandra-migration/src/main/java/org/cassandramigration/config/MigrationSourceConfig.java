/**
 * 
 */
package org.cassandramigration.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.cassandramigration.config.model.CassandraConfig;
import org.cassandramigration.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

/**
 * @author ajjay
 *
 */
public class MigrationSourceConfig {
	private static final Logger LOG = LoggerFactory.getLogger(MigrationSourceConfig.class);
	private static final String CONFIG_YML = "cassandra_migrations.yml";
	private static final String CONFIG_PROPERTIES = "cassandra_migrations.properties";
	private ClassLoader loader = Thread.currentThread().getContextClassLoader();
	private CassandraConfig cassandraConfig = null;

	public CassandraConfig getCassandraConfigDetails() {
		loadConfigurtions();
		validate();
		return this.cassandraConfig;
	}

	private void loadConfigurtions() {
		if (null != loader.getResource(CONFIG_YML)) {
			parseYamFile();
		}
		if (null == cassandraConfig && null != loader.getResource(CONFIG_PROPERTIES)) {
			parsePropertiesFile();
		}
		if (null != cassandraConfig) {
			String seeds = cassandraConfig.getSeeds();
			if (!StringUtils.isEmpty(seeds)) {
				cassandraConfig.setHostList(seeds.trim().split("[,]"));
			}
		}
		LOG.info("Configuration loaded, {}", cassandraConfig);
	}

	private void parsePropertiesFile() {
		InputStream propertiesSourceAsStream = getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES);
		try {
			Properties configProperties = new Properties();
			configProperties.load(propertiesSourceAsStream);
			cassandraConfig = new CassandraConfig();
			cassandraConfig.setSeeds(configProperties.getProperty("seeds"));
			cassandraConfig.setPort(configProperties.getProperty("port"));
			cassandraConfig.setUsername(configProperties.getProperty("username"));
			cassandraConfig.setPassword(configProperties.getProperty("password"));
			cassandraConfig.setKeyspace(configProperties.getProperty("keyspace"));
			cassandraConfig.setResource_location(configProperties.getProperty("resource_location"));
		} catch (IOException e) {
			LOG.error("Unable to open properties file.", e);
		} finally {
			if (null != propertiesSourceAsStream) {
				try {
					propertiesSourceAsStream.close();
				} catch (IOException e) {
					LOG.error("Unable to close properties file.", e);
				}

			}
		}
	}

	private void parseYamFile() {
		Yaml yaml = new Yaml();
		InputStream ymlResourceAsStream = loader.getResourceAsStream(CONFIG_YML);
		try {
			cassandraConfig = yaml.loadAs(ymlResourceAsStream, CassandraConfig.class);
			ymlResourceAsStream.close();
		} catch (IOException e) {
			LOG.error("Unable to open properties file.", e);
		} finally {
			if (null != ymlResourceAsStream) {
				try {
					ymlResourceAsStream.close();
				} catch (IOException e) {
					LOG.error("Unable to close properties file.", e);
				}

			}
		}
	}

	private void validate() {
		if (null == cassandraConfig)
			throw new ResourceNotFoundException("Invalid config.", cassandraConfig);

		if (!cassandraConfig.isFullyValid())
			throw new ResourceNotFoundException("Insufficient config details", cassandraConfig);
	}
}
