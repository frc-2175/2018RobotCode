package org.usfirst.frc.team2175.control;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;

import edu.wpi.first.wpilibj.Joystick;

public class DryverStation {
	private Joystick leftJoystick;
	private Joystick rightJoystick;
	private Joystick gamepad;
	
	public DryverStation() {
		ServiceLocator.register(this);
		RobotInfo robotInfo = ServiceLocator.get(RobotInfo.class);
		leftJoystick = robotInfo.get(RobotInfo.LEFT_JOYSTICK);
		rightJoystick = robotInfo.get(RobotInfo.RIGHT_JOYSTICK);
		gamepad = robotInfo.get(RobotInfo.GAMEPAD);
	}
	
	public double getMoveValue() {
		return leftJoystick.getY();
	}
	
	public double getTurnValue() {
		return rightJoystick.getX();
	}

	public double getIntakeAxisValue() {
		return gamepad.getRawAxis(0);
	}

	public double getClimberAxisValue() {
		return gamepad.getRawAxis(3);
	}
}
