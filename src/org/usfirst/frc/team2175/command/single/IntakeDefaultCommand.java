package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.control.DryverStation;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class IntakeDefaultCommand extends BaseCommand {
	private IntakeSubsystem intakeSubsystem;
	private DryverStation driverStation;

	public IntakeDefaultCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
		driverStation = ServiceLocator.get(DryverStation.class);
		requires(intakeSubsystem);
	}

	@Override
	protected void execute() {
		intakeSubsystem.handleInputs(driverStation.getIntakeAxisValue());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
