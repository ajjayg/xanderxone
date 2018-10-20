/**
 * 
 */
package org.cassandramigration.parsers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ajjay
 *
 */
public class ResourceScanner {
	private static final Logger LOG = LoggerFactory.getLogger(ResourceScanner.class);

	/**
	 * Scan's resource location recursively to find migration files.
	 * 
	 * @param resourceLocation
	 * @return
	 */
	public List<String> scanLocationForResources(final String resourceLocation) {

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource(resourceLocation);

		File directory = new File(url.getPath());
		LOG.info("Picking up migration files from directory : migrations");
		File[] fList = directory.listFiles();
		List<String> filePaths = new ArrayList<>();
		for (File file : fList) {
			filePaths.add(file.getPath());
		}
		return filePaths;
	}

}
