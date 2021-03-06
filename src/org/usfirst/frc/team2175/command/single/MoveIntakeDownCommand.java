package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class MoveIntakeDownCommand extends BaseCommand {
	private final IntakeSubsystem intakeSubsystem;

	public MoveIntakeDownCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
		requires(intakeSubsystem);
	}

	@Override
	protected void init() {
		intakeSubsystem.moveDown();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
