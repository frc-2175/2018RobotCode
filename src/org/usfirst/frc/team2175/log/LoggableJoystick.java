package org.usfirst.frc.team2175.log;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Joystick;

public class LoggableJoystick implements Loggable {
	HashMap<String, Object> fields;
	private Joystick joystick;
	private String key;

	public LoggableJoystick(String key, Joystick joystick) {
		fields = new HashMap<>();
		this.joystick = joystick;
		this.key = key;
	}

	@Override
	public String getLogType() {
		return "joystick";
	}

	@Override
	public String getId() {
		return key;
	}

	@Override
	public HashMap<String, Object> getValues() {
		fields.put("x", joystick.getX());
		fields.put("y", joystick.getY());
		fields.put("twist", joystick.getTwist());
		return fields;
	}

}
