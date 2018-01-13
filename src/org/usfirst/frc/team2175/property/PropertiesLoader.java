package org.usfirst.frc.team2175.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesLoader {

    private static final Logger log = Logger.getLogger(PropertiesLoader.class.getName());

    private static final String CAN_T_CONTINUE_MSG = "; can't continue";

    /**
     * Load the properties from the specified file name.
     *
     * @param fileName
     *            The file name, including any desired path (absolute or
     *            relative).
     * @return Properties instance loaded with the properties in the file.
     */
    public static Properties loadProperties(final String fileName) {
        final File file = new File(fileName);
        return loadProperties(file);
    }

    public static Properties loadProperties(final File file) {
        final InputStream inputStream = openPropertiesFile(file);
        final Properties prop = loadPropertiesFromFile(file, inputStream);
        errorIfNoPropertiesLoaded(file, prop);

        return prop;
    }

    private static InputStream openPropertiesFile(final File file) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (final FileNotFoundException e) {
            final String msg = "Error finding properties file=" + file
                    + CAN_T_CONTINUE_MSG;
            log.log(Level.SEVERE, msg, e);
            throw new IllegalStateException(msg, e);
        }
        return inputStream;
    }

    private static Properties loadPropertiesFromFile(final File file,
            final InputStream inputStream) {
        final Properties prop = new Properties();
        try {
            prop.load(inputStream);
        } catch (final IOException e) {
            final String msg = "Error reading properties file=" + file
                    + CAN_T_CONTINUE_MSG;
            log.log(Level.SEVERE, msg, e);
            throw new IllegalStateException(msg, e);
        }
        return prop;
    }

    private static void errorIfNoPropertiesLoaded(final File file,
            final Properties prop) {
        if (prop.isEmpty()) {
            final String msg = "No properties were loaded from file=" + file
                    + CAN_T_CONTINUE_MSG;
            throw new IllegalStateException(msg);
        }
    }

}
