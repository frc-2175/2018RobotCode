package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.control.DryverStation;
import org.usfirst.frc.team2175.subsystem.ElevatorSubsystem;

public class ElevatorDefaultCommand extends BaseCommand{
	private ElevatorSubsystem elevatorSubsystem;
	private DryverStation driverStation;
	
	public ElevatorDefaultCommand () {
		elevatorSubsystem = ServiceLocator.get(ElevatorSubsystem.class);
		driverStation = ServiceLocator.get(DryverStation.class);
		requires(elevatorSubsystem);
	}

	@Override 
	protected void execute () {
		elevatorSubsystem.runElevator(driverStation.getElevatorAxisValue());
	}
	@Override
	protected boolean isFinished() {
		return false;
	}

}
