package org.usfirst.frc.team2175.log;

import java.util.HashMap;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class LoggableVictorSPX implements Loggable {
	private HashMap<String, Object> fields;
	private WPI_VictorSPX victor;
	private String key;

	public LoggableVictorSPX(String key, WPI_VictorSPX victor) {
		fields = new HashMap<>();
		this.victor = victor;
		this.key = key;
	}

	@Override
	public String getLogType() {
		return "talon";
	}

	@Override
	public String getId() {
		return key;
	}

	@Override
	public HashMap<String, Object> getValues() {
		fields.put("current", victor.getOutputCurrent());
		return fields;
	}
}
