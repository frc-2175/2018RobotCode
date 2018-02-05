package org.usfirst.frc.team2175.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.usfirst.frc.team2175.ServiceLocator;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.wpi.first.wpilibj.Timer;

public class RobotLogger {
	private ArrayList<Loggable> loggers;
	private static final Logger log;
	public final static String BASE_DIRECTORY = "/home/lvuser/log/";
	private static int matchNumber = 0;
	private final static int NUMBER_OF_FOLDERS_TO_KEEP = 10;
	
	static {
		File workingDirectory = new File(BASE_DIRECTORY);
		File[] listedFiles = workingDirectory.listFiles();
		for(File file : listedFiles) {
			if(file.isDirectory()) {
				try {
					int current = Integer.parseInt(file.getName());
					if(current > matchNumber) {
						matchNumber = current;
					}
				} catch(NumberFormatException e) {
					System.out.println("Folder parsed did not contain an integer");
					e.printStackTrace();
				}
			}
		}
		matchNumber++;
		(new File(BASE_DIRECTORY + matchNumber)).mkdirs();
		
		ConfigurationFactory.setConfigurationFactory(new LoggingConfigurationFactory(BASE_DIRECTORY + "/" + matchNumber));
		log = LogManager.getLogger(RobotLogger.class.getName());
	}
	
	public static void loadClass() {}

	public static class LogEntry {
		double timestamp;
		HashMap<String, Object> values;

		public LogEntry(HashMap<String, Object> values) {
			this.timestamp = Timer.getFPGATimestamp();
			this.values = values;
		}
	}

	private Gson gson;
	private HashMap<Loggable, BufferedWriter> writers;

	public RobotLogger() {
		ServiceLocator.register(this);
		
		gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				// .setDateFormat("yyyy-MM-ddTHH:mm:ss.SSSX")
				.create();
		writers = new HashMap<>();

		loggers = new ArrayList<>();
		
		File workingDirectory = new File(BASE_DIRECTORY);
		File[] listedFiles = workingDirectory.listFiles();
		Arrays.sort(listedFiles, (File fileOne, File fileTwo) -> {
			try {
				int fileOneNumber = Integer.parseInt(fileOne.getName());
				int fileTwoNumber = Integer.parseInt(fileTwo.getName());
				int result;
				if(fileOneNumber > fileTwoNumber) {
					result = 1;
				} else if(fileOneNumber < fileTwoNumber) {
					result = -1;
				} else {
					result = 0;
				}
				return result;
			} catch(NumberFormatException e) {
				System.out.println("Failed to parse int from folder name" );
				e.printStackTrace();
				return 0;
			}
		});
		for(int i = 0; i < listedFiles.length - NUMBER_OF_FOLDERS_TO_KEEP; i++) {
			File[] directoryContents = listedFiles[i].listFiles();
			if(directoryContents != null) {
				for(File file : directoryContents) {
					file.delete();
				}
			}
			listedFiles[i].delete();
		}
	}

	public void addLoggable(Loggable loggable) {
		loggers.add(loggable);
	}

	public void flush() {
		for (BufferedWriter w : writers.values()) {
			try {
				w.flush();
			} catch (IOException e) {
				log.error("Failed to flush", e);
			}
		}
	}

	public String getLogFilename(Loggable l) {
		return BASE_DIRECTORY + "/" + matchNumber + "/" + l.getLogType() + "-" + l.getId() + ".log";
	}

	public void logLoggable(Loggable l) throws IOException {
		BufferedWriter w = writers.get(l);
		if (w == null) {
			w = new BufferedWriter(new FileWriter(getLogFilename(l)));
			writers.put(l, w);
		}

		String json = gson.toJson(new LogEntry(l.getValues()));
		w.write(json + "\n");
	}

	public void log() {
		for (Loggable logger : loggers) {
			try {
				logLoggable(logger);
			} catch (IOException e) {
				log.error("Failed to log " + getLogFilename(logger), e);
			}
		}
	}

}
