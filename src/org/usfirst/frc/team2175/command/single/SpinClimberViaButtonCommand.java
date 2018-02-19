package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;
import org.usfirst.frc.team2175.subsystem.ClimberSubsystem;

public class SpinClimberViaButtonCommand extends BaseCommand {

	protected ClimberSubsystem climberSubsystem;
	protected SmartDashboardInfo smartDashboardInfo;

	public SpinClimberViaButtonCommand() {
		climberSubsystem = ServiceLocator.get(ClimberSubsystem.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
	}

	@Override
	protected void execute() {
		climberSubsystem.spinClimber(smartDashboardInfo.getNumber(SmartDashboardInfo.CLIMBER_SPEED));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		climberSubsystem.stop();
	}

}
