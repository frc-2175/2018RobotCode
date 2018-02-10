package org.usfirst.frc.team2175.command.single;

import java.util.logging.Logger;

import org.usfirst.frc.team2175.log.RobotLogger;

import edu.wpi.first.wpilibj.command.Command;

public abstract class BaseCommand extends Command {
	protected final Logger log = RobotLogger.getLogger(getClass());

	public BaseCommand() {
		log.info("Constructing " + getClass().getName() + ".");
	}

	@Override
	protected void initialize() {
		log.info("Initializing " + getClass().getName() + ".");
	}

	@Override
	protected void end() {
		log.info("Ending " + getClass().getName() + ".");
	}

	@Override
	protected void interrupted() {
		log.info("Interrupting " + getClass().getName() + ".");
		end();
	}

	protected double abs(double val) {
		return Math.abs(val);
	}
}
