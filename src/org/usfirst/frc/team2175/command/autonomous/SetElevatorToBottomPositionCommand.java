package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.ElevatorSubsystem;

public class SetElevatorToBottomPositionCommand {
	private ElevatorSubsystem elevatorSubsystem;

	public SetElevatorToBottomPositionCommand() {
		elevatorSubsystem = ServiceLocator.get(ElevatorSubsystem.class);
		
	}
	protected void execute() {
		elevatorSubsystem.runElevatorDown();
	}
	protected boolean isFinished() {
		return false;
	}
}