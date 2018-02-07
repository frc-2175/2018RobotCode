package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.MotorWrapper;
import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;

public class ElevatorSubsystem extends BaseSubsystem {
	private MotorWrapper elevatorMotor;
	private RobotInfo robotInfo;
	private SmartDashboardInfo smartDashboardInfo;

	public ElevatorSubsystem() {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
		elevatorMotor = robotInfo.get(RobotInfo.ELEVATOR_MOTOR);

	}

	public void runElevatorUp() {
		elevatorMotor.set(smartDashboardInfo.getNumber(SmartDashboardInfo.RUN_ELEVATOR_UP_SPEED));
	}

	public void runElevatorDown() {
		elevatorMotor.set(smartDashboardInfo.getNumber(SmartDashboardInfo.RUN_ELEVATOR_DOWN_SPEED));
	}

	public void runElevator(double axisValue) {
		elevatorMotor.set(axisValue * smartDashboardInfo.getNumber(SmartDashboardInfo.ELEVATOR_MAX_SPEED));
	}

	public void stopElevator() {
		elevatorMotor.set(0);
	}
}
