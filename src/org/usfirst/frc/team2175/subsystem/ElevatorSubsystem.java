package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ElevatorSubsystem extends BaseSubsystem {
	private WPI_TalonSRX elevatorOne;
	private WPI_TalonSRX elevatorTwo;
	private RobotInfo robotInfo;
	private SmartDashboardInfo smartDashboardInfo;

	public ElevatorSubsystem() {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
		elevatorOne = robotInfo.get(RobotInfo.ELEVATOR_MOTOR);
		elevatorTwo = robotInfo.get(RobotInfo.ELEVATOR_MOTOR2);
		
		elevatorTwo.follow(elevatorOne);
	}

	public void runElevatorUp() {
		elevatorOne.set(smartDashboardInfo.getNumber(SmartDashboardInfo.RUN_ELEVATOR_UP_SPEED));
	}

	public void runElevatorDown() {
		elevatorOne.set(smartDashboardInfo.getNumber(SmartDashboardInfo.RUN_ELEVATOR_DOWN_SPEED));
	}
	
	public void runElevator(double axisValue) {
		elevatorOne.set(axisValue);
	}
	
	public void stopElevator() {
		elevatorOne.set(0);
	}
}
