package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class SpinIntakeRightCommand extends BaseCommand {
	private IntakeSubsystem intakeSubsystem;

	public SpinIntakeRightCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}

	@Override
	protected void execute() {
		intakeSubsystem.turnCube(true);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
