package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

/**
 * Spins the intake in and actuates the intake out. On end, it actuates the
 * intake to the middle position.
 */
public class SpinIntakeInDriverCommand extends BaseCommand {
	private final IntakeSubsystem intakeSubsystem;

	public SpinIntakeInDriverCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}

	@Override
	protected void init() {
		intakeSubsystem.moveDown();
	}

	@Override
	protected void execute() {
		intakeSubsystem.runIntakeIn();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void onEnd() {
		intakeSubsystem.moveHalf();
		intakeSubsystem.clearValues();
	}
}
