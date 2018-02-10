package org.usfirst.frc.team2175.log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggingConfig {
	/** Set to root package, and must match one set in logging.properties. */
	public static final String ROOT_LOGGER_NAME = "org.usfirst.frc.team2175";

	public static final String PROPERTY_FILE_PATH = "/home/lvuser/properties/logging.properties";

	public static void initialize() {
		initializeFileLog(PROPERTY_FILE_PATH);
	}

	protected static void initializeFileLog(final String propertyFile) {
		System.out.println("Initializing file logging");

		final LogManager logManager = LogManager.getLogManager();

		// regretfully the icky java.util.logging won't allow adding an existing
		// property file to it so we have to reload the properties file again
		try (final InputStream in = new FileInputStream(propertyFile)) {
			logManager.readConfiguration(in);
		} catch (final FileNotFoundException e) {
			throw new IllegalStateException(
				"Did not find logging properties file=" + propertyFile + ", msg=" + e.getMessage(), e);
		} catch (SecurityException | IOException e) {
			throw new IllegalStateException("Unable to read logging properties", e);
		}

		final String levelProperty = logManager.getProperty("java.util.logging.FileHandler.level");

		final Logger log = Logger.getLogger(LoggingConfig.class.getName());
		final Level level = log.getLevel();
		log.info("File logging initialized, actual logging level=" + level + ", configured level=" + levelProperty);
	}
}
