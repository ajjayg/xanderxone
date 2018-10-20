/**
 * 
 */
package org.cassandramigration.validator;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ajjay
 *
 */
public class CQLCommandValidator {
	private static final List<String> SCHEMA_LEVEL = Arrays.asList("CREATE", "ALTER", "DROP");
	private static final List<String> DATA_LEVEL = Arrays.asList("INSERT", "UPDATE", "DELETE", "TRUNCATE");

	private CQLCommandValidator() {
	}

	public static boolean isDDLCommand(final String command) {
		if (StringUtils.isEmpty(command))
			return false;
		return SCHEMA_LEVEL.contains(getStartOfStatement(command));
	}

	public static boolean isDMLCommand(final String command) {
		if (StringUtils.isEmpty(command))
			return false;

		return DATA_LEVEL.contains(getStartOfStatement(command));
	}

	/**
	 * @param command
	 * @return
	 */
	private static String getStartOfStatement(final String command) {
		return command.split("\\s+")[0];
	}
}
