package org.usfirst.frc.team2175.log;

import java.util.HashMap;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class LoggableJoystickButton implements Loggable {
	private JoystickButton joystickButton;
	private String key;
	private HashMap<String, Object> fields;

	public LoggableJoystickButton(String key, JoystickButton joystickButton) {
		fields = new HashMap<>();
		this.joystickButton = joystickButton;
		this.key = key;
	}

	@Override
	public String getLogType() {
		return "joystick-button";
	}

	@Override
	public String getId() {
		return key;
	}

	@Override
	public HashMap<String, Object> getValues() {
		fields.put("Pressed", joystickButton.get());
		return fields;
	}

}
