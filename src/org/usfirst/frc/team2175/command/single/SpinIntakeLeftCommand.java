package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class SpinIntakeLeftCommand extends BaseCommand {
	private IntakeSubsystem intakeSubsystem;

	public SpinIntakeLeftCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}

	@Override
	protected void execute() {
		intakeSubsystem.turnCube(false);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
