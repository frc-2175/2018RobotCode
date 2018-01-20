package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.control.DryverStation;
import org.usfirst.frc.team2175.subsystem.ClimberSubsystem;

public class SpinClimberDefaultCommand extends BaseCommand{
	private ClimberSubsystem climberSubsystem;
	private DryverStation driverStation;
	
	public SpinClimberDefaultCommand() {
		climberSubsystem = ServiceLocator.get(ClimberSubsystem.class);
		driverStation = ServiceLocator.get(DryverStation.class);
		requires(climberSubsystem);
	}
	@Override
	protected void execute() {
		climberSubsystem.spinClimber(driverStation.getClimberAxisValue());
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}