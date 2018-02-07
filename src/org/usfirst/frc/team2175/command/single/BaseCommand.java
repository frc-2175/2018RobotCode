package org.usfirst.frc.team2175.command.single;

import edu.wpi.first.wpilibj.command.Command;

public abstract class BaseCommand extends Command {
	@Override
	protected void interrupted() {
		end();
	}

	protected double abs(double val) {
		return Math.abs(val);
	}
}
