package org.usfirst.frc.team2175.control;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;

import edu.wpi.first.wpilibj.DriverStation;
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
	private JoystickButton elevatorPresetLowButton;
	private JoystickButton intakeActuateNoneButton;
	private JoystickButton intakeSpinOutSlowButton;
	private JoystickButton climberRunOutButton;
	private JoystickButton climberRunButton;
	private JoystickButton driverSpinOutButton;
	private JoystickButton driverSpinOutSlowButton;
	private JoystickButton driverSpinOutSuperFastButton;
	private JoystickButton driverActuateDownButton;
	private JoystickButton actuateDownAndSpinInButton;
	private JoystickButton driverSpinOutVerySlowButton;
	private static final double JOYSTICK_DEADBAND = 0.15;
	private static final double GAMEPAD_DEADBAND = 0.1;

	public DryverStation() {
		ServiceLocator.register(this);
		RobotInfo robotInfo = ServiceLocator.get(RobotInfo.class);

		leftJoystick = robotInfo.get(RobotInfo.LEFT_JOYSTICK);
		rightJoystick = robotInfo.get(RobotInfo.RIGHT_JOYSTICK);
		gamepad = robotInfo.get(RobotInfo.GAMEPAD);

		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);

		shiftButton = new JoystickButton(leftJoystick, 1);
		intakeSpinInButton = new JoystickButton(gamepad, 8);
		intakeSpinOutButton = new JoystickButton(gamepad, 7);
		intakeActuateHalfButton = new JoystickButton(gamepad, 1);
		intakeActuateFullButton = new JoystickButton(gamepad, 2);
		elevatorPresetLowButton = new JoystickButton(gamepad, 3);
		intakeActuateNoneButton = new JoystickButton(gamepad, 4);
		intakeSpinOutSlowButton = new JoystickButton(gamepad, 5);
		climberRunOutButton = new JoystickButton(gamepad, 9);
		climberRunButton = new JoystickButton(gamepad, 10);
		driverSpinOutButton = new JoystickButton(rightJoystick, 3);
		driverSpinOutSlowButton = new JoystickButton(rightJoystick, 2);
		driverSpinOutSuperFastButton = new JoystickButton(rightJoystick, 1);
		driverActuateDownButton = new JoystickButton(leftJoystick, 2);
		actuateDownAndSpinInButton = new JoystickButton(gamepad, 6);
		driverSpinOutVerySlowButton = new JoystickButton(rightJoystick, 5);
	}

	public double getMoveValue() {
		return deadband(-leftJoystick.getY(), JOYSTICK_DEADBAND);
	}

	public double getTurnValue() {
		double value = Math.asin(rightJoystick.getX()) * 0.8;
		return deadband(value, JOYSTICK_DEADBAND);
	}

	public double getElevatorFullSpeedAxisValue() {
		return deadband(-gamepad.getRawAxis(1), GAMEPAD_DEADBAND);
	}

	public double getElevatorPrecisionAxisValue() {
		return deadband(-gamepad.getRawAxis(3), GAMEPAD_DEADBAND)
			* smartDashboardInfo.getNumber(SmartDashboardInfo.ELEVATOR_PRECISION_MODE);
	}

	public double getTurnCubeAxisValue() {
		return deadband(gamepad.getRawAxis(2), GAMEPAD_DEADBAND);
	}

	protected double deadband(double value, double deadband) {
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

	public JoystickButton getIntakeSpinOutSlowButton() {
		return intakeSpinOutSlowButton;
	}

	public JoystickButton getClimberRunButton() {
		return climberRunButton;
	}

	public JoystickButton getClimberRunOutButton() {
		return climberRunOutButton;
	}

	public JoystickButton getDriverSpinOutButton() {
		return driverSpinOutButton;
	}

	public JoystickButton getDriverSpinOutSlowButton() {
		return driverSpinOutSlowButton;
	}

	public JoystickButton getDriverSpinOutSuperFastButton() {
		return driverSpinOutSuperFastButton;
	}

	public JoystickButton getDriverSpinOutVerySlowButton() {
		return driverSpinOutVerySlowButton;
	}

	public JoystickButton getElevatorPresetLowButton() {
		return elevatorPresetLowButton;
	}

	public JoystickButton getDriverActuateDownButton() {
		return driverActuateDownButton;
	}

	public JoystickButton getActuateDownAndSpinInButton() {
		return actuateDownAndSpinInButton;
	}

	public boolean isSwitchLeft() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() > 0) {
			if (gameData.charAt(0) == 'L') {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean isScaleLeft() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() > 0) {
			if (gameData.charAt(1) == 'L') {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
