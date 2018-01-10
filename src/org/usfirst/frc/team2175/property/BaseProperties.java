package org.usfirst.frc.team2175.property;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Base class for configuration, loading values from properties file.
 */
public abstract class BaseProperties {

	private final Logger log = Logger.getLogger(getClass().getName());

	/** This is the default directory set by the WPILib. */
	private static final String PROPERTY_FILE_DIR_DEFAULT = "/home/lvuser/";
	private static String propertyFileDir = PROPERTY_FILE_DIR_DEFAULT;
	private final Properties properties;

	public BaseProperties() {
		log.info("Configuring class '" + getClass() + "'");

		final String propertyFileName = getFullyQualifiedPropertyFileName();
		properties = new PropertiesLoader().loadProperties(propertyFileName);
		try {
			populate();
		} catch (final Throwable e) {
			final String msg = "ERROR populating class '" + getClass() + "'";
			log.log(Level.SEVERE, msg, e);
			throw e;
		}

		log.info("Finished configuring class '" + getClass() + "'");
	}

	/** @return The property file name to load for this config. */
	protected abstract String getPropertyFileName();

	/** Perform the configuration. */
	protected abstract void populate();

	protected boolean getBooleanPropertyValue(final String propertyName) {
		final String propertyValue = getStringPropertyValue(propertyName);
		return Boolean.parseBoolean(propertyValue);
	}

	protected String getStringPropertyValue(final String propertyName) {
		final String value = properties.getProperty(propertyName);
		if (value == null) {
			final String msg = "Property '" + propertyName + "' not found in property file";
			throw new IllegalStateException(msg);
		}
		return value;
	}

	public String getFullyQualifiedPropertyFileName() {
		return propertyFileDir + getPropertyFileName();
	}

	public static void setPropertyFileDir(final String directory) {
		propertyFileDir = directory;
	}

	public Properties getProperties() {
		return properties;
	}

}
