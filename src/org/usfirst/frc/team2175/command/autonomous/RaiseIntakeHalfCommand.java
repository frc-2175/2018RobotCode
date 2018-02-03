package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.single.BaseCommand;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class RaiseIntakeHalfCommand extends BaseCommand {
	private IntakeSubsystem intakeSubsystem;
		
	public RaiseIntakeHalfCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
	}
		
	@Override 
	protected void initialize() {
		intakeSubsystem.moveHalf();
	}
		
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		intakeSubsystem.stopAllMotors();
	}
}