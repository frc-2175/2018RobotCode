package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class MoveIntakeMiddleCommand extends BaseCommand {
	private IntakeSubsystem intakeSubsystem;

	public MoveIntakeMiddleCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}

	@Override
	protected void initialize() {
		intakeSubsystem.moveHalf();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
