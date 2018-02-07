package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class MoveIntakeUpCommand extends BaseCommand {
	private IntakeSubsystem intakeSubsystem;

	public MoveIntakeUpCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}

	@Override
	protected void initialize() {
		intakeSubsystem.moveUp();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
