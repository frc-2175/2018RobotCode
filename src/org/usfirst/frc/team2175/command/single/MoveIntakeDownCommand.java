package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class MoveIntakeDownCommand extends BaseCommand{
	private IntakeSubsystem intakeSubsystem;
	
	public MoveIntakeDownCommand () {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}
	
	@Override
	protected void initialize() {
		intakeSubsystem.moveDown();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
