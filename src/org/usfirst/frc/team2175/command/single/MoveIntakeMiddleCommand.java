package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class MoveIntakeMiddleCommand extends BaseCommand {
	private final IntakeSubsystem intakeSubsystem;

	public MoveIntakeMiddleCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
		requires(intakeSubsystem);
	}

	@Override
	protected void init() {
		intakeSubsystem.moveHalf();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
