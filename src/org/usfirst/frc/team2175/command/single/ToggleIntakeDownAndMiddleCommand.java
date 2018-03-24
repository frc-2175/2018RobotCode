package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class ToggleIntakeDownAndMiddleCommand extends BaseCommand {
	private final IntakeSubsystem intakeSubsystem;

	public ToggleIntakeDownAndMiddleCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}

	@Override
	protected void init() {
		intakeSubsystem.moveDown();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void onEnd() {
		intakeSubsystem.moveHalf();
	}
}
