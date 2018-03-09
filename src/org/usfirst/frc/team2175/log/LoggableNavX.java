package org.usfirst.frc.team2175.log;

import java.util.HashMap;

import com.kauailabs.navx.frc.AHRS;

public class LoggableNavX implements Loggable {
	private final AHRS navX;
	private final String key;
	private final HashMap<String, Object> fields;

	public LoggableNavX(AHRS navX, String key) {
		this.navX = navX;
		this.key = key;
		fields = new HashMap<>();
	}

	@Override
	public String getLogType() {
		return "navx";
	}

	@Override
	public String getId() {
		return key;
	}

	@Override
	public HashMap<String, Object> getValues() {
		fields.put("angle", navX.getAngle());
		return null;
	}

}
