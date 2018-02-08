package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.single.BaseCommand;
import org.usfirst.frc.team2175.subsystem.ElevatorSubsystem;

public class SetElevatorToTopPositionCommand extends BaseCommand {
	private ElevatorSubsystem elevatorSubsystem;

	public SetElevatorToTopPositionCommand() {
		elevatorSubsystem = ServiceLocator.get(ElevatorSubsystem.class);

	}

	@Override
	protected void execute() {
		elevatorSubsystem.runElevatorUp();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		elevatorSubsystem.stopElevator();
	}
}
