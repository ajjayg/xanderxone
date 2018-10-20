/**
 * 
 */
package org.cassandramigration.utils;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author ajjay
 *
 */
public class DateUtils {
	private DateUtils() {
	}

	public static String getCurrentTimeISO() {
		return Instant.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME);
	}

	public static String format(long millis) {
		return String.format("%02d:%02d.%03ds", millis / 60000, (millis % 60000) / 1000, (millis % 1000));
	}
}
