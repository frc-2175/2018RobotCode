package org.usfirst.frc.team2175.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc.team2175.ServiceLocator;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.wpi.first.wpilibj.Timer;

public class RobotLogger {
	private ArrayList<Loggable> loggers;
	private final static Logger log = Logger.getLogger(RobotLogger.class.getName());
	public final static String BASE_DIRECTORY = "/home/lvuser/log/";

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
		gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				// .setDateFormat("yyyy-MM-ddTHH:mm:ss.SSSX")
				.create();
		writers = new HashMap<>();

		ServiceLocator.register(this);

		loggers = new ArrayList<>();
	}

	public void addLoggable(Loggable loggable) {
		loggers.add(loggable);
	}

	public static String getLogFilename(Loggable l) {
		return BASE_DIRECTORY + l.getLogType() + "-" + l.getId() + ".log";
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
				log.log(Level.SEVERE, "Failed to log " + getLogFilename(logger), e);
			}
		}
	}

}
