package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.MotorWrapper;
import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;

public class ClimberSubsystem extends BaseSubsystem {
	private final MotorWrapper masterMotor;
	private final RobotInfo robotInfo;

	public ClimberSubsystem() {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		masterMotor = robotInfo.get(RobotInfo.CLIMBER_MASTER);
	}

	public void spinClimber(double axisValue) {
		// Inverted during Duluth
		masterMotor.set(-axisValue);
	}

	public void stop() {
		spinClimber(0);
	}

}
