package org.usfirst.frc.team2175.control;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;

import edu.wpi.first.wpilibj.Joystick;

public class Driverstation {
	private Joystick leftJoystick;
	private Joystick rightJoystick;
	private Joystick gamepad;
	RobotInfo robotInfo;
	
	public Driverstation() {
		ServiceLocator.register(this);
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
}