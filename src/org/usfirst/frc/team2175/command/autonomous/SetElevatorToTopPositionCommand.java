package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.ElevatorSubsystem;

public class SetElevatorToTopPositionCommand {
	private ElevatorSubsystem elevatorSubsystem;

	public SetElevatorToTopPositionCommand() {
		elevatorSubsystem = ServiceLocator.get(ElevatorSubsystem.class);
		
	}
	protected void execute() {
		elevatorSubsystem.runElevatorUp();
	}
	protected boolean isFinished() {
		return false;
	}
	
}
