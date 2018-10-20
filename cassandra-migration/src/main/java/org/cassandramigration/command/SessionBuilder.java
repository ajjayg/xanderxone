/**
 * 
 */
package org.cassandramigration.command;

import java.util.List;

import org.apache.cassandra.db.KeyspaceNotDefinedException;
import org.cassandramigration.config.MigrationSourceConfig;
import org.cassandramigration.config.model.CassandraConfig;
import org.cassandramigration.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

/**
 * @author ajjay
 *
 */
public class SessionBuilder {
	private static final Logger LOG = LoggerFactory.getLogger(SessionBuilder.class);

	private final CassandraConfig cassandraConfig;
	private Cluster cluster;
	private Session session;

	public SessionBuilder() {
		cassandraConfig = new MigrationSourceConfig().getCassandraConfigDetails();
		if (null == cassandraConfig || !cassandraConfig.isFullyValid()) {
			throw new ResourceNotFoundException("Resource not fully qualified to create cluster object.",
					cassandraConfig);
		}
		init();
	}

	private void init() {
		buildCluster();
		Metadata metadata = cluster.getMetadata();
		logConnectionInfo(metadata);
		validateKeySpace(metadata);
		setSession();
	}

	private Cluster buildCluster() {
		try {
			cluster = Cluster.builder().addContactPoints(cassandraConfig.getHostList())
					.withPort(Integer.parseInt(cassandraConfig.getPort()))
					.withCredentials(cassandraConfig.getUsername(), cassandraConfig.getPassword()).build();
		} catch (Exception ex) {
			LOG.error("Exception while creating cluster object..", ex);
		}
		return cluster;
	}

	private void setSession() {
		this.session = cluster.newSession();
		this.session.execute("USE " + cassandraConfig.getKeyspace());
	}

	public Session getSession() {
		return this.session;
	}

	private void validateKeySpace(final Metadata metadata) {
		Boolean isValid = Boolean.FALSE;
		List<KeyspaceMetadata> keyspaces = metadata.getKeyspaces();
		for (KeyspaceMetadata keyspaceMetadata : keyspaces) {
			if (keyspaceMetadata.getName().equals(cassandraConfig.getKeyspace())) {
				isValid = Boolean.TRUE;
				break;
			}
		}
		if (!isValid) {
			LOG.error("Configured keyspace:{} doesn't exist ", cassandraConfig.getKeyspace());
			throw new KeyspaceNotDefinedException("Configured keyspace doesn't exist.");
		}
	}

	private void logConnectionInfo(final Metadata metadata) {
		StringBuilder sb = new StringBuilder();
		sb.append("Successfully Connected to cluster: ");
		sb.append(metadata.getClusterName());

		for (Host host : metadata.getAllHosts()) {
			sb.append("\n @Data center: ");
			sb.append(host.getDatacenter());
			sb.append("\t@Host: ");
			sb.append(host.getAddress());
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("***********************************************");
			LOG.info(sb.toString());
			LOG.info("***********************************************");
		}
	}

	public void close() {
		if (null != session)
			this.session.close();
		if (null != cluster)
			this.cluster.close();
	}

}
