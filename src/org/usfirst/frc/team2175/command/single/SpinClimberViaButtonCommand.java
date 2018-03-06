package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.SmartDashboardInfo;
import org.usfirst.frc.team2175.subsystem.ClimberSubsystem;

public class SpinClimberViaButtonCommand extends BaseCommand {

	protected final ClimberSubsystem climberSubsystem;
	protected final SmartDashboardInfo smartDashboardInfo;

	private boolean goingUp;

	public SpinClimberViaButtonCommand(boolean goingUp) {
		climberSubsystem = ServiceLocator.get(ClimberSubsystem.class);
		smartDashboardInfo = ServiceLocator.get(SmartDashboardInfo.class);
		this.goingUp = goingUp;
	}

	@Override
	protected void execute() {
		String key = (goingUp) ? SmartDashboardInfo.CLIMBER_UP_SPEED : SmartDashboardInfo.CLIMBER_DOWN_SPEED;
		climberSubsystem.spinClimber(smartDashboardInfo.getNumber(key));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void onEnd() {
		climberSubsystem.stop();
	}

}
