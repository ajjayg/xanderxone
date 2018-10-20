/**
 * 
 */
package org.cassandramigration.config.model;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

/**
 * @author ajjay
 *
 */
@Data
public class CassandraConfig {
	/**
	 * You can provide a single contact point, but it is usually a good idea to
	 * provide more, so that the driver can fallback if the first one is down.
	 */
	private String seeds;
	private String port;
	private String username;
	private String password;
	private String keyspace;
	private String resource_location;
	private String[] hostList = new String[0];

	public boolean isFullyValid() {
		return (!StringUtils.isEmpty(seeds) && !StringUtils.isEmpty(port) && !StringUtils.isEmpty(username)
				&& !StringUtils.isEmpty(password) && !StringUtils.isEmpty(keyspace)
				&& !StringUtils.isEmpty(resource_location) && hostList.length > 0);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[seeds=");
		builder.append(seeds);
		builder.append(", port=");
		builder.append(port);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(StringUtils.isEmpty(password) ? null : "**********");
		builder.append(", keyspace=");
		builder.append(keyspace);
		builder.append(", resource_location=");
		builder.append(resource_location);
		builder.append("]");
		return builder.toString();
	}

}
