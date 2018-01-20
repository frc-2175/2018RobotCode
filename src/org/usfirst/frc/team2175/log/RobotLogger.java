package org.usfirst.frc.team2175.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.usfirst.frc.team2175.ServiceLocator;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.wpi.first.wpilibj.Timer;

public class RobotLogger {
	
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
	
	public RobotLogger() throws IOException {
		gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//				.setDateFormat("yyyy-MM-ddTHH:mm:ss.SSSX")
				.create();
		writers = new HashMap<>();
		
		ServiceLocator.register(this);
	}
	
	public static String getLogFilename(Loggable l) {
		return l.getLogType() + "-" + l.getId() + ".log";
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

}
