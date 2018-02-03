package org.usfirst.frc.team2175.log;

import java.util.HashMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class LoggableTalonSRX implements Loggable {
	private HashMap<String, Object> fields;
	private WPI_TalonSRX talon;
	private String key;

	public LoggableTalonSRX(String key, WPI_TalonSRX talon) {
		fields = new HashMap<>();
		this.talon = talon;
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
		fields.put("value", talon.get());
		return fields;
	}
}
