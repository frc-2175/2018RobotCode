package org.usfirst.frc.team2175.log;

import java.util.HashMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class LoggableTalonSRX extends WPI_TalonSRX implements Loggable {

	public LoggableTalonSRX(int deviceNumber) {
		super(deviceNumber);
	}
	
	@Override
	public String getLogType() {
		return "talon";
	}

	@Override
	public int getId() {
		return this.getDeviceID();
	}

	@Override
	public HashMap<String, Object> getValues() {
		HashMap<String, Object> fields = new HashMap<>();
		fields.put("value", get());
		fields.put("isInverted", getInverted());
		fields.put("controlMode", getControlMode().name());
		return fields;
	}

}
