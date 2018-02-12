package org.usfirst.frc.team2175.command.single;

import static org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem.clamp;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.control.DryverStation;
import org.usfirst.frc.team2175.subsystem.ElevatorSubsystem;

public class ElevatorDefaultCommand extends BaseCommand {
	private ElevatorSubsystem elevatorSubsystem;
	private DryverStation driverStation;
	private double precisionValue;
	private double fullSpeedValue;

	public ElevatorDefaultCommand() {
		elevatorSubsystem = ServiceLocator.get(ElevatorSubsystem.class);
		driverStation = ServiceLocator.get(DryverStation.class);
		requires(elevatorSubsystem);
	}

	@Override
	protected void execute() {
		precisionValue = driverStation.getElevatorPrecisionAxisValue();
		fullSpeedValue = driverStation.getElevatorFullSpeedAxisValue();
		double moveValue = clamp(precisionValue + fullSpeedValue, -1, 1);
		elevatorSubsystem.runElevator(moveValue);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
