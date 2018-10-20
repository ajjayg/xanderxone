/**
 * 
 */
package org.cassandramigration.parsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ajjay
 *
 */
public class CQLContentParser {

	private static final Logger LOG = LoggerFactory.getLogger(CQLContentParser.class);
	private static final String DELIMETER = ";";
	private static final String SPACE = " ";

	public List<String> readCQLFile(final String fileLocation) {
		List<String> queryStringList = new ArrayList<>();
		LOG.info("request initiated with fileLocation: {} ", fileLocation);
		Path path = null;
		Stream<String> lines = null;
		try {
			path = Paths.get(fileLocation);
			lines = Files.lines(path);
		} catch (IOException e) {
			LOG.error("Exception with error,", e);
		}
		if (null == lines)
			return queryStringList;

		StringBuilder data = new StringBuilder();

		lines.forEach(line -> {
			line = line.trim();
			if (!line.isEmpty() && !line.matches("^--*.*") 
					&& !line.matches("^///*.*") && !line.matches(".*\\*/$")
					&& !line.matches(".*\\*$") && !line.matches("^\\*.*")) {
				
				data.append(line).append(SPACE);
				if (line.endsWith(DELIMETER)) {
					queryStringList.add(data.toString());
					data.delete(0, data.length());
				}
			}

		});
		lines.close();

		return queryStringList;
	}
}
