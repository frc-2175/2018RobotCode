package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.subsystem.IntakeSubsystem;

public class SpinIntakeOutCommand extends BaseCommand {
	private final IntakeSubsystem intakeSubsystem;
	private final boolean usingSpeed;
	private final double speed;

	public SpinIntakeOutCommand() {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
		usingSpeed = false;
		speed = 0;
	}

	public SpinIntakeOutCommand(double speed) {
		intakeSubsystem = ServiceLocator.get(IntakeSubsystem.class);
		usingSpeed = true;
		this.speed = speed;
	}

	@Override
	protected void execute() {
		if (usingSpeed) {
			intakeSubsystem.runIntakeOut(speed);
		} else {
			intakeSubsystem.runIntakeOut(false);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void onEnd() {
		intakeSubsystem.clearValues();
	}
}
