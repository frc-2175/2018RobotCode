package org.usfirst.frc.team2175.control;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class DryverStation {
	private Joystick leftJoystick;
	private Joystick rightJoystick;
	private Joystick gamepad;
	private SmartDashboardInfo smartDashboardInfo;
	private JoystickButton shiftButton;
	private JoystickButton intakeSpinInButton;
	private JoystickButton intakeSpinOutButton;
	private JoystickButton intakeActuateFullButton;
	private JoystickButton intakeActuateHalfButton;
	private JoystickButton intakeActuateNoneButton;
	private final double JOYSTICK_DEADBAND = 0.15;
	private final double GAMEPAD_DEADBAND = 0.1;

	public DryverStation() {
		ServiceLocator.register(this);
		RobotInfo robotInfo = ServiceLocator.get(RobotInfo.class);

		leftJoystick = robotInfo.get(RobotInfo.LEFT_JOYSTICK);
		rightJoystick = robotInfo.get(RobotInfo.RIGHT_JOYSTICK);
		gamepad = robotInfo.get(RobotInfo.GAMEPAD);

		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);

		shiftButton = new JoystickButton(leftJoystick, 1);
		intakeSpinInButton = new JoystickButton(gamepad, 3);
		intakeSpinOutButton = new JoystickButton(gamepad, 4);
		intakeActuateHalfButton = new JoystickButton(gamepad, 7);
		intakeActuateFullButton = new JoystickButton(gamepad, 8);
		intakeActuateNoneButton = new JoystickButton(gamepad, 5);
	}

	public double getMoveValue() {
		return deadband(leftJoystick.getY(), true);
	}

	public double getTurnValue() {
		return deadband(rightJoystick.getX(), true);
	}

	public double getIntakeAxisValue() {
		return deadband(gamepad.getRawAxis(2), false);
	}

	public double getClimberAxisValue() {
		return deadband(gamepad.getRawAxis(6), false);
	}

	public double getElevatorAxisValue() {
		return deadband(-gamepad.getRawAxis(1), false);
	}

	public boolean getIsSpinInButtonPressed() {
		return gamepad.getRawButton(3);
	}

	public boolean getIsSpinOutButtonPressed() {
		return gamepad.getRawButton(4);
	}

	protected double deadband(double value, boolean isJoystickDeadband) {
		double deadband = (isJoystickDeadband) ? JOYSTICK_DEADBAND : GAMEPAD_DEADBAND;
		if (Math.abs(value) > deadband) {
			if (value > 0.0) {
				return (value - deadband) / (1.0 - deadband);
			} else {
				return (value + deadband) / (1.0 - deadband);
			}
		} else {
			return 0.0;
		}
	}

	public JoystickButton getShiftButton() {
		return shiftButton;
	}

	public JoystickButton getIntakeSpinInButton() {
		return intakeSpinInButton;
	}

	public JoystickButton getIntakeSpinOutButton() {
		return intakeSpinOutButton;
	}

	public JoystickButton getIntakeActuateFullButton() {
		return intakeActuateFullButton;
	}

	public JoystickButton getIntakeActuateHalfButton() {
		return intakeActuateHalfButton;
	}

	public JoystickButton getIntakeActuateNoneButton() {
		return intakeActuateNoneButton;
	}
}
